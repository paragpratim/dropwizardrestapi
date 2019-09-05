package com.parag.dropwizardrestapi.core.testutils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.validation.Validator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.parag.dropwizardrestapi.DropwizardrestapiConfiguration;
import com.parag.dropwizardrestapi.api.impl.CustomerDTO;
import com.parag.dropwizardrestapi.core.di.TestApplicationBusinessLogicModule;
import com.parag.dropwizardrestapi.resources.BaseResource;

import io.dropwizard.Application;
import io.dropwizard.configuration.ConfigurationException;
import io.dropwizard.configuration.YamlConfigurationFactory;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.jersey.validation.Validators;
import io.dropwizard.setup.Environment;

public class RestApiTester extends Application<DropwizardrestapiConfiguration> {

	private static final Injector injector = Guice.createInjector(new TestApplicationBusinessLogicModule());

	@Override
	public void run(DropwizardrestapiConfiguration configuration, Environment environment) throws Exception {
		DateFormat eventDateFormat = new SimpleDateFormat(getTestConfiguration().getDateFormat());
		environment.getObjectMapper().setDateFormat(eventDateFormat);
		BaseResource<CustomerDTO> customerResources = injector.getInstance(new Key<BaseResource<CustomerDTO>>() {
		});
		environment.jersey().register(customerResources);
	}

	public DropwizardrestapiConfiguration getTestConfiguration() throws IOException, ConfigurationException {
		ObjectMapper objectMapper = Jackson.newObjectMapper();
		Validator validator = Validators.newValidator();
		YamlConfigurationFactory<DropwizardrestapiConfiguration> factory = new YamlConfigurationFactory<>(
				DropwizardrestapiConfiguration.class, validator, objectMapper, "dw");
		File yaml = new File(Thread.currentThread().getContextClassLoader().getResource("config.yml").getPath());
		return factory.build(yaml);
	}

}
