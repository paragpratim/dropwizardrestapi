package com.parag.dropwizardrestapi.resources.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.parag.dropwizardrestapi.api.impl.CustomerDTO;
import com.parag.dropwizardrestapi.core.testutils.RestAPIResourcesTester;
import com.parag.dropwizardrestapi.db.impl.CustomerDAO;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;

@ExtendWith(DropwizardExtensionsSupport.class)
public class CustomerResourcesTest {

	public static final Logger LOG = LoggerFactory.getLogger(CustomerResourcesTest.class);
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD");
	private CustomerDTO aCustomer;

	private static final List<Integer> SUCCESS_HTTP_CODE = Arrays.asList(200, 201);
	private static final RestAPIResourcesTester RESOURCE_TESTER = new RestAPIResourcesTester();
	private static final ResourceExtension RESOURCES = RESOURCE_TESTER.addResource(CustomerResources.class).build();
	private static final CustomerDAO CUSTOMER_DAO = (CustomerDAO) RESOURCE_TESTER.getResources(CustomerResources.class)
			.getDAO();

	@BeforeEach
	public void setUp() throws ParseException {
		aCustomer = new CustomerDTO(5, "Parag", "Ghosh", "paragpratim@gmail.com", dateFormat.parse("2019-08-24"));
		Mockito.when(CUSTOMER_DAO.get(ArgumentMatchers.anyLong())).thenReturn(aCustomer);

		ArrayList<CustomerDTO> customers = new ArrayList<>();
		customers.add(aCustomer);
		Mockito.when(CUSTOMER_DAO.getAll()).thenReturn(customers);
	}

	@AfterEach
	public void tearDown() {
		Mockito.reset(CUSTOMER_DAO);
	}

	@Test // Get all the resources test.
	public void getAllCustomerTest() {
		List<CustomerDTO> expected = Collections.singletonList(aCustomer);
		ArrayList<CustomerDTO> actual = RESOURCES.client().target("/customers").request()
				.get(new GenericType<ArrayList<CustomerDTO>>() {
				});
		Assertions.assertEquals(actual.size(), 1);
		Assertions.assertEquals(expected.get(0).toString(), actual.get(0).toString());
		Mockito.verify(CUSTOMER_DAO).getAll();
	}

	@Test // Get resource by id test.
	public void getCustomerByIdTest() {
		CustomerDTO actual = RESOURCES.client().target("/customers/5").request().get(CustomerDTO.class);
		Assertions.assertEquals(aCustomer.toString(), actual.toString());
		Mockito.verify(CUSTOMER_DAO).get(5);
	}

	@Test
	public void createCustomerTest() {
		Entity<CustomerDTO> entity = Entity.entity(aCustomer, MediaType.APPLICATION_JSON_TYPE);
		Response actual = RESOURCES.client().target("/customers").request().post(entity);
		Assertions.assertTrue(SUCCESS_HTTP_CODE.contains(actual.getStatus()));
		Mockito.verify(CUSTOMER_DAO).get(5);
	}

	@Test
	public void updateCustomerTest() {
		Entity<CustomerDTO> entity = Entity.entity(aCustomer, MediaType.APPLICATION_JSON_TYPE);
		Response actual = RESOURCES.client().target("/customers/5").request().put(entity);
		Assertions.assertTrue(SUCCESS_HTTP_CODE.contains(actual.getStatus()));
		Mockito.verify(CUSTOMER_DAO).get(5);
	}

	@Test
	public void deleteCustomerTest() {
		Response actual = RESOURCES.client().target("/customers/5").request().delete();
		Assertions.assertTrue(SUCCESS_HTTP_CODE.contains(actual.getStatus()));
		Mockito.verify(CUSTOMER_DAO).get(5);
	}

}