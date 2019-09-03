package com.parag.dropwizardrestapi.db.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.parag.dropwizardrestapi.api.BaseDTO;
import com.parag.dropwizardrestapi.api.impl.CustomerDTO;
import com.parag.dropwizardrestapi.db.BaseDAO;

public class CustomerDAO implements BaseDAO {

	private static final Logger LOG = Logger.getLogger(CustomerDAO.class);

	@Override
	public ArrayList<BaseDTO> getAll() {
		Map<Integer, BaseDTO> customers = new HashMap<>();
		customers.put(1, new CustomerDTO(1, "Parag", "Ghosh", "paragpratim@gmail.com", new Date()));
		customers.put(2, new CustomerDTO(2, "John", "Doe", "john.doe@abc.com", new Date()));
		customers.put(3, new CustomerDTO(3, "Jane", "Doe", "jane.doe@abc.com", new Date()));
		return new ArrayList<>(customers.values());
	}

	@Override
	public BaseDTO get(long id) {
		Map<Integer, BaseDTO> customers = new HashMap<>();
		customers.put(1, new CustomerDTO(1, "Parag", "Ghosh", "paragpratim@gmail.com", new Date()));
		customers.put(2, new CustomerDTO(2, "John", "Doe", "john.doe@abc.com", new Date()));
		customers.put(3, new CustomerDTO(3, "Jane", "Doe", "jane.doe@abc.com", new Date()));
		return customers.get(Math.toIntExact(id));
	}

	@Override
	public void update(long id, BaseDTO resource) {
		Map<Integer, BaseDTO> customers = new HashMap<>();
		customers.put(1, new CustomerDTO(1, "Parag", "Ghosh", "paragpratim@gmail.com", new Date()));
		customers.put(2, new CustomerDTO(2, "John", "Doe", "john.doe@abc.com", new Date()));
		customers.put(3, new CustomerDTO(3, "Jane", "Doe", "jane.doe@abc.com", new Date()));
		customers.put(Math.toIntExact(id), resource);

	}

	@Override
	public void remove(long id) {
		Map<Integer, BaseDTO> customers = new HashMap<>();
		customers.put(1, new CustomerDTO(1, "Parag", "Ghosh", "paragpratim@gmail.com", new Date()));
		customers.put(2, new CustomerDTO(2, "John", "Doe", "john.doe@abc.com", new Date()));
		customers.put(3, new CustomerDTO(3, "Jane", "Doe", "jane.doe@abc.com", new Date()));
		customers.remove(Math.toIntExact(id));

	}

}
