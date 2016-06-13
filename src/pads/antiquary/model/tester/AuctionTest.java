package pads.antiquary.model.tester;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import pads.antiquary.model.exceptions.*;
import pads.antiquary.model.item.*;
import pads.antiquary.model.people.*;
import pads.antiquary.model.sales.*;

public class AuctionTest {

	private Auction auction1;
	private Auction auction2;
	private Item item1;
	private Auctionable item2;
	private Lot lot1;
	private Customer client1;
	private Bid bid;

	@Before
	public void newAuction() throws Exception {

		item1 = new Bulky("Four-seat wooden pew", "1916", LocalDate.of(2016, 1, 15), 75.00, 100.00, 32, 86, 175, 50);
		item2 = new WorkOfArt("Girl #1 in bronze", "19xx", LocalDate.of(2014, 8, 10), 200.00, 600.00, "French School",
				TypeOfWork.Sculpture, Authenticity.N);

		lot1 = new Lot();
		lot1.addItem(item1);
		client1 = new Customer(12, "James", "Fox", LocalDate.of(1995, 2, 24), "james@foo.com", 654321987, Gender.MALE,
				"Albacete", Notificate.ALWAYS, ContractType.Standard);

		auction1 = new Auction("Example1", "Its the first example", 100, 10, 1, lot1);
		auction2 = new Auction("Example2", "Its the second example", 200, 20, 1, item2);
		bid = new Bid(120.0, client1);
	}

	@Test
	public void testAuction1() {

		assertEquals("Example1", auction1.getName());
		assertEquals("Its the first example", auction1.getDescription());
		assertEquals(Integer.valueOf(100), Integer.valueOf(auction1.getTargetPrice()));
		assertEquals(Integer.valueOf(10), Integer.valueOf(auction1.getEntryPrice()));
		assertEquals(Integer.valueOf(1), Integer.valueOf(auction1.getDuration()));
		assertTrue(auction1.getItem().getItems().contains(item1));
	}

	@Test(expected = NotFound.class)
	public void testAuction2() throws NotFound, InvalidBid, NotInAuction {

		new Auction(auction1.toString().split(";"));
	}

	@Test(expected = InvalidBid.class)
	public void testAuction4() throws NotFound, InvalidBid, NotInAuction, Duplicated {

		auction1.entry(client1);
		auction1.addBid(new Bid(Double.valueOf(2),client1));

	}

	@Test(expected = NotInAuction.class)
	public void testAuction5() throws NotFound, InvalidBid, NotInAuction {
		auction1.addBid(bid);
	}

	@Test
	public void addBid() throws NotFound, InvalidBid, NotInAuction, Duplicated {
		auction1.entry(client1);
		auction1.addBid(bid);
		auction1.getTimer().getTimer().cancel();
	}
	

	@Test
	public void addentry() throws NotFound, InvalidBid, NotInAuction, Duplicated {
		auction1.entry(client1);
		auction1.getBidder().contains(client1);
	}

	@Test(expected = Duplicated.class)
	public void addentry2() throws NotFound, InvalidBid, NotInAuction, Duplicated {
		auction1.entry(client1);
		auction1.entry(client1);
	}

	@Test
	public void testEqualsObject() {
		assertEquals(false, auction1.equals(auction2));
		assertEquals(false, auction1.equals(null));
		assertEquals(false, auction1.equals(""));
	}

}
