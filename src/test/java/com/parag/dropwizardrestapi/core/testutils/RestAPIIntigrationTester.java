package com.parag.dropwizardrestapi.core.testutils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestAPIIntigrationTester extends Application<DropwizardrestapiConfiguration> {
	public static final Logger LOG = LoggerFactory.getLogger(RestAPIIntigrationTester.class);

	private static final Injector injector = Guice.createInjector(new TestApplicationBusinessLogicModule());

	@Override
	public void run(DropwizardrestapiConfiguration configuration, Environment environment) throws Exception {
		DateFormat eventDateFormat = new SimpleDateFormat(configuration.getDateFormat());
		environment.getObjectMapper().setDateFormat(eventDateFormat);
		BaseResource<CustomerDTO> customerResources = injector.getInstance(new Key<BaseResource<CustomerDTO>>() {
		});
		environment.jersey().register(customerResources);
	}

	public static DropwizardrestapiConfiguration getTestConfiguration() {
		ObjectMapper objectMapper = Jackson.newObjectMapper();
		Validator validator = Validators.newValidator();
		YamlConfigurationFactory<DropwizardrestapiConfiguration> factory = new YamlConfigurationFactory<>(
				DropwizardrestapiConfiguration.class, validator, objectMapper, "dw");
		File yaml = new File(Thread.currentThread().getContextClassLoader().getResource("config.yml").getPath());
		try {
			return factory.build(yaml);
		} catch (IOException | ConfigurationException e) {
			LOG.error(e.getMessage());
			return null;
		}
	}
}
