package pads.antiquary.model.misc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import pads.antiquary.model.exceptions.*;
import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.item.*;
import pads.antiquary.model.people.*;
import pads.antiquary.model.sales.*;

public class ShopFileIO {

	/** Private constructor of the class, to avoid to instance the class */
	private ShopFileIO() {
	}

	/* **********Write methods********** */

	/**
	 * Writes all the objects contained in a array list
	 * 
	 * @param array
	 *            Array woth the objects to be writed
	 * @param fileName
	 *            Name of the file
	 * @throws IOException
	 */
	private static void arrayWriter(ArrayList<?> array, String fileName) throws IOException {
		FileWriter writer = new FileWriter(new File(fileName));

		for (Object o : array) { // writes all the objects of the array
			writer.write(o + "\n");
		}
		writer.flush();
		writer.close();
	}

	/**
	 * Method to write in a file an String
	 * 
	 * @param toPrint
	 *            String to write
	 * @param fileName
	 *            Name of the file
	 * @throws IOException
	 */
	private static void writer(String toPrint, String fileName) throws IOException {
		FileWriter writer = new FileWriter(new File(fileName));
		writer.write(toPrint); // Writes the string
		writer.flush();
		writer.close();
	}

	/**
	 * Writes the items of the antiquary in a file in csv format
	 * 
	 * @param fileName
	 *            Name of the file
	 * @throws IOException
	 */
	public static void itemsWriter(String fileName) throws IOException {
		arrayWriter(Shop.getShop().getStock(), fileName);
	}

	/**
	 * Writes the users of the antiquary in a file in csv format
	 * 
	 * @param fileName
	 *            Name of the file
	 * @throws IOException
	 */
	public static void userWriter(String fileName) throws IOException {
		arrayWriter(Shop.getShop().getUsers(), fileName);
	}

	/**
	 * Writes the customers of the antiquary in a file in csv format
	 * 
	 * @param fileName
	 *            Name of the file
	 * @throws IOException
	 */
	public static void customerWriter(String fileName) throws IOException {
		arrayWriter(Shop.getShop().getCustomers(), fileName);
	}

	/**
	 * Writes the Auctions of the antiquary in a file in csv format
	 * 
	 * @param fileName
	 *            Name of the file
	 * @throws IOException
	 */
	public static void auctionWriter(String fileName) throws IOException {
		arrayWriter(Shop.getShop().getAuctions(), fileName);
	}

	/**
	 * Writes the sales of the antiquary in a file in csv format
	 * 
	 * @param fileName
	 *            Name of the file
	 * @throws IOException
	 */
	public static void saleWriter(String fileName) throws IOException {
		arrayWriter(Shop.getShop().getSales(), fileName);
	}

	/**
	 * Writes the settings of the antiquary in a file in csv format
	 * 
	 * @param fileName
	 *            Name of the file
	 * @throws IOException
	 */
	public static void settingsWriter(String fileName) throws IOException {
		writer(Settings.toPrint(), fileName);
	}

	/* **********Reader methods********** */

	/**
	 * Creates a new FileReader
	 * 
	 * @param fileName
	 *            name of the file to read
	 * @return a bufferedReader opened
	 * @throws FileNotFoundException
	 *             In case that the file is not found
	 */
	private static BufferedReader reader(String fileName) throws FileNotFoundException {
		return new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
	}

	/**
	 * Reads the items from a file
	 * 
	 * @param fileName
	 *            name of the file to read
	 * @return array with all the items
	 * @throws IOException
	 *             In case that the file is not found or read error
	 * @throws InvalidSplit
	 *             malformed line of the file
	 */
	public static ArrayList<Item> itemReader(String fileName) throws IOException, InvalidSplit {
		ArrayList<Item> array = new ArrayList<Item>();
		BufferedReader buffer = reader(fileName);
		String line;
		String[] itemSplit;
		Item item = null;

		while ((line = buffer.readLine()) != null) {
			itemSplit = line.split(";");

			if (itemSplit.length < 3)
				throw new InvalidSplit();

			else if (itemSplit[0].equals("S")) {
				if (itemSplit.length != 7)
					throw new InvalidSplit();
				item = new Small(itemSplit);

			} else if (itemSplit[0].equals("B")) {
				if (itemSplit.length != 10)
					throw new InvalidSplit();
				item = new Bulky(itemSplit);

			} else if (itemSplit[0].equals("A")) {
				if (itemSplit.length != 9)
					throw new InvalidSplit();
				item = new WorkOfArt(itemSplit);
			}

			if (!array.contains(item))
				array.add(item);
		}
		buffer.close();
		return array;
	}

	/**
	 * Reads the users from a file. That method can take a moment due to hash
	 * methods
	 * 
	 * @param fileName
	 *            name of the file to read
	 * @return array with all the users
	 * @throws IOException
	 *             In case that the file is not found or read error
	 * @throws InvalidSplit
	 *             malformed line of the file
	 * @throws Exception
	 *             in case of error hashing the user codes
	 */
	public static ArrayList<User> userReader(String fileName) throws IOException, InvalidSplit, Exception {
		ArrayList<User> array = new ArrayList<User>();
		BufferedReader buffer = reader(fileName);
		String line;
		String[] userSplit;
		User user = null;

		while ((line = buffer.readLine()) != null) {
			userSplit = line.split(";");

			if (userSplit.length != 10)
				throw new InvalidSplit();

			// Checks the type of user with the has of the employee/manager code
			else if (Password.check(Settings.EMPLOYEE_CODE + userSplit[7], userSplit[9])) {
				user = new Employee(userSplit);

			} else if (Password.check(Settings.MANAGER_CODE + userSplit[7], userSplit[9])) {
				user = new Manager(userSplit);

			} else { // User invalid codes
				throw new InvalidSplit();
			}

			if (!array.contains(user))
				array.add(user);
		}
		buffer.close();
		return array;
	}

	/**
	 * Reads the customers from a file
	 * 
	 * @param fileName
	 *            name of the file to read
	 * @return array with all the customers
	 * @throws IOException
	 *             In case that the file is not found or read error
	 * @throws InvalidSplit
	 *             malformed line of the file
	 */
	public static ArrayList<Customer> customerReader(String fileName) throws IOException, InvalidSplit {

		ArrayList<Customer> array = new ArrayList<Customer>();
		BufferedReader buffer = reader(fileName);
		String line;
		String[] custSplit;
		Customer customer = null;

		while ((line = buffer.readLine()) != null) {
			custSplit = line.split(";");

			if (custSplit.length != 14)
				throw new InvalidSplit();

			customer = new Customer(custSplit);

			if (!array.contains(customer))
				array.add(customer);
		}
		buffer.close();
		return array;
	}

	/**
	 * Reads the sales from a file
	 * 
	 * @param fileName
	 *            name of the file to read
	 * @return array with all the sales
	 * @throws IOException
	 *             In case that the file is not found or read error
	 * @throws InvalidSplit
	 *             malformed line of the file
	 * @throws NotFound
	 *             in case an item of a sale is not in the shop
	 */
	public static ArrayList<Sale> salesReader(String fileName) throws IOException, InvalidSplit, NotFound {

		ArrayList<Sale> array = new ArrayList<Sale>();
		BufferedReader buffer = reader(fileName);
		String line;
		String[] saleSplit;
		Sale sale = null;

		while ((line = buffer.readLine()) != null) {
			saleSplit = line.split(";"); // Parsing the line

			if (saleSplit.length != 7)
				throw new InvalidSplit();

			sale = new Sale(saleSplit);

			if (!array.contains(sale))
				array.add(sale);
		}
		buffer.close();
		return array;
	}

	/**
	 * Reads the items from a file
	 * 
	 * @param fileName
	 *            name of the file to read
	 * @return array with all the items
	 * @throws IOException
	 *             In case that the file is not found or read error
	 * @throws InvalidSplit
	 *             malformed line of the file
	 * @throws NotFound
	 *             in case an item of a sale is not in the shop
	 * @throws NotInAuction
	 * @throws InvalidBid
	 */
	public static ArrayList<Auction> auctionReader(String fileName)
			throws IOException, InvalidSplit, NotFound, InvalidBid, NotInAuction {

		ArrayList<Auction> array = new ArrayList<Auction>();
		BufferedReader buffer = reader(fileName);
		String line;
		String[] auctionSplit;
		Auction auction = null;

		while ((line = buffer.readLine()) != null) {
			auctionSplit = line.split(";"); // Parsing the line
			if (auctionSplit.length != 11)
				throw new InvalidSplit();

			// Parses the auction and throw the timer or close it
			auction = new Auction(auctionSplit);

			if (!array.contains(auction))
				array.add(auction);
		}
		buffer.close();

		return array;

	}

	public static void settingsLoader() throws IOException {
		BufferedReader buffer = reader(Settings.getDefaultSettingsFile());
		String line;

		for (int i = 0; (line = buffer.readLine()) != null; i++) {
			switch (i) {
			case 0:
				Settings.setMIN_SPEND(Integer.parseInt(line));
				break;
			case 1:
				Settings.setCOST_PER_KG(Double.parseDouble(line));
				break;
			case 2:
				Settings.setMAX_COST(Integer.parseInt(line));
				break;
			case 3:
				Settings.setMIN_COST(Integer.parseInt(line));
				break;
			case 4:
				Settings.setMIN_HEIGHT(Integer.parseInt(line));
				break;
			case 5:
				Settings.setMIN_WEIGHT(Integer.parseInt(line));
				break;
			case 6:
				Settings.setCONTRACT_DURATION(Integer.parseInt(line));
				break;
			case 7:
				Settings.setSTANDARD_FREE_ENTRYS(Integer.parseInt(line));
				break;
			case 8:
				Settings.setBID_SUBJECT(line);
				break;
			case 9:
				Settings.setBID_BODY(line);
				break;
			default:
				break;

			}
		}
	}

}
