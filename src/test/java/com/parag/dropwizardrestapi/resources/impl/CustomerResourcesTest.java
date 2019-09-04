package com.parag.dropwizardrestapi.resources.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.ClassRule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.google.inject.Inject;
import com.parag.dropwizardrestapi.api.BaseDTO;
import com.parag.dropwizardrestapi.api.impl.CustomerDTO;
import com.parag.dropwizardrestapi.core.di.TestApplicationBusinessLogicModule;
import com.parag.dropwizardrestapi.core.testutils.GuiceJUnit5Extension;
import com.parag.dropwizardrestapi.db.BaseDAO;

import io.dropwizard.testing.junit.ResourceTestRule;

@ExtendWith(GuiceJUnit5Extension.class)
@GuiceJUnit5Extension.GuiceModules(TestApplicationBusinessLogicModule.class)
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
		aCustomer = new CustomerDTO(5, "Parag", "Ghosh", "paragpratim@gmail.com", DATE_FORMAT.parse("2019-08-24"));
		Mockito.when(customerDAO.get(ArgumentMatchers.anyLong())).thenReturn(aCustomer);
		ArrayList<BaseDTO> customers = new ArrayList<BaseDTO>();
		customers.add(aCustomer);
		Mockito.when(customerDAO.getAll()).thenReturn(customers);
	}

	@AfterEach
	private void tearDown() {
		Mockito.reset(customerDAO);
	}

	@Test
	public void getAllCustomerTest() {
		List<BaseDTO> expected = Collections.singletonList(aCustomer);
//		ArrayList<BaseDTO> actual = resources.target("/api/customers").request().get(new GenericType<ArrayList<BaseDTO>>() {
//		});
		System.out.println(customerDAO.getClass());
		Response response = customerResources.getById(5);
		System.out.println(response.getStatusInfo());
		//Assertions.assertThat(actual).containsAll(expected);
		Mockito.verify(customerDAO).get(5L);

	}

}

//