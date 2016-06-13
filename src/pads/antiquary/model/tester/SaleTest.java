package pads.antiquary.model.tester;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pads.antiquary.model.exceptions.*;
import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.item.*;
import pads.antiquary.model.people.*;
import pads.antiquary.model.sales.*;

public class SaleTest {
	

	private Item item1;
	private Item item2;
	private Customer client1;
	private Customer client2;
	private Sale sale;
	private Sale sale2;

	@Before
	public void newSale() throws Duplicated {
		item1 = new WorkOfArt("Girl #1 in bronze", "19xx", LocalDate.of(2014, 8, 10), 200.00, 600.00, "French School",
				TypeOfWork.Sculpture, Authenticity.N);
		item2 = new Bulky("Four-seat wooden pew", "1916", LocalDate.of(2016, 1, 15), 75.00, 100.00, 32, 86, 175, 50);

		item1.setSold(false);
		item1.setInAuction(false);
		item2.setSold(true);
		client1 = new Customer(12, "James", "Fox", LocalDate.of(1995, 2, 24), "james@foo.com", 654321987, Gender.MALE,
				"Albacete", Notificate.ALWAYS, ContractType.Standard);
		client2 = new Customer(13, "Helen", "Black", LocalDate.of(1990, 3, 5), "helen@foo.com", 654321999,
				Gender.FEMALE, "NY", Notificate.NEVER, ContractType.Vip);
		sale = new Sale(client1);
		sale2 = new Sale(client2);
		Shop.getShop().addCustomer(client1);
	}

	

	@Test
	public void testSale() {
		assertEquals(12,sale.getCustomer().getCustomerID());
		assertEquals("James",sale.getCustomer().getName());
		assertEquals(LocalDate.of(1995, 2, 24),sale.getCustomer().getBirthDay());
	}

	@Test
	public void testAddItem() throws Duplicated, InvalidSale {
		sale.addItem(item1);
		
		assertTrue(sale.getItems().contains(item1));
		assertEquals(item1.getTargetPrice(),sale.getFinalPrice());
	}

	@Test
	public void testPurchase() throws Duplicated, InvalidSale {

		sale.addItem(item1);
		sale.purchase();
		assertTrue(sale.getItems().contains(item1));
		assertTrue(item1.isSold());
		
	}

	@Test
	public void testEqualsObject() throws Duplicated, InvalidSale {
		sale.addItem(item1);
		
		assertTrue(sale.getItems().contains(item1));
		assertFalse(sale2.getItems().contains(item1));
		assertFalse(sale2.getItems().contains(null));
		assertFalse(sale2.getItems().contains(""));
		
	}
	@After
	public void clean(){
		Shop.cleanShop();
	}

}
