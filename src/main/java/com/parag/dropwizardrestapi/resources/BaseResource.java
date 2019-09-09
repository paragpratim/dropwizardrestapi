package com.parag.dropwizardrestapi.resources;

import java.net.URISyntaxException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.parag.dropwizardrestapi.api.BaseDTO;
import com.parag.dropwizardrestapi.db.BaseDAO;

public interface BaseResource<T extends BaseDTO> {

	@GET
	Response getAll();

	@GET
	@Path("/{id}")
	Response getById(@PathParam("id") Integer id);

	@POST
	Response create(T aDTO) throws URISyntaxException;

	@PUT
	@Path("/{id}")
	Response update(T aDTO) throws URISyntaxException;

	@DELETE
	@Path("/{id}")
	Response deleteById(@PathParam("id") Integer id);

	BaseDAO<T> getDAO();
}
