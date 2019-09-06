package com.parag.dropwizardrestapi.resources.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.GenericType;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.parag.dropwizardrestapi.DropwizardrestapiConfiguration;
import com.parag.dropwizardrestapi.api.impl.CustomerDTO;
import com.parag.dropwizardrestapi.core.di.TestApplicationBusinessLogicModule;
import com.parag.dropwizardrestapi.core.testutils.GuiceJUnit5Extension;
import com.parag.dropwizardrestapi.core.testutils.RestAPIIntigrationTester;
import com.parag.dropwizardrestapi.db.BaseDAO;

import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

@ExtendWith(DropwizardExtensionsSupport.class)
@ExtendWith(GuiceJUnit5Extension.class)
@GuiceJUnit5Extension.GuiceModules(TestApplicationBusinessLogicModule.class)
public class CustomerResourcesTest {
	
	public static final Logger LOG = LoggerFactory.getLogger(CustomerResourcesTest.class);

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD");
	private CustomerDTO aCustomer;

	@Inject
	private BaseDAO<CustomerDTO> customerDAO;

	public static final DropwizardAppExtension<DropwizardrestapiConfiguration> DROPWIZARD = new DropwizardAppExtension<>(
			RestAPIIntigrationTester.class, RestAPIIntigrationTester.getTestConfiguration());

	@BeforeEach
	private void setUp() throws ParseException {
		aCustomer = new CustomerDTO(5, "Parag", "Ghosh", "paragpratim@gmail.com", dateFormat.parse("2019-08-24"));
		Mockito.when(customerDAO.get(ArgumentMatchers.anyLong())).thenReturn(aCustomer);
		ArrayList<CustomerDTO> customers = new ArrayList<>();
		customers.add(aCustomer);
		Mockito.when(customerDAO.getAll()).thenReturn(customers);
	}

	@Test
	public void getAllCustomerTest() {
		LOG.info(customerDAO.getAll().toString());
		List<CustomerDTO> expected = Collections.singletonList(aCustomer);
		ArrayList<CustomerDTO> actual = DROPWIZARD.client().target("http://localhost:9000/api/customers").request()
				.get(new GenericType<ArrayList<CustomerDTO>>() {
				});
		LOG.info(expected.get(0).toString());
		LOG.info(actual.get(0).toString());
		// Response response = customerResources.getById(5);
		// System.out.println(response.getEntity().toString());
		Assertions.assertEquals(expected, actual);
		// Mockito.verify(customerDAO).get(5L);

	}

}

//