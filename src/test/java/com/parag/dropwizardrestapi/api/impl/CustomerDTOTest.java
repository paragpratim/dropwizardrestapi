package com.parag.dropwizardrestapi.api.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.FixtureHelpers;

public class CustomerDTOTest {

	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("YYYY-MM-DD");
	private CustomerDTO aCustomer;

	@BeforeEach
	private void setUp() throws ParseException {
		aCustomer = new CustomerDTO(1222, "Parag", "Ghosh", "paragpratim@gmail.com", DATE_FORMAT.parse("2019-08-24"));
		;
	}

	@Test
	public void serializationTest()
			throws ParseException, JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		String expected = MAPPER.writeValueAsString(
				MAPPER.readValue(FixtureHelpers.fixture("fixtures/customer.json"), CustomerDTO.class));
		Assertions.assertEquals(MAPPER.writeValueAsString(aCustomer), expected);
	}

	@Test
	public void deserializationTest()
			throws ParseException, JsonParseException, JsonMappingException, JsonProcessingException, IOException {
		CustomerDTO actual = MAPPER.readValue(FixtureHelpers.fixture("fixtures/customer.json"), CustomerDTO.class);
		Assertions.assertEquals(aCustomer.toString(), actual.toString());
	}

}
