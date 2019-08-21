package com.parag.dropwizardrestapi.db.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.parag.dropwizardrestapi.api.DefaultDTO;
import com.parag.dropwizardrestapi.api.impl.Customer;
import com.parag.dropwizardrestapi.db.DafaultDAO;

public class CustomerDAO implements DafaultDAO {

	private static Map<Integer, DefaultDTO> customers = new HashMap<>();
	static {
		customers.put(1, new Customer(1, "Parag", "Ghosh", "paragpratim@gmail.com", new Date()));
		customers.put(2, new Customer(2, "John", "Doe", "john.doe@abc.com", new Date()));
		customers.put(3, new Customer(3, "Jane", "Doe", "jane.doe@abc.com", new Date()));
	}

	@Override
	public ArrayList<DefaultDTO> getAll() {
		return new ArrayList<>(customers.values());
	}

	@Override
	public DefaultDTO get(long id) {
		return customers.get(Math.toIntExact(id));
	}

	@Override
	public void update(long id, DefaultDTO resource) {
		customers.put(Math.toIntExact(id), resource);

	}

	@Override
	public void remove(long id) {
		customers.remove(Math.toIntExact(id));

	}

}
