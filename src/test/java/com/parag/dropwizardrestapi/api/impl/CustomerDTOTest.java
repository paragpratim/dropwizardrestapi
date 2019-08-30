package com.parag.dropwizardrestapi.api.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.FixtureHelpers;

public class CustomerDTOTest {

	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD");
	private CustomerDTO aCustomer;

	@BeforeEach
	private void setUp() throws ParseException {
		aCustomer = new CustomerDTO(1222, "Parag", "Ghosh", "paragpratim@gmail.com", dateFormat.parse("2019-08-24"));
	}

	@Test
	public void serializationTest()
			throws IOException {
		String expected = MAPPER.writeValueAsString(
				MAPPER.readValue(FixtureHelpers.fixture("fixtures/customer.json"), CustomerDTO.class));
		Assertions.assertEquals(MAPPER.writeValueAsString(aCustomer), expected);
	}

	@Test
	public void deserializationTest()
			throws IOException {
		CustomerDTO actual = MAPPER.readValue(FixtureHelpers.fixture("fixtures/customer.json"), CustomerDTO.class);
		Assertions.assertEquals(aCustomer.toString(), actual.toString());
	}

}
