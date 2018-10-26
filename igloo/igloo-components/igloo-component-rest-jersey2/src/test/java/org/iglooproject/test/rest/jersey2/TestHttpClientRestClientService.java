package org.iglooproject.test.rest.jersey2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.iglooproject.test.rest.jersey2.business.person.model.Person;
import org.iglooproject.test.rest.jersey2.client.PersonRestClientService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class TestHttpClientRestClientService extends AbstractRestServiceTestCase {

	@Autowired
	private PersonRestClientService personRestClientService;
	
	@Test
	public void testListPerson() throws Exception {
		Person person1 = new Person();
		person1.setFirstName("Dave");
		person1.setLastName("King");
		personService.create(person1);
		
		Person person2 = new Person();
		person2.setFirstName("James");
		person2.setLastName("Hetfield");
		personService.create(person2);
		
		List<Person> result = personRestClientService.listPersons();
		assertEquals(2, result.size());
		assertTrue(result.stream().anyMatch(
			p -> p.getFirstName().equals(person1.getFirstName()) && p.getLastName().equals(person1.getLastName())
		));
		assertTrue(result.stream().anyMatch(
			p -> p.getFirstName().equals(person2.getFirstName()) && p.getLastName().equals(person2.getLastName())
		));
	}

	@Test
	public void testGetPerson() throws Exception {
		Person person = new Person();
		person.setFirstName("Corey");
		person.setLastName("Taylor");
		personService.create(person);
		
		Person result = personRestClientService.getPerson(person.getId());
		assertEquals(person.getId(), result.getId());
		assertEquals(person.getFirstName(), result.getFirstName());
		assertEquals(person.getLastName(), result.getLastName());
	}

	@Test
	public void testCreatePerson() throws Exception {
		Person person = new Person();
		person.setFirstName("Franky");
		person.setLastName("Costanza");
		
		Person result = personRestClientService.createPerson(person);
		
		List<Person> persons = personService.list();
		assertEquals(1, persons.size());
		assertEquals(person.getFirstName(), persons.get(0).getFirstName());
		assertEquals(person.getLastName(), persons.get(0).getLastName());
		assertEquals(person.getFirstName(), result.getFirstName());
		assertEquals(person.getLastName(), result.getLastName());
	}

	@Test
	public void testUpdatePerson() throws Exception {
		Person person = new Person();
		person.setFirstName("Lars");
		person.setLastName("Ulrich");
		personService.create(person);
		
		Person updatedPerson = new Person();
		updatedPerson.setId(person.getId());
		updatedPerson.setFirstName("Corey");
		updatedPerson.setLastName("Taylor");
		
		Person result = personRestClientService.updatePerson(updatedPerson);
		
		getEntityManager().clear();
		
		List<Person> persons = personService.list();
		assertEquals(1, persons.size());
		assertEquals(updatedPerson.getFirstName(), persons.get(0).getFirstName());
		assertEquals(updatedPerson.getLastName(), persons.get(0).getLastName());
		assertEquals(updatedPerson.getFirstName(), result.getFirstName());
		assertEquals(updatedPerson.getLastName(), result.getLastName());
	}

	@Test
	public void testDeletePerson() throws Exception {
		Person person = new Person();
		person.setFirstName("Lars");
		person.setLastName("Ulrich");
		personService.create(person);
		
		assertEquals(1, personService.list().size());
		
		personRestClientService.deletePerson(person.getId());
		
		assertEquals(0, personService.list().size());
	}
}
