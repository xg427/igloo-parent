package org.iglooproject.test.infinispan.base;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.iglooproject.functional.Function2;
import org.iglooproject.test.infinispan.util.listener.MonitorNotifyListener;
import org.iglooproject.test.infinispan.util.process.SimpleProcess;
import org.iglooproject.test.infinispan.util.tasks.AbstractTask;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.notifications.cachemanagerlistener.annotation.ViewChanged;
import org.infinispan.notifications.cachemanagerlistener.event.ViewChangedEvent;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Joiner;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;

public abstract class TestBase {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestBase.class);

	protected Collection<Process> processesRegistry = Lists.newArrayList();
	protected EmbeddedCacheManager cacheManager = null;

	protected Collection<Process> prepareCluster(int nodeNumber, Class<? extends AbstractTask> taskName)
			throws IOException {
		Collection<Process> processes = Lists.newArrayListWithExpectedSize(nodeNumber);
		int expectedViewSize = nodeNumber + 1;

		// start other instances
		for (int i = 0; i < nodeNumber; i++) {
			Process process;
			if (taskName != null) {
				process = runInfinispan("node " + Integer.toString(i),
						Integer.toString(expectedViewSize), taskName.getName());
			} else {
				process = runInfinispan("node " + Integer.toString(i));
			}
			processes.add(process);
			processesRegistry.add(process);
		}

		return processes;
	}

	protected final Process runInfinispan(String nodeName, String... customArguments) throws IOException {
		Function2<URL, String> urlToFile = input -> input.getFile();
		String classpath = Joiner.on(File.pathSeparator)
				.join(
						Lists.newArrayList(((URLClassLoader) Thread.currentThread().getContextClassLoader()).getURLs())
								.stream()
								.map(urlToFile)
								.iterator()
				);
		List<String> arguments = Lists.newArrayList();
		arguments.add(System.getProperty("java.home") + "/bin/java");
		arguments.add("-classpath");
		arguments.add(classpath);
		arguments.add(getProcessClassName());
		arguments.add(nodeName);
		arguments.addAll(Lists.newArrayList(customArguments));

		Process process = new ProcessBuilder(arguments).start();
		new Thread(new ConsoleConsumer(process)).start();
		LOGGER.debug("command launched {}", Joiner.on(" ").join(arguments));
		return process;
	}

	protected String getProcessClassName() {
		return SimpleProcess.class.getCanonicalName();
	}

	@Before
	public void initializeJmx() {
		ManagementFactory.getPlatformMBeanServer();
	}

	@After
	public void shutdownProcesses() {
		LOGGER.debug("Stopping infinispan test {} (teardown whole cluster)", getClass().getSimpleName());
		shutdownProcesses(true);
	}

	protected void shutdownProcesses(boolean shutdownCache) {
		final Object monitor = new Object();
		cacheManager.addListener(new MonitorNotifyListener() {
			@ViewChanged
			public void onViewChanged(ViewChangedEvent viewChangedEvent) {
				synchronized (monitor) {
					LOGGER.debug("notify monitor on view change");
					monitor.notify();
				}
			}
		});
		if (cacheManager != null) {
			for (Process process : processesRegistry) {
				LOGGER.debug("process destroy asked {}", process);
				final int numView = cacheManager.getMembers().size();
				LOGGER.debug("process actual size {}", numView);
				process.destroy();
				
				// enable this code to stop nodes one-by-one
//				try {
//					
//					// wait for shutdown before stopping next node
//					waitForEvent(monitor, new Callable<Boolean>() {
//						@Override
//						public Boolean call() throws Exception {
//							if (LOGGER.isDebugEnabled()) {
//								LOGGER.debug("checking view size expected {} : actual {}", numView - 1,
//										cacheManager.getMembers().size());
//							}
//							
//							return cacheManager.getMembers().size() <= numView - 1;
//						}
//					}, 20, TimeUnit.SECONDS);
//					LOGGER.debug("process destroy viewed in cluster {}", process);
//				} catch (InterruptedException | TimeoutException | ExecutionException e) {
//					if (e instanceof InterruptedException) {
//						Thread.currentThread().interrupt();
//					} else {
//						LOGGER.warn("Error stopping threads", e);
//					}
//				}
			}
			
			for (Process process : processesRegistry) {
				try {
					process.waitFor();
					LOGGER.debug("process destroy detected {}", process);
				} catch (RuntimeException | InterruptedException e) {
					if (e instanceof InterruptedException) {
						Thread.currentThread().interrupt();
					} else {
						LOGGER.warn("Error stopping threads", e);
					}
				}
			}
			
			try {
				waitForEvent(monitor, new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						if (LOGGER.isDebugEnabled()) {
							LOGGER.debug("checking view size expected {} : actual {}", 1,
									cacheManager.getMembers().size());
						}
						return cacheManager.getMembers().size() <= 1;
					}
				}, 20, TimeUnit.SECONDS);
			} catch (InterruptedException | TimeoutException | ExecutionException e) {
				if (e instanceof InterruptedException) {
					Thread.currentThread().interrupt();
				} else {
					LOGGER.warn("Threads stopped but view not updated", e);
				}
			}
			
			processesRegistry.clear();
			
			if (shutdownCache) {
				// shutdown caches last (so that other processes terminates
				// gracefully)
				LOGGER.warn("cache stop {}", cacheManager);
				cacheManager.stop();
				LOGGER.warn("cache stopped {}", cacheManager);
			}
		}
	}

	public void waitForEvent(Object monitor, Callable<Boolean> testEvent, long delay, TimeUnit unit)
			throws InterruptedException, TimeoutException, ExecutionException {
		Stopwatch stopwatch = Stopwatch.createUnstarted();
		while (!Thread.currentThread().isInterrupted() && stopwatch.elapsed(unit) < delay) {
			if (!stopwatch.isRunning()) {
				stopwatch.start();
			}
			if (test(testEvent)) {
				return;
			}
			synchronized (monitor) {
				monitor.wait(Math.max(0, unit.toMillis(delay) - stopwatch.elapsed(TimeUnit.MILLISECONDS)));
			}
		}
		// test a last time
		if (test(testEvent)) {
			return;
		}
		// fails with timeout
		throw new TimeoutException();
	}

	/**
	 * Wait for {@code delay} {@code unit} (otherwise TimeoutException) that
	 * {@code nodeNumber} nodes connect to cluster.
	 * 
	 * @param nodeNumber
	 *            expected nodes' count
	 * @param delay
	 *            maximum time to wait for nodes
	 * @param unit
	 *            unit used to {@code delay} parameter
	 * @throws ExecutionException
	 * @throws TimeoutException
	 *             if {@code nodeNumber} not reached before {@code delay}
	 * @throws InterruptedException
	 */
	public void waitNodes(EmbeddedCacheManager cacheManager, int nodeNumber, int delay, TimeUnit unit)
			throws InterruptedException, TimeoutException, ExecutionException {
		Object monitor = new Object();
		waitForEvent(monitor, new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				LOGGER.warn("size: {}", cacheManager.getMembers().size());
				return cacheManager.getMembers().size() == nodeNumber;
			}

		}, delay, TimeUnit.SECONDS);
	}

	private boolean test(Callable<Boolean> testEvent) throws ExecutionException {
		try {
			if (testEvent.call()) {
				return true;
			}
		} catch (Exception e) {
			if (e instanceof InterruptedException) {
				Thread.currentThread().interrupt();
			}
			throw new ExecutionException(e);
		}
		return false;
	}

	// taken from
	// org.jboss.as.arquillian.container.managed.ManagedDeployableContainer
	private class ConsoleConsumer implements Runnable {
		
		private final Process process;
		
		public ConsoleConsumer(Process process) {
			super();
			this.process = process;
		}
		
		@Override
		public void run() {
			final InputStream stream = process.getInputStream();

			try {
				byte[] buf = new byte[32];
				int num;
				// Do not try reading a line cos it considers '\r' end of line
				while ((num = stream.read(buf)) != -1) {
					System.out.write(buf, 0, num);
				}
			} catch (IOException e) {
			}
		}

	}

}
