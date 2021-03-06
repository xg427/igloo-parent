package org.iglooproject.test.infinispan.base;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.iglooproject.infinispan.service.IInfinispanClusterService;
import org.iglooproject.infinispan.service.InfinispanClusterServiceImpl;
import org.iglooproject.test.infinispan.util.TestCacheManagerBuilder;
import org.iglooproject.test.infinispan.util.process.InfinispanClusterProcess;
import org.iglooproject.test.infinispan.util.roles.SimpleRolesProvider;

/**
 * Test {@link IInfinispanClusterService} wrapper.
 */
public class TestInfinispanCluster extends TestBase {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestBase.class);

	@Test
	public void testInfinispanCluster() throws IOException, InterruptedException, ExecutionException {
		// start infinispan
		final int nodeNumber = 3;
		
		// start test instance
		String nodeName = "node main";
		this.cacheManager = new TestCacheManagerBuilder(nodeName, "test").build();
		InfinispanClusterServiceImpl cluster =
				new InfinispanClusterServiceImpl(nodeName, cacheManager, new SimpleRolesProvider(), null, null);
		cluster.init();
		
		// start other nodes
		prepareCluster(nodeNumber, null);
		
		LOGGER.debug("waiting nodes");
		try {
			waitNodes(cacheManager, nodeNumber + 1, 20, TimeUnit.SECONDS);
		} catch (TimeoutException e) {
			Assert.fail(String.format("View size %d not reached before timeout", nodeNumber + 1));
		}
	}

	@Override
	protected String getProcessClassName() {
		return InfinispanClusterProcess.class.getCanonicalName();
	}

}
