package com.parag.dropwizardrestapi.resources.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.ClassRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import com.google.inject.Inject;
import com.google.inject.matcher.Matchers;
import com.parag.dropwizardrestapi.api.impl.CustomerDTO;
import com.parag.dropwizardrestapi.db.BaseDAO;

import io.dropwizard.testing.junit.ResourceTestRule;

public class CustomerResourcesTest {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("YYYY-MM-DD");
	private CustomerDTO aCustomer;

	@InjectMocks
	private static BaseDAO customerDAO;
	@Inject
	private static CustomerResources customerResources;

	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder().addResource(customerResources).build();

	@BeforeEach
	private void setUp() throws ParseException {
		aCustomer = new CustomerDTO(1222, "Parag", "Ghosh", "paragpratim@gmail.com", DATE_FORMAT.parse("2019-08-24"));
		Mockito.when(customerDAO.get(Matchers.any()).thenReturn(aCustomer);
		;
	}

	@AfterEach
	private void tearDown() {
		Mockito.reset(customerDAO);
	}

	@Test
	public void getCustomerTest() {
		

	}

}
