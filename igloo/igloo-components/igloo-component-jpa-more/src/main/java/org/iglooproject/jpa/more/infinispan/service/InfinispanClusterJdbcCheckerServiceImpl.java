package org.iglooproject.jpa.more.infinispan.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.iglooproject.functional.Functions2;
import org.iglooproject.infinispan.model.AddressWrapper;
import org.iglooproject.infinispan.service.IInfinispanClusterCheckerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

public class InfinispanClusterJdbcCheckerServiceImpl implements IInfinispanClusterCheckerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(InfinispanClusterJdbcCheckerServiceImpl.class);

	@Autowired
	private DataSource dataSource;

	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public boolean unsetCoordinator(AddressWrapper oldCoordinator) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("nodeType", "master");
		parameterSource.addValue("anonymous", "anonymous");
		parameterSource.addValue("node", oldCoordinator.toString());
		parameterSource.addValue("lastUpdateDate", new Date());
		try {
			long count = jdbcTemplate.update("update infinispan set node = :anonymous, lastupdatedate = :lastUpdateDate where nodetype = :nodeType and node = :node", parameterSource);
			return count == 1;
		} catch (RuntimeException e) {
			LOGGER.error("Fails to unset coordinator", e);
			return false;
		}
	}

	@Override
	public boolean updateCoordinator(AddressWrapper newCoordinator, Collection<AddressWrapper> knownNodes) {
		List<String> knownNodesString = Lists.newArrayList();
		knownNodesString.addAll(Collections2.transform(knownNodes, Functions2.toStringFunction()));
		knownNodesString.add("anonymous");
		knownNodesString.add(newCoordinator.toString());
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("nodeType", "master");
		parameterSource.addValue("anonymous", "anonymous");
		parameterSource.addValue("node", newCoordinator.toString());
		parameterSource.addValue("knownNodes", knownNodesString);
		parameterSource.addValue("lastUpdateDate", new Date());
		{
			int count = jdbcTemplate.queryForObject("select count(*) from infinispan where nodetype = :nodeType", parameterSource, Integer.class);
			if (count == 0) {
				jdbcTemplate.update("insert into infinispan (node, nodetype, lastupdatedate) values (:anonymous, :nodeType, :lastUpdateDate)", parameterSource);
			}
		}
		int count = jdbcTemplate.update("update infinispan set node = :node, lastupdatedate = :lastUpdateDate where nodetype = :nodeType and node in (:knownNodes)", parameterSource);
		return count == 1;
	}

	@Override
	public boolean updateCoordinatorTimestamp(AddressWrapper currentCoordinator) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("nodeType", "master");
		parameterSource.addValue("node", currentCoordinator.toString());
		parameterSource.addValue("lastUpdateDate", new Date());
		int count = jdbcTemplate.update("update infinispan set lastupdatedate = :lastUpdateDate where nodetype = :nodeType and node = :node", parameterSource);
		return count == 1;
	}

	@Override
	public boolean tryForceUpdate(AddressWrapper currentCoordinator, int delay, TimeUnit unit) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("nodeType", "master");
		parameterSource.addValue("node", currentCoordinator.toString());
		parameterSource.addValue("lastUpdateDate", new Date());
		parameterSource.addValue("delay", String.format("%d MILLISECONDS", unit.toMillis(delay)));
		int count = jdbcTemplate.update("update infinispan set node = :node, lastupdatedate = :lastUpdateDate where nodetype = :nodeType and lastupdatedate < NOW() - CAST(:delay AS INTERVAL)", parameterSource);
		return count == 1;
	}

	@Override
	public boolean isClusterActive(Collection<AddressWrapper> clusterNodes) {
		List<String> clusterNodesString = Lists.newArrayList();
		clusterNodesString.addAll(Collections2.transform(clusterNodes, Functions2.toStringFunction()));
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("clusterNodes", clusterNodesString);
		parameterSource.addValue("nodeType", "master");
		return jdbcTemplate.queryForObject("select count(*) from infinispan where nodetype = :nodeType and node in (:clusterNodes)", parameterSource, Integer.class) == 1;
	}

	@PostConstruct
	public void init() { //NOSONAR
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

}
