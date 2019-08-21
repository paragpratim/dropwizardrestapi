package com.parag.dropwizardrestapi.db;

import java.util.ArrayList;

import com.parag.dropwizardrestapi.api.DefaultDTO;

public interface DafaultDAO {

	ArrayList<DefaultDTO> getAll();

	DefaultDTO get(long id);

	void update(long id, DefaultDTO dto);

	void remove(long id);

}
