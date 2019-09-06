package com.parag.dropwizardrestapi;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.parag.dropwizardrestapi.core.di.TestApplicationBusinessLogicModule;
import com.parag.dropwizardrestapi.core.testutils.GuiceJUnit5Extension;
import com.parag.dropwizardrestapi.core.testutils.RestAPIIntigrationTester;

import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

@ExtendWith(DropwizardExtensionsSupport.class)
@ExtendWith(GuiceJUnit5Extension.class)
@GuiceJUnit5Extension.GuiceModules(TestApplicationBusinessLogicModule.class)
public class ResourceIntigrationTest {

	public static final Logger LOG = LoggerFactory.getLogger(ResourceIntigrationTest.class);

	public static final DropwizardAppExtension<DropwizardrestapiConfiguration> DROPWIZARD = new DropwizardAppExtension<>(
			RestAPIIntigrationTester.class, RestAPIIntigrationTester.getTestConfiguration());

	@Test
	public void customerRespurceIntigrationTest() {
		Response response = DROPWIZARD.client().target("http://localhost:9000/api/customers")
				.request(MediaType.APPLICATION_JSON_TYPE).get();

		Assertions.assertEquals(response.getStatus(), Status.OK.getStatusCode());
	}
}
