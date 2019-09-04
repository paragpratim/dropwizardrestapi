package com.parag.dropwizardrestapi.resources.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.parag.dropwizardrestapi.core.di.TestApplicationBusinessLogicModule;
import com.parag.dropwizardrestapi.core.testutils.GuiceJUnit5Extension;
import com.parag.dropwizardrestapi.db.BaseDAO;
import com.parag.dropwizardrestapi.resources.BaseResource;

@ExtendWith(GuiceJUnit5Extension.class)
@GuiceJUnit5Extension.GuiceModules(TestApplicationBusinessLogicModule.class)
public class SampleTest {
	private static final Logger LOG = LoggerFactory.getLogger(SampleTest.class);

	@Inject
	private BaseDAO customerDAO;

	@Inject
	private BaseResource customerResources;

	@Test
	public void testGuiceInit() {
		LOG.info("Inside @Test");
		LOG.info(customerDAO.getClass().toString());
		LOG.info(customerResources.getClass().toString());
		LOG.info(customerDAO.getAll().toString());
	}

}
