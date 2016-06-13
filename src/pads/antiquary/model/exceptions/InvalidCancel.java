package pads.antiquary.model.exceptions;

/**
 * Exception to be throwed when an auction with bids is canceled
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class InvalidCancel extends Exception {
	private static final long serialVersionUID = 4807350130400790616L;

	@Override
	public String toString() {
		return "Auction can't be canceled, there is a bid up";

	}

}
