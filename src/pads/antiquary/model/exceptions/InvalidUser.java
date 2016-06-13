package pads.antiquary.model.exceptions;

/**
 * Exception throws if in the login an invalid user is introduced
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class InvalidUser extends Exception {
	private static final long serialVersionUID = -7090790934029012806L;

	@Override
	public String toString() {
		return "Invalid User";
	}

}
