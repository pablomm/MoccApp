package pads.antiquary.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import pads.antiquary.model.exceptions.Duplicated;
import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.people.Customer;
import pads.antiquary.model.people.Gender;
import pads.antiquary.view.CustomerPanel;

/**
 * Controller of the panel
 * creates new customer
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class NewCustomerController implements ActionListener {

	/**
	 * Panel of the controller
	 */
	private CustomerPanel panel;

	public NewCustomerController(CustomerPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		int phone = 0;

		try {
			phone = Integer.parseInt(panel.getPhone());
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(panel, "Invalid phone");
			return;
		}

		Customer c = new Customer(panel.getName(), panel.getSurname(), LocalDate.now(), panel.getMail(), phone,
				(panel.getMale()) ? Gender.MALE : Gender.FEMALE, panel.getAdress(), panel.getNotification(),
				panel.getContract());
		try {
			Shop.getShop().addCustomer(c);
		} catch (Duplicated e1) {
			panel.refreshPanel();
			panel.updateTable();
			JOptionPane.showMessageDialog(panel, "Customer it's already in the database");
			return;
		}
		
		JOptionPane.showMessageDialog(panel, "Customer created");
		panel.setCustomer(null);
		panel.refreshPanel();
		panel.updateTable();

	}

}