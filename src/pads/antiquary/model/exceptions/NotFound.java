package pads.antiquary.model.exceptions;

/**
 * Exception throws when a search don't obtains any results
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class NotFound extends Exception {
	private static final long serialVersionUID = -4856863529417236863L;

	@Override
	public String toString() {
		return "Your search has no matches";
	}
}
