package com.parag.dropwizardrestapi.db;

import java.util.ArrayList;

import com.parag.dropwizardrestapi.api.BaseDTO;

public interface BaseDAO<T extends BaseDTO> {

	ArrayList<T> getAll();

	T get(long id);

	void update(long id, T dto);

	void remove(long id);
}
