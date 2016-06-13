package pads.antiquary.model.tester;

import static org.junit.Assert.*;

import java.time.LocalDate;


import org.junit.Before;
import org.junit.Test;

import pads.antiquary.model.exceptions.*;
import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.item.*;
import pads.antiquary.model.people.*;
import pads.antiquary.model.sales.*;

public class ShopTest {
	private Auction auction1;
	private Auction auction2;

	private Item item1;
	private Auctionable item2;
	private Lot lot1;
	private Customer client1;
	private Customer client2;
	private Sale sale;
	private Sale sale2;
	private User user1;
	private User user2;



	@Before
	public void newAuction() throws Exception {
		Shop.cleanShop();
		item1 = new Bulky("Four-seat wooden pew;", "1916", LocalDate.of(2016, 1, 15), 75.00, 100.00, 32, 86, 175, 50);

		item2 = new WorkOfArt("Girl #1 in bronze", "19xx", LocalDate.of(2014, 8, 10), 200.00, 600.00, "French School",
				TypeOfWork.Sculpture, Authenticity.N);

		lot1 = new Lot();
		lot1.addItem(item1);
		client1 = new Customer(12, "James", "Fox", LocalDate.of(1995, 2, 24), "james@foo.com", 654321987, Gender.MALE,
				"Albacete", Notificate.ALWAYS, ContractType.Standard);
		client2 = new Customer(13, "Helen", "Black", LocalDate.of(1990, 3, 5), "helen@foo.com", 654321999,
				Gender.FEMALE, "NY", Notificate.NEVER, ContractType.Vip);

		auction1 = new Auction("Example1", "Its the first example", 100, 10, 1, lot1);
		auction2 = new Auction("Example2", "Its the second example", 200, 20, 1, item2);
		user1 = new Manager("Pablor", "Pablo", "Rojas", LocalDate.of(1996, 10, 3), "pabl@foo.com", 633333336,
				Gender.MALE, "Albacete", "pablorpassword", false);
		user2 = new Employee("Dianam", "Diana", "Marcos", LocalDate.of(1996, 12, 2), "dian@foo.com", 633333336,
				Gender.FEMALE, "Madrid",
				"uxiIX3Vfjn/z8PYmkixHt1RtIWVr+j+Erg3geLvHqvQ=$+WUYgOAM2Aqly3bikWL6qD8U0sjjlnOq0q6tIJJanEc=", false);
		sale = new Sale(client1);
		sale2 = new Sale(client2);
	}

	@Test
	public void testCleanShop() {
		Shop.cleanShop();
		assertTrue(Shop.getShop().getAuctions().isEmpty());
		assertTrue(Shop.getShop().getCustomers().isEmpty());
		assertTrue(Shop.getShop().getSales().isEmpty());
		assertTrue(Shop.getShop().getStock().isEmpty());
		assertTrue(Shop.getShop().getUsers().isEmpty());
		assertTrue(Shop.getShop().getUserLogin() == null);
	}

	@Test
	public void testAddItem() throws Duplicated {
		Shop.getShop().addItem(item1);
		assertTrue(Shop.getShop().getStock().contains(item1));
	}
	
	@Test(expected = Duplicated.class)
	public void testAddItem2() throws Duplicated {
		Shop.getShop().addItem(item1);
		Shop.getShop().addItem(item1);
	}

	@Test
	public void testAddUser() throws Duplicated {
		Shop.getShop().addUser(user1);
		Shop.getShop().addUser(user2);
		assertTrue(Shop.getShop().getUsers().contains(user1));
		assertTrue(Shop.getShop().getUsers().contains(user2));
	}
	
	@Test(expected = Duplicated.class)
	public void testAddUser2() throws Duplicated {
		Shop.getShop().addUser(user1);
		Shop.getShop().addUser(user1);
	}

	@Test
	public void testAddCustomer() throws Duplicated {
		Shop.getShop().addCustomer(client1);
		Shop.getShop().addCustomer(client2);
		assertTrue(Shop.getShop().getCustomers().contains(client1));
		assertTrue(Shop.getShop().getCustomers().contains(client2));
		
		
	}
	
	@Test(expected = Duplicated.class)
	public void testAddCustomer2() throws Duplicated {
		Shop.getShop().addCustomer(client1);
		Shop.getShop().addCustomer(client1);
	}

	@Test
	public void testAddSale() throws Duplicated {
		Shop.getShop().addSale(sale);
		Shop.getShop().addSale(sale2);
		assertTrue(Shop.getShop().getSales().contains(sale));
		assertTrue(Shop.getShop().getSales().contains(sale2));
	}

	@Test
	public void testAddAuction() throws Duplicated {
		Shop.getShop().addAuction(auction1);
		Shop.getShop().addAuction(auction2);
		assertTrue(Shop.getShop().getAuctions().contains(auction1));
		assertTrue(Shop.getShop().getAuctions().contains(auction2));
	}
	
	@Test(expected = Duplicated.class)
	public void testAddAuction2() throws Duplicated {
		Shop.getShop().addAuction(auction1);
		Shop.getShop().addAuction(auction1);
	}

	@Test
	public void testSearchUser() throws Duplicated, NotFound {
		Shop.getShop().addUser(user1);
		assertEquals(user1,Shop.getShop().searchUser("Pablor"));
	}
	
	@Test (expected = NotFound.class)
	public void testSearchUser2() throws Duplicated, NotFound {
		assertEquals(user1,Shop.getShop().searchUser("Pablorsd"));
	}

	@Test
	public void testSearchCustomer() throws Duplicated, NotFound {
		Shop.getShop().addCustomer(client1);
		assertTrue(Shop.getShop().searchCustomer(client1.getName()).contains(client1));
	}
	@Test (expected = NotFound.class)
	public void testSearchCustomer2() throws Duplicated, NotFound {
		Shop.getShop().searchCustomer("FakeName");
	}

	@Test
	public void testSearchCustomerbyID() throws Duplicated, NotFound {
		Shop.getShop().addCustomer(client1);
		assertEquals(client1,Shop.getShop().searchCustomerbyID((Integer.valueOf(client1.getCustomerID()).toString())));
	}

	@Test
	public void testSearchItemByStr() throws Duplicated, NotFound {
		Shop.getShop().addItem(item1);
		assertTrue(Shop.getShop().searchItemByStr("pew").contains(item1));
	}
	
	@Test (expected = NotFound.class)
	public void testSearchItemByStr2() throws Duplicated, NotFound {
		assertEquals(item1,Shop.getShop().searchItemByStr("pewjijijij"));
	}

	@Test
	public void testSearchExactItem() throws Duplicated, NotFound {
		Shop.getShop().addItem(item1);
		assertEquals(item1,Shop.getShop().searchExactItem(item1.getDescription()));
	}
	
	@Test (expected = NotFound.class)
	public void testSearchExactItem2() throws Duplicated, NotFound {
		assertEquals(item1,Shop.getShop().searchExactItem(item1.getDescription()));
	}

	@Test
	public void testSearchAuction() throws Duplicated, NotFound {
		Shop.getShop().addAuction(auction1);
		assertTrue(Shop.getShop().searchAuction(auction1.getName()).contains(auction1));
	}
	@Test (expected = NotFound.class)
	public void testSearchAuction2() throws Duplicated, NotFound {
		Shop.getShop().searchAuction("qwertyy");
	}

	@Test
	public void testLogin() throws NotFound, InvalidPassword, Duplicated {
		Shop.getShop().addUser(user2);
		assertTrue(Shop.getShop().login(user2.getUserName(), "dianampassword"));
		assertEquals(Shop.getShop().getUserLogin(),user2);
	}
	@Test
	public void testLogin2() throws NotFound, InvalidPassword, Duplicated {
		Shop.getShop().addUser(user2);
		assertFalse(Shop.getShop().login(user2.getUserName(), "fakepassword"));
		assertTrue(Shop.getShop().getUserLogin() == null);
	}

	@Test
	public void testLogout() throws Duplicated, NotFound, InvalidPassword {
		Shop.getShop().addUser(user2);
		assertTrue(Shop.getShop().login(user2.getUserName(), "dianampassword"));
		assertEquals(Shop.getShop().getUserLogin(),user2);
		Shop.getShop().logout();
		assertTrue(Shop.getShop().getUserLogin() == null);
	}

	@Test
	public void testEraseShop() throws Throwable {
		Shop.cleanShop();
		assertTrue(Shop.getShop().getAuctions().isEmpty());
		assertTrue(Shop.getShop().getCustomers().isEmpty());
		assertTrue(Shop.getShop().getSales().isEmpty());
		assertTrue(Shop.getShop().getStock().isEmpty());
		assertTrue(Shop.getShop().getUsers().isEmpty());
		assertTrue(Shop.getShop().getUserLogin() == null);
	}

}
