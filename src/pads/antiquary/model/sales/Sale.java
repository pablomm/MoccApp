package pads.antiquary.model.sales;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import pads.antiquary.model.exceptions.*;
import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.item.*;
import pads.antiquary.model.misc.Settings;
import pads.antiquary.model.people.Customer;

/**
 * Class with the methods an atributes to administrate a sale
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class Sale {

	/** Price of the items */
	private Double price;
	/** Total delivery cost of the items */
	private Double deliveryCost;
	/** Discounts applicated to the items */
	private Double discount;
	/** Final price of the sale */
	private Double finalPrice;
	/** Client that realize the sale */
	private Customer customer;
	/** Items to be sold */
	private ArrayList<Item> items;
	/** Date of the sale */
	private LocalDate date;

	/**
	 * Constructor of the class
	 * 
	 * @param customer
	 */
	public Sale(Customer customer) {
		this.customer = customer;
		this.price = 0.00;
		this.deliveryCost = 0.00;
		this.discount = 0.00;
		this.finalPrice = 0.00;
		this.items = new ArrayList<Item>();
		this.date = LocalDate.now();
	}

	public Sale(String[] saleSplit) throws NotFound {
		this(Shop.getShop().searchCustomerbyID(saleSplit[4]));
		this.date = LocalDate.parse(saleSplit[5], DateTimeFormatter.ofPattern("d/L/yyyy"));

		String[] items = saleSplit[6].split(",");

		for (String str : items) {
			try {
				Item item = Shop.getShop().searchExactItem(str);
				this.addItem(item);
				item.setSold(true);

			} catch (Duplicated | InvalidSale e) {
				// if it is duplicated simply add once the sale
				// check whether the object is available is not the task of the
				// loader
			}
		}
	}

	/**
	 * Update the price when an item is added
	 * 
	 * @param item
	 */
	private void updatePrice(Item item) {
		this.price += item.getTargetPrice();
		if (this.customer.getContract().isValid()) {
			if (this.customer.getContract().getType().equals(ContractType.valueOf("Vip"))) {
				this.discount += item.getTargetPrice() * item.getDiscount() / 100;
			} else if (this.customer.getContract().getType().equals(ContractType.valueOf("Standard"))) {
				this.deliveryCost += item.deliveryCost();
				if (this.customer.getAmountSpend() >= Settings.getMIN_SPEND())
					this.discount += item.getTargetPrice() * item.getDiscount() / 100;
			}
		}
	}

	/**
	 * update the final price
	 */
	private void updateFinalPrice() {
		this.finalPrice = this.price + this.deliveryCost - this.discount;
	}

	/**
	 * Add an item to teh sale
	 * 
	 * @param item
	 * @throws Duplicated
	 * @throws InvalidSale
	 */
	public void addItem(Item item) throws Duplicated, InvalidSale {
		if (!item.isEnable())
			throw new InvalidSale();
		if (this.items.contains(item))
			throw new Duplicated();
		this.items.add(item);
		updatePrice(item);
		updateFinalPrice();
	}

	/**
	 * Closes the sale
	 */
	public void purchase() {
		this.customer.increaseSpend(finalPrice);
		for (Item i : this.items) {
			this.customer.addItem(i);
			i.setSold(true);
		}
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @return the deliveryCost
	 */
	public Double getDeliveryCost() {
		return deliveryCost;
	}

	/**
	 * @return the discount
	 */
	public Double getDiscount() {
		return discount;
	}

	/**
	 * @return the finalPrice
	 */
	public Double getFinalPrice() {
		return finalPrice;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @return the items
	 */
	public ArrayList<Item> getItems() {
		return items;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
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
		Sale other = (Sale) obj;
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
		if (deliveryCost == null) {
			if (other.deliveryCost != null)
				return false;
		} else if (!deliveryCost.equals(other.deliveryCost))
			return false;
		if (discount == null) {
			if (other.discount != null)
				return false;
		} else if (!discount.equals(other.discount))
			return false;
		if (finalPrice == null) {
			if (other.finalPrice != null)
				return false;
		} else if (!finalPrice.equals(other.finalPrice))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String ret = price + ";" + deliveryCost + ";" + discount + ";" + finalPrice + ";" + customer.getCustomerID()
				+ ";" + date.format(DateTimeFormatter.ofPattern("d/L/yyyy")) + ";";

		for (Item i : this.items) {
			ret += i.getDescription() + ",";
		}

		return ret;
	}

}