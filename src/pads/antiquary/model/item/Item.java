package pads.antiquary.model.item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class with the information and methods related to all the Items
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public abstract class Item {

	/** Description of the item */
	private String description;

	/** manufacture year of the item, x in thee unknown digits */
	private String manufactureYear;

	/** Acquisition date of the item */
	private LocalDate acquisitionDate;

	/** Acquisition cost of the item */
	private Double acquisitionCost;

	/** Sales price of the item */
	private Double targetPrice;

	/** Indicates if the item is sold */
	private boolean sold;

	/** Indicates if the item is in a Auction */
	private boolean inAuction;

	/**
	 * Constructor of the class Item
	 * 
	 * @param description
	 *            Description of the Item
	 * @param manufactureYear
	 *            Manufacture Year (X in unknown digits)
	 * @param acquisitionDate
	 *            Acquisition date
	 * @param acquisitionCost
	 *            Acquisition cost
	 * @param targetPrice
	 *            Sales price
	 */
	public Item(String description, String manufactureYear, LocalDate acquisitionDate, Double acquisitionCost,
			Double targetPrice) {
		super();
		this.description = description;
		this.manufactureYear = manufactureYear;
		this.acquisitionDate = acquisitionDate;
		this.acquisitionCost = acquisitionCost;
		this.targetPrice = targetPrice;
		this.sold = false;
		this.inAuction = false;
	}

	/* Setters and getters */

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	public boolean isInAuction() {
		return inAuction;
	}

	public void setInAuction(boolean inAuction) {
		this.inAuction = inAuction;
	}

	public String getDescription() {
		return description;
	}

	public String getManufactureYear() {
		return manufactureYear;
	}

	public LocalDate getAcquisitionDate() {
		return acquisitionDate;
	}

	public Double getAcquisitionCost() {
		return acquisitionCost;
	}

	public Double getTargetPrice() {
		return targetPrice;
	}

	public boolean isEnable() {
		return !(this.inAuction || this.sold);
	}

	/**
	 * gets the discount of the item
	 * 
	 * @return the item's discount
	 */
	public abstract Double getDiscount();

	/**
	 * Gets the delivery cost associated to the item
	 * 
	 * @return teh delivery cost
	 */
	public abstract Double deliveryCost();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.description + ";" + this.manufactureYear + ";"
				+ this.acquisitionDate.format(DateTimeFormatter.ofPattern("d/L/yyyy")) + ";" + this.acquisitionCost
				+ ";" + this.targetPrice;

	}
}