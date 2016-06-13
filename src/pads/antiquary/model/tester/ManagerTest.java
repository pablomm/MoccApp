package pads.antiquary.model.tester;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import pads.antiquary.model.people.Gender;
import pads.antiquary.model.people.Manager;

public class ManagerTest {

	private Manager manager;
	private Manager manager2;

	@Before
	public void newManager() throws Exception {
		manager = new Manager("Pablor", "Pablo", "Rojas", LocalDate.of(1996, 10, 3), "pabl@foo.com", 633333336,
				Gender.MALE, "Albacete", "pablorpassword", false);
		manager2 = new Manager("Pablor", "Pablo", "Rojas", LocalDate.of(1996, 10, 3), "pabl@foo.com", 633333336,
				Gender.MALE, "Albacete",
				"8bYvXr5DR81lv+nbMwAKt0FsYjS3kMW6oSPJkdynZOU=$B3R0mhR3N6AMC/4jw9Rp95UX2F5uL1b3XGjDKQr947M=", false);

	}

	@Test
	public void testManager1() throws Exception {

		assertEquals("Pablor", manager.getUserName());
		assertEquals("Pablo", manager.getName());
		assertEquals("Rojas", manager.getSurname());
		assertEquals(LocalDate.of(1996, 10, 3), manager.getBirthDay());
		assertEquals("pabl@foo.com", manager.getEmail());
		assertEquals(633333336, manager.getPhone());
		assertEquals(Gender.MALE, manager.getGender());
		assertEquals("Albacete", manager.getAdress());

	}

	@Test
	public void testManager2() throws Exception {

		assertEquals("Pablor", manager2.getUserName());
		assertEquals("Pablo", manager2.getName());
		assertEquals("Rojas", manager2.getSurname());
		assertEquals(LocalDate.of(1996, 10, 3), manager2.getBirthDay());
		assertEquals("pabl@foo.com", manager2.getEmail());
		assertEquals(633333336, manager2.getPhone());
		assertEquals(Gender.MALE, manager2.getGender());
		assertEquals("Albacete", manager2.getAdress());
	}

	@Test
	public void testManager3() {
		Manager managerStr = new Manager(manager.toString().split(";"));

		assertEquals(managerStr.getUserName(), manager.getUserName());
		assertEquals(managerStr.getName(), manager.getName());
		assertEquals(managerStr.getSurname(), manager.getSurname());
		assertEquals(managerStr.getBirthDay(), manager.getBirthDay());
		assertEquals(managerStr.getEmail(), manager.getEmail());
		assertEquals(managerStr.getPhone(), manager.getPhone());
		assertEquals(managerStr.getGender(), manager.getGender());
		assertEquals(managerStr.getAdress(), manager.getAdress());
		assertEquals(managerStr.getPassword(), manager.getPassword());
	}

}
