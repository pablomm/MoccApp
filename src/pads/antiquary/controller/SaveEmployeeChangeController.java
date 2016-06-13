package pads.antiquary.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import pads.antiquary.model.people.Gender;
import pads.antiquary.model.people.User;
import pads.antiquary.view.ModEmployeePanel;

/**
 * Controller of the panel
 * save employee changes
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class SaveEmployeeChangeController implements ActionListener {

	/**
	 * Panel of the controller
	 */
	private ModEmployeePanel panel;

	public SaveEmployeeChangeController(ModEmployeePanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		User u = panel.getUserSelected();

		if (u == null) {
			JOptionPane.showMessageDialog(panel, "You have to select an employee");
			return;
		}
		
		try {
		u.setPhone(Integer.parseInt(panel.getPhone()));
		} catch(Exception e1) {
			JOptionPane.showMessageDialog(panel, "Invalid phone");
			return;
		}
		
		u.setUserName(panel.getUsername());
		u.setName(panel.getName());
		u.setSurname(panel.getSurname());
		u.setAdress(panel.getAdress());
		u.setEmail(panel.getEmail());
		u.setGender((panel.getMale()) ? Gender.MALE : Gender.FEMALE);
		
		JOptionPane.showMessageDialog(panel, "Changes saved");
		
		panel.clearFields();

		
	}
}
