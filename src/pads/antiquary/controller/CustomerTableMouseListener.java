package pads.antiquary.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import pads.antiquary.model.exceptions.NotFound;
import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.people.Customer;
import pads.antiquary.view.AuctionPanel;

/**
 * Controller of the panel
 * control the customer table of the auction
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class CustomerTableMouseListener implements MouseListener {

	/**
	 * Panel of the controller
	 */
	private AuctionPanel panel;

	public CustomerTableMouseListener(AuctionPanel panel) {
		this.panel = panel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (panel.getCustomerTable().getSelectedRow() == -1)
			return;

		try {
			Customer c = Shop.getShop().searchCustomerbyID((String) panel.getCustomerTable().getModel()
					.getValueAt(panel.getCustomerTable().getSelectedRow(), 2));
			panel.setCustomerSelected(c);
		} catch (NotFound e1) {}
		panel.refreshPanel();
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
