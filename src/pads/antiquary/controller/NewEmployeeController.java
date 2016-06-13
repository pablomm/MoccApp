package pads.antiquary.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import pads.antiquary.model.exceptions.Duplicated;
import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.people.Employee;
import pads.antiquary.model.people.Gender;
import pads.antiquary.model.people.Manager;
import pads.antiquary.model.people.User;
import pads.antiquary.view.ModEmployeePanel;
import pads.antiquary.view.NewEmployeePanel;

/**
 * Controller of the panel
 * creates new employee
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class NewEmployeeController implements ActionListener {

	/**
	 * Panel of the controller
	 */
	private NewEmployeePanel panel;
	
	/**
	 * Panel associated of the controller
	 */
	private ModEmployeePanel modpanel;

	public NewEmployeeController(NewEmployeePanel panel, ModEmployeePanel modpanel) {
		this.panel = panel;
		this.modpanel = modpanel;
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

		if (!panel.getPass().equals(panel.getPass2())) {
			JOptionPane.showMessageDialog(panel, "Passwords are differents");
			return;
		}
		User u;
		switch (panel.getType()) {
		case 0:
			try {
				u = new Employee(panel.getUsername(), panel.getName(), panel.getSurname(), LocalDate.now(),
						panel.getEmail(), phone, (panel.getMale()) ? Gender.MALE : Gender.FEMALE, panel.getAdress(),
						panel.getPass(),true);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(panel, "invalid password");
				return;
			}
			break;

		default:
			try {
				u = new Manager(panel.getUsername(), panel.getName(), panel.getSurname(), LocalDate.now(), panel.getEmail(),
						phone, (panel.getMale()) ? Gender.MALE : Gender.FEMALE, panel.getAdress(), panel.getPass(),true);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(panel, "invalid password");
				return;
			}
			break;

		}
		try {
			Shop.getShop().addUser(u);
		} catch (Duplicated e1) {
			JOptionPane.showMessageDialog(panel, "User is already in the database");
		}
		panel.clearFields();
		modpanel.updateEmployees();
		JOptionPane.showMessageDialog(panel, "User created");

	}
}
