package org.iglooproject.test.infinispan.base;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.remoting.transport.Address;
import org.junit.Test;

import com.google.common.collect.Maps;

import org.iglooproject.infinispan.model.Message;
import org.iglooproject.test.infinispan.util.TestCacheManagerBuilder;
import org.iglooproject.test.infinispan.util.TestConstants;
import org.iglooproject.test.infinispan.util.tasks.SimpleMessagingTask;

public class TestRestart extends TestBase {

	/**
	 * Simple messaging test with other nodes' restart.
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 * @throws TimeoutException
	 */
	@Test
	public void testMessage() throws IOException, InterruptedException, ExecutionException, TimeoutException {
		final int nodeNumber = 3;
		
		// start test instance
		final EmbeddedCacheManager cacheManager = new TestCacheManagerBuilder("node main", "test").build();
		this.cacheManager = cacheManager;
		
		final Object monitor = new Object();
		final Map<Address, String> messages = Maps.newConcurrentMap();
		
		cacheManager.start();
		cacheManager.getCache(TestConstants.CACHE_DEFAULT).addListener(new NodeMessageListener<String>() {
			@Override
			public void onMessage(Message<String> value) {
				messages.put(value.getAddress(), value.getMessage());
				synchronized (monitor) {
					monitor.notify();
				}
			}
		});
		Callable<Boolean> testAllNodes = waitForNNodes(4, cacheManager);
		
		// SimpleMessagingTask: on connect, each node send a message
		prepareCluster(nodeNumber, SimpleMessagingTask.class);
		
		// wait for n (n=nodeNumber) messages
		Callable<Boolean> testAllMessages = waitForMessages(nodeNumber, messages);
		// wait for messages from other nodes
		waitForEvent(monitor, testAllNodes, 20, TimeUnit.SECONDS);
		waitForEvent(monitor, testAllMessages, 20, TimeUnit.SECONDS);
		
		// shutdown all nodes
		shutdownProcesses(false);
		
		// wait alone state (1 node)
		Callable<Boolean> testAlone = waitForNNodes(1, cacheManager);
		waitForEvent(monitor, testAlone, 20, TimeUnit.SECONDS);
		
		// start new nodes
		prepareCluster(nodeNumber, SimpleMessagingTask.class);
		
		// wait joining nodes
		waitForEvent(monitor, testAllNodes, 20, TimeUnit.SECONDS);
		
		// wait 6 messages (as new nodes use new addresses, new messages are added)
		Callable<Boolean> testTwo = waitForMessages(6, messages);
		waitForEvent(monitor, testTwo, 20, TimeUnit.SECONDS);
	}

	private Callable<Boolean> waitForMessages(final int numberOfMessagesToWait, final Map<Address, String> messages) {
		Callable<Boolean> testOne = new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return messages.keySet().size() == numberOfMessagesToWait;
			}
		};
		return testOne;
	}

	private Callable<Boolean> waitForNNodes(final int numberOfNodes, final EmbeddedCacheManager cacheManager) {
		Callable<Boolean> test = new Callable<Boolean>() {
			@Override
			public Boolean call() throws Exception {
				return cacheManager.getMembers().size() == numberOfNodes;
			}
		};
		return test;
	}

}
