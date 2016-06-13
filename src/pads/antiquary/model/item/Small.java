package pads.antiquary.model.item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class with the information and methods related to small Items
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class Small extends Item {

	/** Discount of the item */
	private Double discount;

	/**
	 * Constructor of the class Small
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
	 * @param discount
	 *            Discount of the item
	 */
	public Small(String description, String manufactureYear, LocalDate acquisitionDate, Double acquisitionCost,
			Double targetPrice, Double discount) {
		super(description, manufactureYear, acquisitionDate, acquisitionCost, targetPrice);
		this.discount = discount;
	}

	/**
	 * Alternative constructor of the class, that accept an String array
	 * 
	 * @param itemSplit
	 *            Array with length 7 with the atributes
	 */
	public Small(String[] itemSplit) {
		this(itemSplit[1], itemSplit[2], LocalDate.parse(itemSplit[3], DateTimeFormatter.ofPattern("d/L/yyyy")),
				Double.valueOf(itemSplit[4]), Double.valueOf(itemSplit[5]), Double.valueOf(itemSplit[6]));

	}

	@Override
	public Double getDiscount() {
		return discount;
	}

	@Override
	public Double deliveryCost() {
		return Double.valueOf(0);
	}

	@Override
	public String toString() {
		return "S;" + super.toString() + ";" + this.discount;
	}

}
