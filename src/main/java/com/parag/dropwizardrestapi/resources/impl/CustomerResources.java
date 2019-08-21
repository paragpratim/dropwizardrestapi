package com.parag.dropwizardrestapi.resources.impl;

import javax.validation.Validator;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.parag.dropwizardrestapi.resources.DefaultResource;

@Path("customer")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResources implements DefaultResource {

	private Validator validator;

}
