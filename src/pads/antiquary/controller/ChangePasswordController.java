package pads.antiquary.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import pads.antiquary.model.exceptions.InvalidPassword;
import pads.antiquary.model.misc.Password;
import pads.antiquary.model.people.User;
import pads.antiquary.view.ModEmployeePanel;

/**
 * Controller of the panel
 * changes the password
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class ChangePasswordController implements ActionListener {

	/**
	 * Panel of the controller
	 */
	private ModEmployeePanel panel;

	public ChangePasswordController(ModEmployeePanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		User u = panel.getUserSelected();
		
		if(u == null){
			JOptionPane.showMessageDialog(panel, "You have to select an employee");
			return;
		}
		boolean check = false;
		try {
			check = Password.check( panel.getPass(),u.getPassword());
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(panel, "Password field empty");
			return;
		}
		
		if(check==false){
			JOptionPane.showMessageDialog(panel, "Incorrect old password");
			return;
		}
		
			try {
				u.changePassword(panel.getPass2());
			} catch (InvalidPassword e1) {
				JOptionPane.showMessageDialog(panel, "Password field empty");
				return;
			}
			
			panel.updateEmployees();
			JOptionPane.showMessageDialog(panel, "Password changed");
			return;
		}

}
