package pads.antiquary.model.exceptions;

/**
 * Exception throws by the ShopFileIo in case of the line readed don't have the
 * number of atributes that should have.
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class InvalidSplit extends Exception {

	private static final long serialVersionUID = 320153160563170040L;

	@Override
	public String toString() {
		return "The split is corrupted or invalid";
	}

}
