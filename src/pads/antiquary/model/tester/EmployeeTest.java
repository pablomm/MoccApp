package pads.antiquary.model.tester;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

import pads.antiquary.model.misc.Password;
import pads.antiquary.model.people.Employee;
import pads.antiquary.model.people.Gender;

public class EmployeeTest {

	private Employee employee1;
	private Employee employee2;

	@Test
	public void testEmployee1() throws Exception {

		employee1 = new Employee("Dianam", "Diana", "Marcos", LocalDate.of(1996, 12, 2), "dian@foo.com", 633333336,
				Gender.FEMALE, "Madrid", "dianampassword", false);

		assertEquals("Dianam", employee1.getUserName());
		assertEquals("Diana", employee1.getName());
		assertEquals("Marcos", employee1.getSurname());
		assertEquals(LocalDate.of(1996, 12, 2), employee1.getBirthDay());
		assertEquals("dian@foo.com", employee1.getEmail());
		assertEquals(633333336, employee1.getPhone());
		assertEquals(Gender.FEMALE, employee1.getGender());
		assertEquals("Madrid", employee1.getAdress());

	}

	@Test
	public void testEmployee2() throws Exception {
		employee2 = new Employee("Dianam", "Diana", "Marcos", LocalDate.of(1996, 12, 2), "dian@foo.com", 633333336,
				Gender.FEMALE, "Madrid",
				"uxiIX3Vfjn/z8PYmkixHt1RtIWVr+j+Erg3geLvHqvQ=$+WUYgOAM2Aqly3bikWL6qD8U0sjjlnOq0q6tIJJanEc=", false);

		assertEquals("Dianam", employee2.getUserName());
		assertEquals("Diana", employee2.getName());
		assertEquals("Marcos", employee2.getSurname());
		assertEquals(LocalDate.of(1996, 12, 2), employee2.getBirthDay());
		assertEquals("dian@foo.com", employee2.getEmail());
		assertEquals(633333336, employee2.getPhone());
		assertEquals(Gender.FEMALE, employee2.getGender());
		assertEquals("Madrid", employee2.getAdress());
		assertTrue(Password.check("dianampassword", employee2.getPassword()));
	}

	@Test
	public void testEmployee3() throws Exception {

		employee1 = new Employee("Dianam", "Diana", "Marcos", LocalDate.of(1996, 12, 2), "dian@foo.com", 633333336,
				Gender.FEMALE, "Madrid", "dianampassword", false);
		Employee employeeStr = new Employee(employee1.toString().split(";"));

		assertEquals(employeeStr.getUserName(), employee1.getUserName());
		assertEquals(employeeStr.getName(), employee1.getName());
		assertEquals(employeeStr.getSurname(), employee1.getSurname());
		assertEquals(employeeStr.getBirthDay(), employee1.getBirthDay());
		assertEquals(employeeStr.getEmail(), employee1.getEmail());
		assertEquals(employeeStr.getPhone(), employee1.getPhone());
		assertEquals(employeeStr.getGender(), employee1.getGender());
		assertEquals(employeeStr.getAdress(), employee1.getAdress());
		assertEquals(employeeStr.getPassword(), employee1.getPassword());
	}

}
