package com.parag.dropwizardrestapi.resources.impl;

import javax.validation.Validator;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.inject.Inject;
import com.parag.dropwizardrestapi.api.impl.CustomerDTO;
import com.parag.dropwizardrestapi.db.BaseDAO;
import com.parag.dropwizardrestapi.db.impl.CustomerDAO;
import com.parag.dropwizardrestapi.resources.BaseResource;

@Path("customer")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResources implements BaseResource {

	//private Validator validator;
	private BaseDAO customerDAO;

	@Inject
	public CustomerResources(/* Validator validator, */ CustomerDAO customerDAO) {
		//this.validator = validator;
		this.customerDAO = customerDAO;
	}

	@GET
	public Response getAll() {
		return Response.ok(customerDAO.getAll()).build();
	}

	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") Integer id) {
		CustomerDTO customer = (CustomerDTO) customerDAO.get(id.longValue());
		if (customer != null)
			return Response.ok(customer).build();
		else
			return Response.status(Status.NOT_FOUND).build();
	}

}
