package pads.antiquary.model.exceptions;

/**
 * Exception to be throwed when a duplicated object is added to an ArrayList
 * that only can contain uniques objects.
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class Duplicated extends Exception {
	private static final long serialVersionUID = 8930708557329697347L;

	public String toString() {
		return "There is a similar object in the ArrayList";
	}
}
