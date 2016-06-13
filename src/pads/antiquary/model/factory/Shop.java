package pads.antiquary.model.factory;

import java.io.IOException;
import java.util.ArrayList;

import pads.antiquary.model.exceptions.*;
import pads.antiquary.model.item.*;
import pads.antiquary.model.misc.*;
import pads.antiquary.model.people.*;
import pads.antiquary.model.sales.*;

/**
 * Factory of the application Stores all the data and contains the main methods
 * 
 * @author pablo
 * 
 */
public class Shop {
	/** Array with all the items */
	private ArrayList<Item> stock;
	/** Array with all the users */
	private ArrayList<User> users;
	/** Array with all the customers */
	private ArrayList<Customer> customers;
	/** Array with all the sales */
	private ArrayList<Sale> sales;
	/** Array with all the auctions */
	private ArrayList<Auction> auctions;
	/** the user login */
	private User userLogin;
	/** The class itself */
	private static Shop instance = null;

	private Shop() {
		stock = new ArrayList<Item>();
		users = new ArrayList<User>();
		customers = new ArrayList<Customer>();
		sales = new ArrayList<Sale>();
		auctions = new ArrayList<Auction>();
		userLogin = null;
	}

	/**
	 * Get the Instance of the class, according to the singleton pattern
	 * 
	 * @return the shop
	 */
	public static Shop getShop() {
		if (Shop.instance == null)
			Shop.instance = new Shop();
		return Shop.instance;
	}

	/** cleas the shop */
	public static void cleanShop() {
		Shop.instance = new Shop();
	}

	/**
	 * Adds an item
	 * 
	 * @param item
	 *            to add
	 * @throws Duplicated
	 *             if it's duplicated
	 */
	public void addItem(Item item) throws Duplicated {
		if (this.stock.contains(item))
			throw new Duplicated();
		this.stock.add(item);
	}

	/**
	 * Add an user
	 * 
	 * @param user
	 *            to be add
	 * @throws Duplicated
	 */
	public void addUser(User user) throws Duplicated {
		if (this.users.contains(user))
			throw new Duplicated();
		this.users.add(user);
	}

	/**
	 * Add a customer
	 * 
	 * @param customer
	 *            to be add
	 * @throws Duplicated
	 */
	public void addCustomer(Customer customer) throws Duplicated {
		if (this.customers.contains(customer))
			throw new Duplicated();
		this.customers.add(customer);
	}

	/**
	 * Add a sale
	 * 
	 * @param sale
	 *            to be add
	 * @throws Duplicated
	 */
	public void addSale(Sale sale) throws Duplicated {
		if (this.sales.contains(sale))
			throw new Duplicated();
		this.sales.add(sale);
	}

	/**
	 * Add an auction
	 * 
	 * @param auction
	 *            to be add
	 * @throws Duplicated
	 */
	public void addAuction(Auction auction) throws Duplicated {
		if (this.auctions.contains(auction))
			throw new Duplicated();
		this.auctions.add(auction);
	}

	/**
	 * 
	 * @return the items
	 */
	public ArrayList<Item> getStock() {
		return stock;
	}

	/** @return the users */
	public ArrayList<User> getUsers() {
		return users;
	}

	/** @return the customers */
	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	/**
	 * 
	 * @return the sales
	 */
	public ArrayList<Sale> getSales() {
		return sales;
	}

	/**
	 * 
	 * @return the auctions
	 */
	public ArrayList<Auction> getAuctions() {
		return auctions;
	}

	/**
	 * 
	 * @return the user login
	 */
	public User getUserLogin() {
		return userLogin;
	}

	/* Search methods */

	/**
	 * Search Users of the shop by username
	 * 
	 * @param username
	 *            to search
	 * @return the user searched
	 * @throws NotFound
	 *             if there aren't coincidences
	 */
	public User searchUser(String username) throws NotFound {
		for (User u : this.users) {
			if (u.getUserName().equals(username))
				return u;
		}
		throw new NotFound();
	}

	/**
	 * Search Users of the shop by coincidence
	 * 
	 * @param name
	 *            String to search
	 * @return the user searched
	 * @throws NotFound
	 *             if there aren't coincidences
	 */
	public ArrayList<Customer> searchCustomer(String name) throws NotFound {
		ArrayList<Customer> match = new ArrayList<Customer>();
		for (Customer c : this.customers) {
			if (c.getSurname().toLowerCase().contains(name.toLowerCase())
					|| c.getName().toLowerCase().contains(name.toLowerCase())
					|| c.getAdress().toLowerCase().contains(name.toLowerCase())
					|| c.getAdress().toLowerCase().contains(name.toLowerCase())
					|| Integer.valueOf(c.getPhone()).toString().toLowerCase().contains(name.toLowerCase()))
				match.add(c);
		}
		if (match.isEmpty())
			throw new NotFound();
		return match;
	}

	/**
	 * Search Customers of the shop by Id
	 * 
	 * @param ID
	 *            to search
	 * @return the customer searched
	 * @throws NotFound
	 *             if there aren't coincidences
	 */
	public Customer searchCustomerbyID(String ID) throws NotFound {
		for (Customer c : this.customers) {
			if (Integer.valueOf(c.getCustomerID()).equals(Integer.valueOf(ID)))
				return c;
		}
		throw new NotFound();
	}

	/**
	 * Search Items in the antiquary giving a string
	 * 
	 * @param str
	 *            String to search
	 * @return the items searched
	 * @throws NotFound
	 *             if there aren't coincidences
	 */
	public ArrayList<Item> searchItemByStr(String str) throws NotFound {
		ArrayList<Item> match = new ArrayList<Item>();
		for (Item i : this.stock) {
			if (i.getDescription().toLowerCase().contains(str.toLowerCase()))
				match.add(i);
		}
		if (match.isEmpty())
			throw new NotFound();
		return match;
	}

	/**
	 * Search Item in the antiquary giving a string
	 * 
	 * @param str
	 *            Exact String to search
	 * @return the items searched
	 * @throws NotFound
	 *             if there aren't coincidences
	 */
	public Item searchExactItem(String str) throws NotFound {
		for (Item i : this.stock) {
			if (i.getDescription().equals(str))
				return i;
		}
		throw new NotFound();
	}

	/**
	 * Search Items in the antiquary giving a string
	 * 
	 * @param str
	 *            String with the year
	 * @return the items searched
	 * @throws NotFound
	 *             if there aren't coincidences
	 */
	public ArrayList<Item> searchItemByDate(String str) throws NotFound {
		ArrayList<Item> match = new ArrayList<Item>();
		
		if(str== null || str.equals(""))
			return this.getStock();
		
		try {
		Integer.parseInt(str.toLowerCase());
		} catch (NumberFormatException e){
			throw new NotFound();
		}
		
		for (Item i : this.stock) {
			if (Integer.valueOf(i.getManufactureYear().toString().replace("x", "0"))
					.compareTo(Integer.parseInt(str)) <= 0
					&& Integer.valueOf(i.getManufactureYear().toString().replace("x", "9"))
							.compareTo(Integer.parseInt(str)) >= 0)
				match.add(i);
		}
		if (match.isEmpty())
			throw new NotFound();
		return match;
	}

	/**
	 * Search Auctions in the antiquary giving a string
	 * 
	 * @param str
	 *            String to search
	 * @return the auctions searched
	 * @throws NotFound
	 *             if there aren't coincidences
	 */
	public ArrayList<Auction> searchAuction(String str) throws NotFound {
		ArrayList<Auction> match = new ArrayList<Auction>();
		for (Auction a : this.auctions) {
			if (a.getName().toLowerCase().contains(str.toLowerCase())
					|| a.getDescription().toLowerCase().contains(str.toLowerCase()))
				match.add(a);
		}
		if (match.isEmpty())
			throw new NotFound();
		return match;
	}

	public boolean login(String username, String password) throws NotFound, InvalidPassword {
		User user = this.searchUser(username);
		try {
			if (Password.check(password, user.getPassword())) {
				this.userLogin = user;
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new InvalidPassword();
		}
	}

	/**
	 * Clean the user login
	 */
	public void logout() {
		this.userLogin = null;
	}

	/**
	 * Loads the stock frop the file
	 * 
	 * @param fileName
	 * @throws IOException
	 * @throws InvalidSplit
	 */
	public void loadStock(String fileName) throws IOException, InvalidSplit {
		this.stock = ShopFileIO.itemReader(fileName);
	}

	/**
	 * Loads the users
	 * 
	 * @param fileName
	 * @throws Exception
	 */
	public void loadUsers(String fileName) throws Exception {
		this.users = ShopFileIO.userReader(fileName);
	}

	/**
	 * Loads the customers
	 * 
	 * @param fileName
	 * @throws Exception
	 */
	public void loadCustomers(String fileName) throws Exception {
		this.customers = ShopFileIO.customerReader(fileName);
	}

	/**
	 * Loads the sales
	 * 
	 * @param fileName
	 * @throws Exception
	 */
	public void loadSales(String fileName) throws Exception {
		this.sales = ShopFileIO.salesReader(fileName);
	}

	/**
	 * Loads the auctions
	 * 
	 * @param fileName
	 * @throws Exception
	 */
	public void loadAuctions(String fileName) throws Exception {
		this.auctions = ShopFileIO.auctionReader(fileName);
	}

	/**
	 * Loads all the objects
	 * 
	 * @throws Exception
	 */
	public void loadFiles() throws Exception {
		ShopFileIO.settingsLoader();
		loadStock(Settings.getDEFAULT_ITEM_FILE());
		loadUsers(Settings.getDEFAULT_USER_FILE());
		loadCustomers(Settings.getDEFAULT_CUSTOMER_FILE());
		loadSales(Settings.getDEFAULT_SALE_FILE());
		loadAuctions(Settings.getDEFAULT_AUCTION_FILE());
	}

	/**
	 * Erases the shop(used in testing) to simulate the close of the application
	 * 
	 * @throws Throwable
	 */
	public void eraseShop() throws Throwable {
		// Cancell all the timers
		for (Auction a : this.auctions) {
			if (a.getTimer() != null)
				a.getTimer().getTimer().cancel();
		}
		Shop.cleanShop();
	}

	/**
	 * Writes the objects in the files
	 * 
	 * @throws IOException
	 */
	public static void save() throws IOException {

		ShopFileIO.settingsWriter(Settings.getDefaultSettingsFile());
		ShopFileIO.auctionWriter(Settings.getDEFAULT_AUCTION_FILE());
		ShopFileIO.customerWriter(Settings.getDEFAULT_CUSTOMER_FILE());
		ShopFileIO.saleWriter(Settings.getDEFAULT_SALE_FILE());
		ShopFileIO.userWriter(Settings.getDEFAULT_USER_FILE());

		/* The Items file is not modificated by this application */
		// ShopFileIO.itemsWriter(Settings.DEFAULT_ITEM_FILE);
	}
}
