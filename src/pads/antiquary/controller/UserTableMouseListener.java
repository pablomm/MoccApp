package pads.antiquary.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import pads.antiquary.model.exceptions.NotFound;
import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.people.User;
import pads.antiquary.view.ModEmployeePanel;

/**
 * Controller of the panel
 * select the user of the table
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class UserTableMouseListener implements MouseListener {
	
	/**
	 * Panel of the controller
	 */
	private ModEmployeePanel panel;

	public UserTableMouseListener(ModEmployeePanel panel) {
		this.panel = panel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (panel.getEmployees().getSelectedRow() == -1)
			return;

		try {
			User u = Shop.getShop().searchUser((String)panel.getEmployees().getValueAt(panel.getEmployees().getSelectedRow(), 0));
			panel.setUserSelected(u);
		} catch (NotFound e1) {}
		panel.updateEmployees();;
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
