package com.parag.dropwizardrestapi.resources.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.ws.rs.core.GenericType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.parag.dropwizardrestapi.api.impl.CustomerDTO;
import com.parag.dropwizardrestapi.core.di.TestApplicationBusinessLogicModule;
import com.parag.dropwizardrestapi.core.testutils.GuiceJUnit5Extension;
import com.parag.dropwizardrestapi.core.testutils.RestAPIResourcesTester;
import com.parag.dropwizardrestapi.db.BaseDAO;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;

@ExtendWith(DropwizardExtensionsSupport.class)
@ExtendWith(GuiceJUnit5Extension.class)
@GuiceJUnit5Extension.GuiceModules(TestApplicationBusinessLogicModule.class)
public class CustomerResourcesTest {

	public static final Logger LOG = LoggerFactory.getLogger(CustomerResourcesTest.class);

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD");
	private CustomerDTO aCustomer;

	@Inject
	private BaseDAO<CustomerDTO> customerDAO;

	public static final ResourceExtension resources = new RestAPIResourcesTester().addResource().build();

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

		// List<CustomerDTO> expected = Collections.singletonList(aCustomer);
		ArrayList<CustomerDTO> actual = resources.client().target("/customers").request()
				.get(new GenericType<ArrayList<CustomerDTO>>() {
				});
		// //LOG.info(actual.get(0).toString());
		// //Assertions.assertEquals(expected, actual);
		// //Mockito.verify(customerDAO).get(5L);
		// Mockito.verify(customerDAO).getAll();

		System.out.println(actual.toString());
	}

}

//