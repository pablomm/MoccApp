package pads.antiquary.model.tester;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import pads.antiquary.model.people.Employee;
import pads.antiquary.model.people.Gender;
import pads.antiquary.model.people.Manager;
import pads.antiquary.model.people.Person;

public class PersonTest {

	private Person person1;
	private Person person2;

	@Before
	public void newItem() throws Exception {
		person1 = new Manager("Pablor", "Pablo", "Rojas", LocalDate.of(1996, 10, 3), "pabl@foo.com", 633333336,
				Gender.MALE, "Albacete", "pablorpassword", false);
		person2 = new Employee("Dianam", "Diana", "Marcos", LocalDate.of(1996, 12, 2), "dian@foo.com", 633333336,
				Gender.FEMALE, "Madrid", "dianampassword", false);
	}

	@Test
	public void testPerson() {

		assertEquals("Diana", person2.getName());
		assertEquals("Marcos", person2.getSurname());
		assertEquals(LocalDate.of(1996, 12, 2), person2.getBirthDay());
		assertEquals("dian@foo.com", person2.getEmail());
		assertEquals(633333336, person2.getPhone());
		assertEquals(Gender.FEMALE, person2.getGender());
		assertEquals("Albacete", person1.getAdress());
	}

}
