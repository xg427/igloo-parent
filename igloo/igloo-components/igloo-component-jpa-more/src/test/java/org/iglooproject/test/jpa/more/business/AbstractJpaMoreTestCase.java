package org.iglooproject.test.jpa.more.business;

import org.iglooproject.jpa.exception.SecurityServiceException;
import org.iglooproject.jpa.exception.ServiceException;
import org.iglooproject.jpa.more.business.referencedata.service.IGenericReferenceDataService;
import org.iglooproject.spring.property.dao.IMutablePropertyDao;
import org.iglooproject.spring.property.service.IConfigurablePropertyService;
import org.iglooproject.test.jpa.junit.AbstractTestCase;
import org.iglooproject.test.jpa.more.business.entity.service.ITestEntityService;
import org.iglooproject.test.jpa.more.config.spring.JpaMoreTestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = JpaMoreTestConfig.class)
public abstract class AbstractJpaMoreTestCase extends AbstractTestCase {

	@Autowired
	protected IGenericReferenceDataService genericReferenceDataService;

	@Autowired
	protected ITestEntityService testEntityService;

	@Autowired
	protected IConfigurablePropertyService propertyService;
	
	@Autowired
	private IMutablePropertyDao mutablePropertyDao;

	@Override
	protected void cleanAll() throws ServiceException, SecurityServiceException {
		cleanEntities(testEntityService);
		mutablePropertyDao.cleanInTransaction();
	}
}
