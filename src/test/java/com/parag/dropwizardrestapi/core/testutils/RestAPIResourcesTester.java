package com.parag.dropwizardrestapi.core.testutils;

import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.parag.dropwizardrestapi.api.impl.CustomerDTO;
import com.parag.dropwizardrestapi.core.di.TestApplicationBusinessLogicModule;
import com.parag.dropwizardrestapi.resources.BaseResource;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import io.dropwizard.testing.junit5.ResourceExtension.Builder;

@ExtendWith(DropwizardExtensionsSupport.class)
public class RestAPIResourcesTester extends ResourceExtension.Builder {
	public static final Logger LOG = LoggerFactory.getLogger(RestAPIResourcesTester.class);

	private static final Injector injector = Guice.createInjector(new TestApplicationBusinessLogicModule());

	public Builder addResource() {
		BaseResource<CustomerDTO> customerResources = injector.getInstance(new Key<BaseResource<CustomerDTO>>() {
		});
		return super.addResource(customerResources);
	}
}
