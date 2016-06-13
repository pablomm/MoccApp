package pads.antiquary.model.sales;

import java.time.LocalDateTime;

import pads.antiquary.model.exceptions.NotFound;
import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.people.*;

public class Bid {
	/** Price of the bid */
	private Double price;
	/** Date of the bid */
	private LocalDateTime date;
	/** Bidder */
	private Customer customer;

	/**
	 * Constructor of the bid
	 * 
	 * @param price
	 *            Price of the bid
	 * @param customer
	 *            Bidder
	 * @param date
	 *            Date of the bid
	 */
	public Bid(Double price, Customer customer, LocalDateTime date) {
		super();
		this.price = price;
		this.date = date;
		this.customer = customer;
	}

	/**
	 * Alternative constructor without date
	 * 
	 * @param price
	 *            Price of the bid
	 * @param customer
	 *            Bidder
	 */
	public Bid(Double price, Customer customer) {
		this(price, customer, LocalDateTime.now());
	}

	/**
	 * Alternative constructor that parses the bid from a string array
	 * 
	 * @param bidSplit
	 * @throws NotFound
	 *             In case the customer is no found
	 */
	public Bid(String[] bidSplit) throws NotFound {
		this.price = Double.parseDouble(bidSplit[0]);
		this.date = LocalDateTime.parse(bidSplit[1]);
		this.customer = Shop.getShop().searchCustomerbyID(bidSplit[2]);
	}

	/** @return the customer */
	public Customer getCustomer() {
		return customer;
	}

	/** @return the price */
	public Double getPrice() {
		return price;
	}

	/** the date */
	public LocalDateTime getDate() {
		return date;
	}

	/** @return a string to prin in the notifications */
	public String toPrint() {
		return "Customer: " + this.customer.getName() + "\n Price: " + this.price;
	}

	@Override
	public String toString() {
		return price + "#" + date + "#" + customer.getCustomerID();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bid other = (Bid) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}
}
