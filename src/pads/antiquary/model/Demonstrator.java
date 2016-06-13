package pads.antiquary.model;

import java.time.LocalDate;

import pads.antiquary.model.exceptions.*;
import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.item.*;
import pads.antiquary.model.misc.ReportSales;
import pads.antiquary.model.people.*;
import pads.antiquary.model.sales.*;

/**
 * Class with an example of the funcionality of the application Provides an
 * human readable output with all the steps done Firtsly it restarts the files
 * calling the FileReset main
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */

public class Demonstrator {

	/**
	 * Main of the descriptor, executes an example of the funcionality of the
	 * application
	 * 
	 * @param args
	 *            Input args are ignored
	 * @throws Throwable
	 */
	public static void main(String[] args) throws Throwable {

		System.out.println(" *** MoccApp descriptor ***");

		System.out.println("\n Restoring files before execute the descriptor...");
		System.out.println(" That can take a moment, a lot of objects have to be created and"
				+ " writes in files \n(1-2 seconds ~ 1-2 minutes)\n");
		System.out.println("Please, be pacient ");

		try {// That creates new objects and write them in the files

			FileReset.main(null);

		} catch (Exception e) {
			throw new Exception();
		}
		System.out.println("Done!");
		System.out.println("\n *** FUNCIONALITY EXAMPLE ***");

		System.out.println("\n Now the shop are clean, are the same circumstances that when the application is opened");

		System.out.println("\n *** LOAD ***");

		System.out.println("\nLoading shop objects from the defaults files...");
		System.out.println("That cant take several seconds due to the input/output and users password hash methods");
		Shop.getShop().loadFiles();

		try {
			Shop.getShop().loadFiles();

		} catch (Exception e) {
			System.out.println("Check that the all the files are in the default folder (/files)");
			throw new Exception();
		}

		System.out.println("Done");

		/* ********Printing loaded object******** */

		System.out.println("\n Printing items loaded: \n");
		for (Item i : Shop.getShop().getStock()) {
			System.out.println(i);
		}

		System.out.println("\n Printing users loaded: \n");
		for (User u : Shop.getShop().getUsers()) {
			System.out.println(u.toPrint());
		}

		System.out.println("\n Printing Customers loaded: \n");
		for (Customer c : Shop.getShop().getCustomers()) {
			System.out.println(c);
		}

		System.out.println("\n Printing Sales loaded: \n");
		for (Sale s : Shop.getShop().getSales()) {
			System.out.println(s);
		}

		System.out.println("\n Printing Auctions loaded: \n");
		for (Auction a : Shop.getShop().getAuctions()) {
			System.out.println(a);
		}

		System.out.println("\n *** LOGIN *** \n");

		System.out.println("Password are not stored in plaintext, for instance " + "the password 1234 is stored like\n"
				+ Shop.getShop().searchUser("PabloM").getPassword()
				+ "\n(Due to the salt the hash is different from equal passwords)");

		System.out.println("\nIf we introduce an incorrect password the Login Method returns: "
				+ Shop.getShop().login("PabloM", "IncorrectPassword"));
		System.out.println("And the user login is: " + Shop.getShop().getUserLogin());

		System.out.println(
				"\nBut if we introduce the correct password then returns: " + Shop.getShop().login("PabloM", "1234"));
		System.out.println("Now the user login is: " + Shop.getShop().getUserLogin().getUserName());

		System.out.println("\n *** SEARCHS *** \n");

		System.out.println("We can realize several types of searchs,for instance search an item by year (1916)");
		System.out.println("Result:\n");
		for (Item searchItem : Shop.getShop().searchItemByDate("1916"))
			System.out.println(searchItem.getManufactureYear() + " - " + searchItem.getDescription());

		System.out.println("\nOr by a given word, for example 'girl'");
		System.out.println("Result:\n");
		for (Item searchItem : Shop.getShop().searchItemByStr("girl"))
			System.out.println(searchItem.getManufactureYear() + " - " + searchItem.getDescription());

		System.out.println("\nWe can search an user too, for example 'DidiB'");
		System.out.println("Result:\n");
		User searchUser = Shop.getShop().searchUser("DidiB");
		System.out.println(searchUser.getUserName() + " - " + searchUser.getName() + " " + searchUser.getSurname()
				+ " - " + searchUser.getEmail() + " - " + searchUser.getAdress());

		System.out.println("\nOr a customer, for example, the customer with id '1'");
		System.out.println("Result:\n");
		Customer searchCustomer = Shop.getShop().searchCustomerbyID("1");
		System.out.println(searchCustomer.getCustomerID() + " - " + searchCustomer.getName() + " "
				+ searchCustomer.getSurname() + " - " + searchCustomer.getEmail() + " - " + searchCustomer.getAdress());

		System.out.println("\nOr by name (Client)");
		System.out.println("Result:\n");

		for (Customer searchCustomers : Shop.getShop().searchCustomer("Client"))
			System.out.println(searchCustomers.getCustomerID() + " - " + searchCustomers.getName() + " "
					+ searchCustomers.getSurname() + " - " + searchCustomers.getEmail() + " - "
					+ searchCustomers.getAdress());

		System.out.println("\n *** SALES *** \n");

		System.out.println("We can create a sale if we are employee, for example for the client : " + searchCustomer.getName());
		System.out.println("The money already spent in the shop is : " + searchCustomer.getAmountSpend());

		Sale sale = new Sale(searchCustomer);

		System.out.println("\nFor example, we will add some items: \n Girl #1 in bronze");
		sale.addItem(Shop.getShop().searchExactItem("Girl #1 in bronze"));
		System.out.println(" Girl #2 in bronze");
		sale.addItem(Shop.getShop().searchExactItem("Stand in mahogany "));

		System.out.println("\nIf we try to add an object that is sold yet");
		try {
			sale.addItem(Shop.getShop().searchExactItem("Glass bottle (19th century) Hand holding a colt"));
		} catch (InvalidSale s) {
			System.out.println("The shop don't let us add the item");
		}

		System.out.println("\nOr we try to add an object that is in an auction");
		try {
			sale.addItem(Shop.getShop().searchExactItem("African ethnic Wooden mask"));
		} catch (InvalidSale s) {
			System.out.println("The shop don't let us add the item");
		}
		System.out.println("\nAnd  if we try to add many times the same item");
		try {
			sale.addItem(Shop.getShop().searchExactItem("African ethnic Wooden mask"));
		} catch (InvalidSale s) {
			System.out.println("We can't do it");
		}
		System.out.println("\n The price of the sale is: " + sale.getPrice());
		System.out.println(" The delivery cost of the sale is: " + sale.getDeliveryCost());
		System.out.println(" The discount of the sale is: " + sale.getDiscount());
		System.out.println(" The final price of the sale is: " + sale.getFinalPrice());

		System.out.println("\n When we confirm the purchase the items are added to the customer and"
				+ "the amount spent is increased");
		sale.purchase();

		System.out.println(" Now the amount spent is : " + searchCustomer.getAmountSpend());
		System.out.println(" And the items are in his inventory");
		System.out.println(
				" And the sold state of the items are: " + Shop.getShop().searchExactItem("Girl #1 in bronze").isSold()
						+ " " + Shop.getShop().searchExactItem("Stand in mahogany ").isSold());

		
		
		System.out.println("\n *** LOTS *** \n");

		System.out.println(" \nWe can create lots to pack items and auction them, if we are manager ");
		Lot lot = new Lot();

		System.out.println(" \nFor example, we will add two items to the lot, a bulky and a work of art");

		lot.addItem(Shop.getShop().searchExactItem("Four-seat wooden pew"));
		lot.addItem(Shop.getShop().searchExactItem("Mater Amabilis Ora Pro Novis"));

		System.out.println("The application prevents to add the same item many times");

		System.out.println("\n *** AUCTIONS/BIDS *** \n");

		System.out.println("We can create an auction with a lot and add it to the shop if we are manager");
		Auction auction = new Auction("Auction with the lot", "It's only an example", 200, 20, 1, lot);
		Shop.getShop().addAuction(auction);

		try {
			Shop.getShop().addAuction(auction);
		} catch (Duplicated e) {
			System.out.println("We can't add the same auction several times to the shop\n");
		}

		Auctionable woa = (WorkOfArt) Shop.getShop().searchExactItem("Girl #3 in bronze");
		System.out.println("We can auction a work of art directly");
		Auction auction2 = new Auction("Another auction with WoA", "It's only an example", 1000, 10, 1, woa);
		Shop.getShop().addAuction(auction2);

		System.out.println("Auction name: " + auction.getName());
		System.out.println("Auction entry: " + auction.getEntryPrice());
		System.out.println("Auction targetPrice: " + auction.getTargetPrice());
		System.out.println("Auction Items: ");
		for (Item i : auction.getItem().getItems())
			System.out.println("  " + i.getDescription());

		System.out.println("\nAuction name: " + auction2.getName());
		System.out.println("Auction entry: " + auction2.getEntryPrice());
		System.out.println("Auction targetPrice: " + auction2.getTargetPrice());
		System.out.println("Auction Items: ");
		for (Item i : auction2.getItem().getItems())
			System.out.println("  " + i.getDescription());

		System.out.println("\nThe auction time will start when the first bid it's done");
		System.out.println("\nSo an employee can create a bid");
		Customer bidder = Shop.getShop().searchCustomerbyID("1");
		Bid bid = new Bid(100.00, bidder);

		System.out.println("\nBid information");

		System.out.println("Bidder name: " + bid.getCustomer().getName());
		System.out.println("Bid price: " + bid.getPrice());
		System.out.println("Bid date: " + bid.getDate());

		try {// Try to add a bid
			auction.addBid(bid);
		} catch (NotInAuction e) {
			System.out.println("\nThe system don let add the bid, cause" + e);
		}

		System.out.println("The number of auction entrys of the customer is :" + bidder.getNumberAuctions());
		System.out.println("\nAdding user to the auction");
		System.out.println("The customer is in the auction, have to pay: " + auction.entry(bidder)
				+ " because his contract is of type " + bidder.getContract());

		try {
			auction.addBid(bid);
		} catch (InvalidBid e) {
			System.out.println("\nThe bid is not enought expensive, we will create another one with a price of 1200");
		}
		auction.addBid(new Bid(1200.0, bidder));

		System.out.println("Now the number of auction entrys of the customer is :" + bidder.getNumberAuctions());
		System.out.println("\nWith that bid a timer was thrown to close the auction when the time is up ");
		System.out.println("\nThe amount spent for the customer is: " + bidder.getAmountSpend());
		System.out.println("We will finish the auction manually to avoid wait 1 hour");
		auction.finish();

		System.out.println(
				"\nNow, notifications to all the bidders of the with the notificate atribute active have been sent, and the timer was cancelled");
		System.out.println("\nNow the amount spent for the customer is: " + bidder.getAmountSpend());

		System.out.println("\n *** CUSTOMER *** \n");

		System.out.println("Employees can add customer to the shop");
		Customer customer2 = new Customer(2, "Pepita", "Grillo", LocalDate.of(1989, 2, 1), "a@a.com", 0, Gender.MALE,
				"Spain", Notificate.NEVER, ContractType.None);
		Shop.getShop().addCustomer(customer2);

		try {
			Shop.getShop().addCustomer(customer2);
		} catch (Duplicated e) {
			System.out.println("\nBut they will have an error if the customer is already in the shop");
		}

		System.out.println("\n *** SALE REPORTS *** \n");

		System.out.println(" System provides information about the total sales and auctions");
		System.out.println(" \nStore stadistic between 2/10/2010 and 2/10/2020\n");
		System.out.println(" Store revenue : " + ReportSales.ingresedIn("2/10/2010", "2/10/2020"));
		System.out.println(" Adquisition costs : " + ReportSales.adquisitionCost("2/10/2010", "2/10/2020"));
		System.out.println(" Benefits : " + ReportSales.benefits("2/10/2010", "2/10/2020"));

		System.out.println(" Sales: ");
		for (Sale s : ReportSales.salesInDate("2/10/2010", "2/10/2020")) {
			System.out.println("  " + s.getFinalPrice() + " " + s.getCustomer().getName());
		}

		System.out.println("\n To finalize we will cancel all the timer and closes "
				+ "the shop to simulate the closes of the app");
		Shop.getShop().eraseShop();
	}

}
