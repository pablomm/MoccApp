package pads.antiquary.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import pads.antiquary.model.people.Gender;
import pads.antiquary.view.CustomerPanel;

/**
 * Controller of the panel
 * update the customer
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class ChangeCustomerActionListener implements ActionListener {

	/**
	 * Panel of the controller
	 */
	private CustomerPanel panel;

	public ChangeCustomerActionListener(CustomerPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (panel.getCustomer() == null) {
			JOptionPane.showMessageDialog(panel, "You have to select a customer");
			return;
		}

		int phone = 0;

		try {
			phone = Integer.parseInt(panel.getPhone());
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(panel, "Invalid phone");
			return;
		}

		panel.getCustomer().setName(panel.getName());

		panel.getCustomer().setSurname(panel.getSurname());

		panel.getCustomer().setAdress(panel.getAdress());

		panel.getCustomer().setEmail(panel.getMail());

		panel.getCustomer().setGender((panel.getMale()) ? Gender.MALE : Gender.FEMALE);

		panel.getCustomer().setPhone(phone);

		panel.setCustomer(null);

		panel.refreshPanel();
		JOptionPane.showMessageDialog(panel, "Changes Saved");

	}

}
