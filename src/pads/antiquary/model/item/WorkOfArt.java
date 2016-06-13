package pads.antiquary.model.item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Class with the information and methods related to Work of arts
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class WorkOfArt extends Item implements Auctionable {

	/** Name of the author of the work */
	private String author;

	/** Type of work of art */
	private TypeOfWork type;

	/** Indicates if exists authenticity certificate */
	private Authenticity certificate;

	/**
	 * Constructor of the class WorkOfArt
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
	 * @param author
	 *            String with the author name
	 * @param type
	 *            Type of work of art
	 * @param certificate
	 *            Type of certificate
	 */
	public WorkOfArt(String description, String manufactureYear, LocalDate acquisitionDate, Double acquisitionCost,
			Double targetPrice, String author, TypeOfWork type, Authenticity certificate) {

		super(description, manufactureYear, acquisitionDate, acquisitionCost, targetPrice);
		this.author = author;
		this.type = type;
		this.certificate = certificate;
	}

	public WorkOfArt(String[] itemSplit) {
		this(itemSplit[1], itemSplit[2], LocalDate.parse(itemSplit[3], DateTimeFormatter.ofPattern("d/L/yyyy")),
				Double.valueOf(itemSplit[4]), Double.valueOf(itemSplit[5]), itemSplit[6],
				TypeOfWork.valueOf(itemSplit[7]), Authenticity.valueOf(itemSplit[8]));
	}

	/**
	 * Gets the author of the work
	 * 
	 * @return string with the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Gets the type of work
	 * 
	 * @return the type of work
	 */
	public TypeOfWork getType() {
		return type;
	}

	/**
	 * Gets the authenticity certificate
	 * 
	 * @return the authenticity certificate
	 */
	public Authenticity getCertificate() {
		return certificate;
	}

	@Override
	public Double getDiscount() {
		return 0.00;
	}

	@Override
	public Double deliveryCost() {
		return 0.00;
	}

	@Override
	public String toString() {
		return "A;" + super.toString() + ";" + this.author + ";" + this.type + ";" + this.certificate;

	}

	@Override
	public ArrayList<Item> getItems() {
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(this);
		return items;

	}

}
