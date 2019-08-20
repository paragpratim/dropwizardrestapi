package com.parag.dropwizardrestapi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class DropwizardrestapiApplication extends Application<DropwizardrestapiConfiguration> {

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

	}

}
