package com.parag.dropwizardrestapi;

import org.hibernate.validator.constraints.NotEmpty;

import io.dropwizard.Configuration;

public class DropwizardrestapiConfiguration extends Configuration {

	@NotEmpty
	private String dateFormat;

	public String getDateFormat() {
		return dateFormat;
	}

}
