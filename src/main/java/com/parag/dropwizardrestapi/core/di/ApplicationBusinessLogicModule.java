package com.parag.dropwizardrestapi.core.di;

import com.parag.dropwizardrestapi.db.DafaultDAO;
import com.parag.dropwizardrestapi.db.impl.CustomerDAO;
import com.parag.dropwizardrestapi.resources.DefaultResource;
import com.parag.dropwizardrestapi.resources.impl.CustomerResources;

public class ApplicationBusinessLogicModule extends CoreBusinessLogicModule {

	@Override
	protected void configure() {
		bind(DafaultDAO.class, CustomerDAO.class);
		bind(DefaultResource.class, CustomerResources.class);
	}
}
