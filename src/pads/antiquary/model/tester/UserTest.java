package pads.antiquary.model.tester;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import pads.antiquary.model.exceptions.InvalidPassword;
import pads.antiquary.model.misc.Password;
import pads.antiquary.model.people.Employee;
import pads.antiquary.model.people.Gender;
import pads.antiquary.model.people.Manager;
import pads.antiquary.model.people.User;

public class UserTest {

	private User user1;
	private User user2;

	@Before
	public void newUser() throws Exception {
		user2 = new Employee("Dianam", "Diana", "Marcos", LocalDate.of(1996, 12, 2), "dian@foo.com", 633333336,
				Gender.FEMALE, "Madrid",
				"uxiIX3Vfjn/z8PYmkixHt1RtIWVr+j+Erg3geLvHqvQ=$+WUYgOAM2Aqly3bikWL6qD8U0sjjlnOq0q6tIJJanEc=", false);
	}

	@Test
	public void testUser1() throws Exception {
		user1 = new Manager("Pablor", "Pablo", "Rojas", LocalDate.of(1996, 10, 3), "pabl@foo.com", 633333336,
				Gender.MALE, "Albacete", "pablorpassword", false);
		assertEquals("Pablor", user1.getUserName());
		assertEquals("Pablo", user1.getName());
		assertEquals("Rojas", user1.getSurname());
		assertEquals(LocalDate.of(1996, 10, 3), user1.getBirthDay());
		assertEquals("pabl@foo.com", user1.getEmail());
		assertEquals(633333336, user1.getPhone());
		assertEquals(Gender.MALE, user1.getGender());
		assertEquals("Albacete", user1.getAdress());
	}

	@Test
	public void testUser2() throws Exception {
		assertEquals("Dianam", user2.getUserName());
		assertEquals("Diana", user2.getName());
		assertEquals("Marcos", user2.getSurname());
		assertEquals(LocalDate.of(1996, 12, 2), user2.getBirthDay());
		assertEquals("dian@foo.com", user2.getEmail());
		assertEquals(633333336, user2.getPhone());
		assertEquals(Gender.FEMALE, user2.getGender());
		assertEquals("Madrid", user2.getAdress());
		assertTrue(Password.check("dianampassword", user2.getPassword()));
	}

	@Test(expected = InvalidPassword.class)
	public void testChangePassword1() throws InvalidPassword {
		user2.changePassword("");
	}

	@Test
	public void testChangePassword2() throws Exception {
		user2.changePassword("asdfgh");
		assertTrue(Password.check("asdfgh", user2.getPassword()));
	}

}
