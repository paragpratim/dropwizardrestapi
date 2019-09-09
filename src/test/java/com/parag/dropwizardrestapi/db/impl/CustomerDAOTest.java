package com.parag.dropwizardrestapi.db.impl;

import java.text.ParseException;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.parag.dropwizardrestapi.api.impl.CustomerDTO;

public class CustomerDAOTest {

	public CustomerDTO aCustomerDTO;
	public CustomerDAO customerDao;

	@BeforeEach
	public void setUp() throws ParseException {
		aCustomerDTO = new CustomerDTO(1, "Parag", "Ghosh", "paragpratim@gmail.com", new Date());
		customerDao = new CustomerDAO();
	}

	@Test
	public void getAllCustomersTest() {
		Assertions.assertTrue(customerDao.getAll().contains(aCustomerDTO));
	}

	@Test
	public void getCustomerByIdTest() {
		Assertions.assertEquals(customerDao.get(1), aCustomerDTO);
	}

	@Test
	public void updateCustomerTest() {
		CustomerDTO customerDtoNew = new CustomerDTO(3, "Parag", "Pratim", "paragpratim@gmail.com", new Date());
		CustomerDAO customerDaoToUpdate = new CustomerDAO();
		customerDaoToUpdate.update(3, customerDtoNew);
		Assertions.assertEquals(customerDaoToUpdate.get(3), customerDtoNew);
	}

	@Test
	public void removeCustomerTest() {
		CustomerDAO customerDaoToRemove = new CustomerDAO();
		customerDaoToRemove.remove(3);
		Assertions.assertEquals(customerDaoToRemove.get(3), null);
	}
}
