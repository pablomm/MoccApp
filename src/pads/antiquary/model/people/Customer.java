package pads.antiquary.model.people;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import pads.antiquary.model.item.*;
import pads.antiquary.model.sales.Contract;
import pads.antiquary.model.sales.ContractType;

/**
 * Class to manage the customers data
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas
 * 
 */
public class Customer extends Person {

	/** Id asigned to the customer */
	private Integer customerID;

	/** Highest Id used */
	private static Integer topCustomerID = 0;

	/** Spent in the antiquary by the customer */
	private Double amountSpend;

	/** Type of notifications in the auctions */
	private Notificate notification;

	/** Number of auctions ingresed */
	private Integer numberAuctions;

	/** Array with all the items bought */
	private ArrayList<Item> item;

	/** Shop contract */
	private Contract contract;

	/**
	 * Constructor of the class
	 * 
	 * @param name
	 * @param surname
	 * @param birthDay
	 * @param email
	 * @param phone
	 * @param gender
	 * @param adress
	 * @param notification
	 * @param contract
	 */
	public Customer(String name, String surname, LocalDate birthDay, String email, Integer phone, Gender gender,
			String adress, Notificate notification, ContractType contract) {
		super(name, surname, birthDay, email, phone, gender, adress);

		this.customerID = Customer.generateID();
		this.amountSpend = 0.00;
		this.notification = notification;
		this.numberAuctions = 0;
		this.contract = new Contract(contract);
		this.item = new ArrayList<Item>();

	}

	/**
	 * Alernative constructor, that set the id
	 * 
	 * @param customerId
	 * @param name
	 * @param surname
	 * @param birthDay
	 * @param email
	 * @param phone
	 * @param gender
	 * @param adress
	 * @param notification
	 * @param contract
	 */
	public Customer(int customerId, String name, String surname, LocalDate birthDay, String email, int phone,
			Gender gender, String adress, Notificate notification, ContractType contract) {
		super(name, surname, birthDay, email, phone, gender, adress);

		this.customerID = customerId;
		this.amountSpend = 0.00;
		this.notification = notification;
		this.numberAuctions = 0;
		this.contract = new Contract(contract);
		this.checkCustomerId(customerId);
		this.item = new ArrayList<Item>();

	}

	/**
	 * Parses the customer from a array string
	 * 
	 * @param custSplit
	 */
	public Customer(String[] custSplit) {
		this(Integer.parseInt(custSplit[7]), custSplit[0], custSplit[1],
				LocalDate.parse(custSplit[2], DateTimeFormatter.ofPattern("d/L/yyyy")), custSplit[3],
				Integer.parseInt(custSplit[4]), Gender.valueOf(custSplit[5]), custSplit[6],
				Notificate.valueOf(custSplit[9]), ContractType.valueOf(custSplit[12]));
		this.contract = new Contract(ContractType.valueOf(custSplit[12]),
				LocalDate.parse(custSplit[13], DateTimeFormatter.ofPattern("d/L/yyyy")));
		this.amountSpend = Double.parseDouble(custSplit[8]);
		this.amountSpend = Double.valueOf(0);
		this.numberAuctions = Integer.parseInt(custSplit[10]);

	}

	public int getCustomerID() {
		return customerID;
	}

	/**
	 * @return the amountSpend
	 */
	public Double getAmountSpend() {
		return amountSpend;
	}

	/**
	 * @param amountSpend
	 *            the amountSpend to set
	 */
	public void setAmountSpend(Double amountSpend) {
		this.amountSpend = amountSpend;
	}

	/**
	 * @return the notification
	 */
	public Notificate getNotification() {
		return notification;
	}

	/**
	 * @param notification
	 *            the notification to set
	 */
	public void setNotification(Notificate notification) {
		this.notification = notification;
	}

	/**
	 * @return the numberAuctions
	 */
	public Integer getNumberAuctions() {
		return numberAuctions;
	}

	/**
	 * @param numberAuctions
	 *            the numberAuctions to set
	 */
	public void setNumberAuctions(Integer numberAuctions) {
		this.numberAuctions = numberAuctions;
	}

	/**
	 * @return the item
	 */
	public ArrayList<Item> getItem() {
		return item;
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(ArrayList<Item> item) {
		this.item = item;
	}

	/**
	 * @return the contract
	 */
	public Contract getContract() {
		return contract;
	}

	/**
	 * @param contract
	 *            the contract to set
	 */
	public void setContract(Contract contract) {
		this.contract = contract;
	}

	/**
	 * @return the topCustomerID
	 */
	public static Integer getTopCustomerID() {
		return topCustomerID;
	}

	/**
	 * @param customerID
	 *            the customerID to set
	 */
	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}

	/**
	 * Checks the topId
	 * 
	 * @param customerId
	 */
	private void checkCustomerId(int customerId) {
		if (customerId > Customer.topCustomerID)
			Customer.topCustomerID = customerId;
	}

	/**
	 * Generates a new id
	 * 
	 * @return new customer id
	 */
	private static int generateID() {
		return Customer.topCustomerID+=10;

	}

	/**
	 * Increases the auctions
	 */
	public void increaseNumberAuctions() {
		this.numberAuctions++;
	}

	/**
	 * Increases the spend
	 * 
	 * @param finalPrice
	 */
	public void increaseSpend(Double finalPrice) {
		this.amountSpend += finalPrice;
	}

	/**
	 * Adds an item
	 * 
	 * @param item
	 */
	public void addItem(Item item) {
		this.item.add(item);
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		return this.customerID.equals(((Customer) obj).customerID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String ret = super.toString() + ";" + customerID + ";" + amountSpend + ";" + notification + ";" + numberAuctions
				+ ";";
		// Printing the items buyed
		for (Item i : this.item) {
			ret += i.getDescription() + "#";
		}

		ret += ";" + contract;
		return ret;
	}

}
