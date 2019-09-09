package com.parag.dropwizardrestapi.core.testutils;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.parag.dropwizardrestapi.api.BaseDTO;
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
	private List<BaseResource<? extends BaseDTO>> resources;

	@SuppressWarnings("rawtypes")
	public BaseResource getResources(Class<? extends BaseResource> resourceType) {
		for (BaseResource<? extends BaseDTO> resource : resources) {
			if (resource.getClass().getTypeName().equals(resourceType.getTypeName())) {
				return resource;
			}
		}
		return null;
	}

	public void setResources() {
		this.resources = new ArrayList<>();
		this.resources.add(injector.getInstance(new Key<BaseResource<CustomerDTO>>() {
		}));
	}

	@SuppressWarnings("rawtypes")
	public Builder addResource(Class<? extends BaseResource> resourceType) {
		setResources();
		for (BaseResource<? extends BaseDTO> resource : resources) {
			if (resource.getClass().getTypeName().equals(resourceType.getTypeName())) {
				return super.addResource(resource);
			}
		}
		LOG.error("No Resources to bind");
		return null;
	}
}
