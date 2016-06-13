package pads.antiquary.model.tester;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import pads.antiquary.model.exceptions.Duplicated;
import pads.antiquary.model.item.Auctionable;
import pads.antiquary.model.item.Authenticity;
import pads.antiquary.model.item.Bulky;
import pads.antiquary.model.item.Item;
import pads.antiquary.model.item.TypeOfWork;
import pads.antiquary.model.item.WorkOfArt;
import pads.antiquary.model.people.Customer;
import pads.antiquary.model.people.Gender;
import pads.antiquary.model.people.Notificate;
import pads.antiquary.model.sales.Auction;
import pads.antiquary.model.sales.ContractType;
import pads.antiquary.model.sales.Sale;

public class CustomerTest {

	private Customer client1;
	private Customer client2;
	private Auctionable item1;
	private Auction auction1;
	private Item item2;
	Sale sale1;

	@Before
	public void newCustomer() throws Exception {
		client1 = new Customer(12, "James", "Fox", LocalDate.of(1995, 2, 24), "james@foo.com", 654321987, Gender.MALE,
				"Albacete", Notificate.ALWAYS, ContractType.Standard);
		client2 = new Customer(13, "Helen", "Black", LocalDate.of(1990, 3, 5), "helen@foo.com", 654321999,
				Gender.FEMALE, "NY", Notificate.NEVER, ContractType.Vip);
		item1 = new WorkOfArt("Girl #1 in bronze", "19xx", LocalDate.of(2014, 8, 10), 200.00, 600.00, "French School",
				TypeOfWork.Sculpture, Authenticity.N);
		auction1 = new Auction("Example1", "It�s the first example", 100, 10, 1, item1);
		item2 = new Bulky("Four-seat wooden pew;", "1916", LocalDate.of(2016, 1, 15), 75.00, 100.00, 32, 86, 175, 50);
		sale1 = new Sale(client1);
		sale1.addItem(item2);
	}

	@Test
	public void testCustomer1() {
		assertEquals(12, client1.getCustomerID());
		assertEquals("James", client1.getName());
		assertEquals("Fox", client1.getSurname());
		assertEquals(LocalDate.of(1995, 2, 24), client1.getBirthDay());
		assertEquals("james@foo.com", client1.getEmail());
		assertEquals(654321987, client1.getPhone());
		assertEquals(Gender.MALE, client1.getGender());
		assertEquals("Albacete", client1.getAdress());
		assertEquals(Notificate.ALWAYS, client1.getNotification());
		assertEquals(ContractType.Standard, client1.getContract().getType());

	}

	@Test
	public void testCustomer2() {
		assertEquals("James", client1.getName());
		assertEquals("Fox", client1.getSurname());
		assertEquals(LocalDate.of(1995, 2, 24), client1.getBirthDay());
		assertEquals("james@foo.com", client1.getEmail());
		assertEquals(654321987, client1.getPhone());
		assertEquals(Gender.MALE, client1.getGender());
		assertEquals("Albacete", client1.getAdress());
		assertEquals(Notificate.ALWAYS, client1.getNotification());
		assertEquals(ContractType.Standard, client1.getContract().getType());
	}

	@Test
	public void testCustomer3() {
		Customer customerStr = new Customer(client2.toString().split(";"));

		assertEquals(customerStr.getCustomerID(), client2.getCustomerID());
		assertEquals(customerStr.getName(), client2.getName());
		assertEquals(customerStr.getSurname(), client2.getSurname());
		assertEquals(customerStr.getBirthDay(), client2.getBirthDay());
		assertEquals(customerStr.getEmail(), client2.getEmail());
		assertEquals(customerStr.getPhone(), client2.getPhone());
		assertEquals(customerStr.getGender(), client2.getGender());
		assertEquals(customerStr.getAdress(), client2.getAdress());
		assertEquals(customerStr.getNotification(), client2.getNotification());
		assertEquals(customerStr.getContract().getType(), client2.getContract().getType());
	}

	@Test
	public void testSetCustomerID() {
		client1.setCustomerID(18);

		assertEquals(18, client1.getCustomerID());
	}

	@Test
	public void testIncreaseNumberAuctions() throws Duplicated {

		assertEquals(Integer.valueOf(0), client1.getNumberAuctions());
		auction1.entry(client1);
		assertEquals(Integer.valueOf(1), client1.getNumberAuctions());
		try {
			auction1.entry(client1);
		} catch (Duplicated d) {
		}
		assertEquals(Integer.valueOf(1), client1.getNumberAuctions());
	}

	@Test
	public void testIncreaseSpend() {
		assertEquals(Double.valueOf(0), client1.getAmountSpend());
		sale1.purchase();
		assertEquals(Double.valueOf(item2.getTargetPrice() + item2.deliveryCost()), client1.getAmountSpend());
	}

	@Test
	public void testAddItem() {
		client1.addItem(item2);
		assertTrue(client1.getItem().contains(item2));
		assertFalse(client1.getItem().contains((Item) item1));
	}

}
