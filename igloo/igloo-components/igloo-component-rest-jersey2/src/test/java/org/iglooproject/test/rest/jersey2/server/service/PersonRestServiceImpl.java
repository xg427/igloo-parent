package org.iglooproject.test.rest.jersey2.server.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.iglooproject.jpa.exception.SecurityServiceException;
import org.iglooproject.jpa.exception.ServiceException;
import org.iglooproject.rest.jersey2.service.AbstractRestServiceImpl;
import org.iglooproject.test.rest.jersey2.business.person.model.Person;
import org.iglooproject.test.rest.jersey2.business.person.service.IPersonService;
import org.iglooproject.test.rest.jersey2.server.exception.TestRemoteApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
public class PersonRestServiceImpl extends AbstractRestServiceImpl {

	@Autowired
	private IPersonService personService;

	@GET
	public List<Person> list() {
		return personService.list();
	}

	@GET
	@Path("/{id}")
	public Person get(@PathParam("id") Long id) {
		return personService.getById(id);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Person create(Person person) {
		try {
			Person newPerson = new Person();
			newPerson.setFirstName(person.getFirstName());
			newPerson.setLastName(person.getLastName());
			
			personService.create(newPerson);
			
			return newPerson;
		} catch (ServiceException | SecurityServiceException e) {
			throw getException(TestRemoteApiError.UNKNOWN_SERVER_ERROR, e);
		}
	}

	@POST
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Person update(@PathParam("id") Long id, Person person) {
		try {
			Person persistedPerson = personService.getById(id);
			persistedPerson.setFirstName(person.getFirstName());
			persistedPerson.setLastName(person.getLastName());
			
			personService.update(persistedPerson);
			
			return persistedPerson;
		} catch (ServiceException | SecurityServiceException e) {
			throw getException(TestRemoteApiError.UNKNOWN_SERVER_ERROR, e);
		}
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		try {
			Person person = personService.getById(id);
			personService.delete(person);
		} catch (ServiceException | SecurityServiceException e) {
			throw getException(TestRemoteApiError.UNKNOWN_SERVER_ERROR, e);
		}
	}
}