package com.parag.dropwizardrestapi.db;

import java.util.ArrayList;

import com.parag.dropwizardrestapi.api.BaseDTO;

public interface BaseDAO {

	ArrayList<BaseDTO> getAll();

	BaseDTO get(long id);

	void update(long id, BaseDTO dto);

	void remove(long id);

}
