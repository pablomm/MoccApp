package pads.antiquary.model.item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import pads.antiquary.model.misc.Settings;

/**
 * Class with the information and methods related to bulky Items
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class Bulky extends Item {

	/**
	 * Dimension of the Item
	 */
	private Dimension dimension;

	/**
	 * Constructor of the class
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
	 * @param dimension
	 *            Dimension of the item
	 */
	public Bulky(String description, String manufactureYear, LocalDate acquisitionDate, Double acquisitionCost,
			Double targetPrice, Dimension dimension) {
		super(description, manufactureYear, acquisitionDate, acquisitionCost, targetPrice);
		this.dimension = dimension;
	}

	/**
	 * Alternative constructor of the class, giving directly the values of the
	 * dimension
	 * 
	 * @param description
	 *            Description of the Item
	 * @param manufactureYear
	 *            Manufacture Year, x in unknown digits
	 * @param acquisitionDate
	 *            Acquisition date
	 * @param acquisitionCost
	 *            Acquisition cost
	 * @param targetPrice
	 *            Sales price
	 * @param weight
	 *            Weight of the item
	 * @param height
	 *            Height of the item
	 * @param width
	 *            Width of the item
	 * @param length
	 *            Length of the item
	 */
	public Bulky(String description, String manufactureYear, LocalDate acquisitionDate, Double acquisitionCost,
			Double targetPrice, Integer weight, Integer height, Integer width, Integer length) {

		this(description, manufactureYear, acquisitionDate, acquisitionCost, targetPrice,
				new Dimension(weight, height, width, length));
	}

	/**
	 * Alternative constructor of the class, that accept an String array
	 * 
	 * @param itemSplit
	 *            Array with length 10 with the atributes
	 */
	public Bulky(String[] itemSplit) {
		this(itemSplit[1], itemSplit[2], LocalDate.parse(itemSplit[3], DateTimeFormatter.ofPattern("d/L/yyyy")),
				Double.valueOf(itemSplit[4]), Double.valueOf(itemSplit[5]), Integer.valueOf(itemSplit[6]),
				Integer.valueOf(itemSplit[7]), Integer.valueOf(itemSplit[8]), Integer.valueOf(itemSplit[9]));
	}

	/**
	 * Get the item's dimension
	 * 
	 * @return The dimension of the item
	 */
	public Dimension getDimension() {
		return dimension;
	}

	@Override
	public Double deliveryCost() {

		if (this.dimension.getHeight() > Settings.getMIN_HEIGHT())
			return Double.valueOf(Settings.getMAX_COST());

		else if (this.dimension.getHeight() < Settings.getMIN_HEIGHT()
				&& this.dimension.getWeight() > Settings.getMIN_WEIGHT())
			return Double.valueOf(Settings.getMIN_COST())
					+ Settings.getCOST_PER_KG() * (this.dimension.getWeight() - 15);

		else
			return Double.valueOf(Settings.getMIN_COST());

	}

	@Override
	public Double getDiscount() {
		return Double.valueOf(0);
	}

	@Override
	public String toString() {
		return "B;" + super.toString() + ";" + this.dimension;
	}

}
