package pads.antiquary.model.exceptions;

/**
 * Exception to be throwed when a invalid bid is added to an auction. For
 * instance if the bid is lower than the current bid or the target price. Or if
 * the customer didn't entry in the auction.
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class InvalidBid extends Exception {
	private static final long serialVersionUID = -2158209175187505399L;

	public String toString() {
		return "Exists an expensive bid yet";
	}

}
