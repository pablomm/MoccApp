package pads.antiquary.model.exceptions;

/**
 * Exception throws when an item that isn't enable is add to the sale
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class InvalidSale extends Exception {
	private static final long serialVersionUID = 8691346109990255940L;

	public String toString() {
		return "Invalid Sale";
	}
}
