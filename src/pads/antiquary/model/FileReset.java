package pads.antiquary.model;

import java.io.IOException;
import java.time.LocalDate;

import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.item.*;
import pads.antiquary.model.misc.*;
import pads.antiquary.model.people.*;
import pads.antiquary.model.sales.*;

/**
 * Class with a main to restore the files using in the descriptor, when this is
 * executed the main function of this class is called. Provides an human
 * readable output with all the steps done
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 * 
 */
public class FileReset {

	/**
	 * Restore the files from test them Except the items file, that not change
	 * 
	 * @param args
	 *            The input arguments are ignored
	 * @throws Exception
	 *             in case of
	 */
	public static void main(String[] args) throws Exception {

		System.out.println(" Firstly we will load the file supplied with the items\n "
				+ "and create multiple users, customers, sales, lots and auctions\n");

		// Loads the items file
		try {
			Shop.getShop().loadStock(Settings.getDEFAULT_ITEM_FILE());
		} catch (IOException e) { // Error reading
			System.err.println("Check if the default item file 'items.csv' is in the correct folder 'files'\n" + e);
			throw new Exception();
		}

		System.out.println(" Items Loaded: \n ");
		for (Item i : Shop.getShop().getStock()) {
			System.out.println(i);
		}

		// Creates two new users and adds it to the shop
		User user1 = new Manager("PabloM", "Pablo", "Marcos", LocalDate.of(1996, 10, 2), "pab@foo.com", 676676676,
				Gender.MALE, "Madrid", "1234", true);
		User user2 = new Employee("DidiB", "Diana", "Rojas", LocalDate.of(1996, 12, 3), "di@foo.com", 666666666,
				Gender.FEMALE, "Madrid", "4321", true);

		Shop.getShop().addUser(user1);
		Shop.getShop().addUser(user2);

		System.out.println("\n New users added: \n");
		System.out.println(user1.toPrint());
		System.out.println(user2.toPrint());

		// Creates two new customers and adds it to the shop

		Customer customer1 = new Customer("Client1", "Millers", LocalDate.of(1990, 7, 1), "client1@foo.com", 654321987,
				Gender.MALE, "Albacete", Notificate.ALWAYS, ContractType.Standard);
		Customer customer2 = new Customer("Client2", "Perez", LocalDate.of(1990, 3, 5), "client2@foo.com", 654321999,
				Gender.FEMALE, "NY", Notificate.NEVER, ContractType.Vip);

		Shop.getShop().addCustomer(customer1);
		Shop.getShop().addCustomer(customer2);

		System.out.println("\n New customers added: \n");
		System.out.println(customer1);
		System.out.println(customer1);

		// Creates two new Sales

		// The first sale with one Item (Small)
		Sale sale1 = new Sale(customer1);

		sale1.addItem(Shop.getShop().searchExactItem("Glass bottle (19th century) Hand holding a colt"));
		sale1.purchase(); // Confirm the sale

		Shop.getShop().addSale(sale1);

		System.out.println("\n First sale added:\n" + sale1);
		System.out.println(
				"Now the amount spend by the client " + customer1.getName() + " is " + customer1.getAmountSpend());

		// The second one with two items(Bulky and WorkOfArt)

		Sale sale2 = new Sale(customer1);

		sale2.addItem(Shop.getShop().searchExactItem("Old polychromatic altar from Sonora (Arizona)")); // Bulky
																										// Item
		sale2.addItem(Shop.getShop().searchExactItem("Girl holding flowers (oil paint)")); // Bulky
																							// Item

		sale2.purchase(); // Confirm the sale

		Shop.getShop().addSale(sale2);

		System.out.println("\n Second sale added:\n" + sale2);
		System.out.println(
				"Now the amount spend by the client " + customer1.getName() + " is " + customer1.getAmountSpend());

		// Creates a lot
		Lot lot = new Lot();
		lot.addItem(Shop.getShop().searchExactItem("African ethnic Wooden mask"));

		System.out.println("\n New lot created: ");
		System.out.println(lot);

		// Creates an auction and a bid

		Auction auction = new Auction("ExampleAuction", "It's only an example", 100, 10, 1/* hour */, lot);

		Bid bid = new Bid(120.0, customer2);
		System.out.println("\n New bid created: ");
		System.out.println(bid);

		auction.entry(customer2); // The customer has to entry to bid
		auction.addBid(bid);
		Shop.getShop().addAuction(auction);

		System.out.println("\n New auction created and added, with one bid (the time has already begun): ");
		System.out.println(auction);

		System.out.println(" \n Writting all the objects created in their corresponding files, that can take a moment");

		try {
			Shop.save();
		} catch (IOException e2) {
			System.err.println("\n An error writing in the files happened\n" + e2.getStackTrace());
			throw new Exception();
		}
		System.out.println(" Done");

		System.out.println("\n Now that all the objects are write \n"
				+ "in their files,we will cancel the timers throwed to control the auction's time\n"
				+ "and finalize the shop top simulate the application close");

		try { // finalize the shop
			Shop.getShop().eraseShop();

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
