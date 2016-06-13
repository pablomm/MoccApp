package pads.antiquary.model.tester;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pads.antiquary.model.exceptions.*;
import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.item.*;
import pads.antiquary.model.people.*;
import pads.antiquary.model.sales.*;

public class BidTest {

	private Auction auction1;
	private Item item1;
	private Lot lot1;
	private Customer client1;
	private Bid bid1;

	

	@Before
	public void newBid() throws Exception {

		item1 = new Bulky("Four-seat wooden pew;", "1916", LocalDate.of(2016, 1, 15), 75.00, 100.00, 32, 86, 175, 50);
		item1.setSold(false);
		lot1 = new Lot();
		lot1.addItem(item1);
		client1 = new Customer(12, "James", "Fox", LocalDate.of(1995, 2, 24), "james@foo.com", 654321987, Gender.MALE,
				"Albacete", Notificate.ALWAYS, ContractType.Standard);

		auction1 = new Auction("Example1", "Its the first example", 100, 10, 1, lot1);
		
		bid1 = new Bid(120.0, client1, LocalDateTime.now());
		auction1.entry(client1);
		Shop.getShop().addCustomer(client1);
	}

	@Test
	public void testBid1() throws InvalidBid, NotInAuction {
		auction1.addBid(bid1);
		auction1.getBid().contains(bid1);
		assertEquals(Double.valueOf(120.0),bid1.getPrice());
		assertEquals(bid1.getCustomer(),client1);
	}

	@Test(expected = NotFound.class)
	public void testBid2() throws NotFound {
		Shop.cleanShop();
		 new Bid(bid1.toString().split("#"));
	}

	@Test
	public void testEqualsObject() throws NotFound {

		Bid bidStr = new Bid(bid1.toString().split("#"));

		assertTrue(bid1.equals(bidStr));
		assertFalse(bid1.equals(null));
		assertTrue(bid1.getCustomer().equals(bidStr.getCustomer()));
		assertTrue(bid1.getDate().equals(bidStr.getDate()));
		assertTrue(bid1.getPrice().equals(bidStr.getPrice()));
	}
	
	@After
	public void erase(){
		Shop.cleanShop();
	}

}
