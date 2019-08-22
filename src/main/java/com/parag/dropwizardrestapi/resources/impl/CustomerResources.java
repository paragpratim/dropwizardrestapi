package com.parag.dropwizardrestapi.resources.impl;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.inject.Inject;
import com.parag.dropwizardrestapi.api.BaseDTO;
import com.parag.dropwizardrestapi.api.impl.CustomerDTO;
import com.parag.dropwizardrestapi.db.BaseDAO;
import com.parag.dropwizardrestapi.db.impl.CustomerDAO;
import com.parag.dropwizardrestapi.resources.BaseResource;

@Path("customers")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResources implements BaseResource<CustomerDTO> {

	private BaseDAO customerDAO;

	@Inject
	public CustomerResources(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	@Override
	public Response getAll() {
		return Response.ok(customerDAO.getAll()).build();
	}

	@Override
	public Response getById(Integer id) {
		BaseDTO customer = customerDAO.get(id.longValue());
		if (customer != null)
			return Response.ok(customer).build();
		else
			return Response.status(Status.NOT_FOUND).build();
	}

	@Override
	public Response create(CustomerDTO aDTO) throws URISyntaxException {
		BaseDTO existingCustomer = customerDAO.get(aDTO.getId());
		if (null != existingCustomer) {
			customerDAO.update(aDTO.getId(), aDTO);
			return Response.created(new URI("/customers/" + aDTO.getId())).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@Override
	public Response update(CustomerDTO aDTO) throws URISyntaxException {
		BaseDTO existingCustomer = customerDAO.get(aDTO.getId());
		if (null != existingCustomer) {
			customerDAO.update(aDTO.getId(), aDTO);
			return Response.created(new URI("/customers/" + aDTO.getId())).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@Override
	public Response deleteById(Integer id) {
		BaseDTO customer = customerDAO.get(id.longValue());
		if (null != customer) {
			customerDAO.remove(id);
			return Response.ok().build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

}