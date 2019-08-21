package com.parag.dropwizardrestapi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.parag.dropwizardrestapi.core.di.ApplicationBusinessLogicModule;
import com.parag.dropwizardrestapi.resources.DefaultResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DropwizardrestapiApplication extends Application<DropwizardrestapiConfiguration> {

	private static final Injector injector = Guice.createInjector(new ApplicationBusinessLogicModule());
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(DropwizardrestapiApplication.class);

	public static void main(final String[] args) throws Exception {
		new DropwizardrestapiApplication().run(args);
	}

	@Override
	public String getName() {
		return "dropwizardrestapi";
	}

	@Override
	public void initialize(final Bootstrap<DropwizardrestapiConfiguration> bootstrap) {
		// TODO: application initialization
	}

	@Override
	public void run(final DropwizardrestapiConfiguration configuration, final Environment environment) {
		DateFormat eventDateFormat = new SimpleDateFormat(configuration.getDateFormat());
		environment.getObjectMapper().setDateFormat(eventDateFormat);
		DefaultResource customerResources = injector.getInstance(DefaultResource.class);
		environment.jersey().register(customerResources);
	}

}
