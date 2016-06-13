package pads.antiquary.model.item;

import java.util.ArrayList;

import pads.antiquary.model.exceptions.*;

public class Lot implements Auctionable {

	/** Array list with all the items contained */
	private ArrayList<Item> item;

	/** Constructor of the class Lot */
	public Lot() {
		this.item = new ArrayList<Item>();
	}

	/**
	 * Method to add an item to the lot
	 * 
	 * @param item
	 *            Item to be added
	 * @throws Duplicated
	 *             Indicates that the item is already in the lot
	 */
	public void addItem(Item item) throws Duplicated {
		if (this.item.contains(item))
			throw new Duplicated();
		this.item.add(item);
	}

	@Override
	public void setSold(boolean sold) {
		for (Item i : this.item)
			i.setSold(sold);

	}

	@Override
	public void setInAuction(boolean inAuction) {
		for (Item i : this.item)
			i.setInAuction(inAuction);

	}

	@Override
	public ArrayList<Item> getItems() {
		return this.item;

	}

	@Override
	public boolean isEnable() {
		boolean bool = true;
		for (Item i : this.item)
			bool &= i.isEnable();
		return bool;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String ret = "[";
		for (Item i : this.item)
			ret += i.getDescription() + ",";
		return ret + "]";
	}

}
