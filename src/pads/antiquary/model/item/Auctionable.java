package pads.antiquary.model.item;

import java.util.ArrayList;

/**
 * Interface that implements the objects that can be auctioned, (Lots and
 * WorkOfArt)
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public interface Auctionable {

	/**
	 * Sets solds atribute of all the items in the Auctionable
	 * 
	 * @param sold
	 *            The new sold state
	 */
	public void setSold(boolean sold);

	/**
	 * Sets inAuction atribute of all the items in the Auctionable
	 * 
	 * @param inAuction
	 *            The new inAuction state
	 */
	public void setInAuction(boolean inAuction);

	/**
	 * Checks if all the items are enable
	 * 
	 * @return true if all the items are enable
	 */
	public boolean isEnable();

	/**
	 * Returns the items contained
	 * 
	 * @return An ArrayList with all the items contained
	 */
	public ArrayList<Item> getItems();
}
