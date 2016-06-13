package pads.antiquary.model.tester;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.item.*;
import pads.antiquary.model.people.*;
import pads.antiquary.model.sales.*;

public class AuctionTimerTest {

	private Auction auction;
	private Lot lot;
	private Customer client;

	@Before
	public void newAuction() throws Exception {

		lot = new Lot();
		lot.addItem(new Bulky("Four-seat wooden pew", "1916", LocalDate.of(
				2016, 1, 15), 75.00, 100.00, 32, 86, 175, 50));
		client = new Customer(12, "James", "Fox", LocalDate.of(1995, 2, 24),
				"james@foo.com", 654321987, Gender.MALE, "Albacete",
				Notificate.ALWAYS, ContractType.Standard);
		auction = new Auction("Example1", "Its the first example", 100, 10, 1,
				lot);
		Shop.getShop().addCustomer(client);
		auction.entry(client);
		auction.addBid(new Bid(120.0, client));

	}

	@Test
	public void testAuctionTimer1() throws InterruptedException {

		AuctionTimer timer = new AuctionTimer(auction, 1L, true);
		auction.setTimer(timer);

		Thread.sleep(2000);
		assertFalse(auction.isActive());
	}

	@Test
	public void testAuctionTimer2() {
		AuctionTimer timer = new AuctionTimer(auction, 1L, true);
		auction.setTimer(timer);
		assertTrue(auction.isActive());
	}

	@Test
	public void testAuctionTimer3() throws InterruptedException {
		AuctionTimer timer = new AuctionTimer(auction, 1L, true);
		auction.setTimer(timer);
		auction.getTimer().getTimer().cancel();
		Thread.sleep(1500);
		assertTrue(auction.isActive());
	}

	@After
	public void erase() {
		Shop.cleanShop();
	}
}
