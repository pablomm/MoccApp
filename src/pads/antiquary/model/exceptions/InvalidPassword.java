package pads.antiquary.model.exceptions;

/**
 * Exception to be throw when an empty password is introduced to check or hash
 * it
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class InvalidPassword extends Exception {
	private static final long serialVersionUID = 4112132267390355583L;

	public String toString() {
		return "Invalid Password";
	}
}
