package com.parag.dropwizardrestapi.resources.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.GenericType;

import org.assertj.core.api.Assertions;
import org.junit.ClassRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.google.inject.Inject;
import com.parag.dropwizardrestapi.api.BaseDTO;
import com.parag.dropwizardrestapi.api.impl.CustomerDTO;
import com.parag.dropwizardrestapi.core.di.TestApplicationBusinessLogicModule;
import com.parag.dropwizardrestapi.db.BaseDAO;
import com.parag.dropwizardrestapi.core.testutils.GuiceJUnitRunner;
import io.dropwizard.testing.junit.ResourceTestRule;

@RunWith(GuiceJUnitRunner.class)
@GuiceJUnitRunner.GuiceModules(TestApplicationBusinessLogicModule.class)
public class CustomerResourcesTest {

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("YYYY-MM-DD");
	private BaseDTO aCustomer;

	@Inject
	private BaseDAO customerDAO;

	@Inject
	private static CustomerResources customerResources;

	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder().addResource(customerResources).build();

	@BeforeEach
	private void setUp() throws ParseException {
		aCustomer = new CustomerDTO(1222, "Parag", "Ghosh", "paragpratim@gmail.com", DATE_FORMAT.parse("2019-08-24"));
		Mockito.when(customerDAO.get(ArgumentMatchers.anyLong())).thenReturn(aCustomer);
	}

	@AfterEach
	private void tearDown() {
		Mockito.reset(customerDAO);
	}

	@Test
	public void getAllCustomerTest() {
		List<BaseDTO> expected = Collections.singletonList(aCustomer);
		List<BaseDTO> actual = resources.target("/api/customers").request().get(new GenericType<List<BaseDTO>>() {
		});

		Assertions.assertThat(actual).containsAll(expected);

	}

}

//