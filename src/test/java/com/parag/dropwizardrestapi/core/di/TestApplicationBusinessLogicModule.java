package com.parag.dropwizardrestapi.core.di;

import org.mockito.Mockito;

import com.google.inject.TypeLiteral;
import com.parag.dropwizardrestapi.api.impl.CustomerDTO;
import com.parag.dropwizardrestapi.db.BaseDAO;
import com.parag.dropwizardrestapi.db.impl.CustomerDAO;

public class TestApplicationBusinessLogicModule extends ApplicationBusinessLogicModule {

	@Override
	protected void getDAOBindings() {
		// Bind DAOs for mock and make sure they are serializable.
		bind(new TypeLiteral<BaseDAO<CustomerDTO>>() {
		}).toInstance(Mockito.mock(CustomerDAO.class, Mockito.withSettings().serializable()));
	}
}
