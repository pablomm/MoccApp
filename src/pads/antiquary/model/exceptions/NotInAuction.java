package pads.antiquary.model.exceptions;

/**
 * Exception throws when a customer bids in an auction without pay the entry
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class NotInAuction extends Exception {
	private static final long serialVersionUID = 3388122389810149769L;

	@Override
	public String toString() {
		return "Customer isn't in the auction";
	}

}
