package com.parag.dropwizardrestapi.api.impl;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.parag.dropwizardrestapi.api.DefaultDTO;

public class Customer implements DefaultDTO {

	@NotNull
	private long id;
	@NotBlank
	@Length(min = 2, max = 255)
	private String firstName;
	@NotBlank
	@Length(min = 2, max = 255)
	private String lastName;
	@Pattern(regexp = ".+@.+\\.[a-z]+")
	private String email;
	@NotNull
	private Date creationDate;

	public Customer(long id, String firstName, String lastName, String email, Date creationDate) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.creationDate = creationDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", creationDate=" + creationDate + "]";
	}

}
