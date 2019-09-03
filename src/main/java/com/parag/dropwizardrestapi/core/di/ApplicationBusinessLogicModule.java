package com.parag.dropwizardrestapi.core.di;

import com.parag.dropwizardrestapi.db.BaseDAO;
import com.parag.dropwizardrestapi.db.impl.CustomerDAO;
import com.parag.dropwizardrestapi.resources.BaseResource;
import com.parag.dropwizardrestapi.resources.impl.CustomerResources;

public class ApplicationBusinessLogicModule extends CoreBusinessLogicModule {

	@Override
	protected void configure() {
		bind(BaseResource.class, CustomerResources.class);
		//getDAOBindings();
	}

	protected void getDAOBindings() {
		//bind(BaseDAO.class, CustomerDAO.class);
	}
}
