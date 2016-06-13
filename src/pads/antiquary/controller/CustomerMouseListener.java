package pads.antiquary.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import pads.antiquary.model.exceptions.NotFound;
import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.people.Customer;
import pads.antiquary.view.CustomerPanel;

/**
 * Controller of the panel
 * controls the customer table
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class CustomerMouseListener  implements MouseListener{

	/**
	 * Panel of the controller
	 */
	private CustomerPanel panel;

	public CustomerMouseListener(CustomerPanel panel) {
		this.panel = panel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (panel.getTable().getSelectedRow() == -1)
			return;

		try {
			Customer c = Shop.getShop().searchCustomerbyID((String) panel.getTable().getModel()
					.getValueAt(panel.getTable().getSelectedRow(), 2));
			panel.setCustomer(c);
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
