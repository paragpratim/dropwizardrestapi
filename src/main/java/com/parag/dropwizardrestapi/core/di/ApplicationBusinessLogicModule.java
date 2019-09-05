package com.parag.dropwizardrestapi.core.di;

import com.google.inject.TypeLiteral;

import com.parag.dropwizardrestapi.api.impl.CustomerDTO;
import com.parag.dropwizardrestapi.db.BaseDAO;
import com.parag.dropwizardrestapi.db.impl.CustomerDAO;
import com.parag.dropwizardrestapi.resources.BaseResource;
import com.parag.dropwizardrestapi.resources.impl.CustomerResources;

public class ApplicationBusinessLogicModule extends CoreBusinessLogicModule {

	@Override
	protected void configure() {
		bind(new TypeLiteral<BaseResource<CustomerDTO>>() {
		}).to(CustomerResources.class);
		getDAOBindings();
	}

	protected void getDAOBindings() {
		bind(new TypeLiteral<BaseDAO<CustomerDTO>>() {
		}).to(CustomerDAO.class);
	}
}
