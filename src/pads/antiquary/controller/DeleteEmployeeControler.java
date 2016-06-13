package pads.antiquary.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.people.User;
import pads.antiquary.view.ModEmployeePanel;

/**
 * Controller of the panel
 * deletes the employee
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class DeleteEmployeeControler implements ActionListener {
	
	/**
	 * Panel of the controller
	 */
	private ModEmployeePanel panel;
	
	private static final String TEXT = "Do you want to delete ";
	
	private static final String TITLE = "Confirmation";

	public DeleteEmployeeControler(ModEmployeePanel panel){
		this.panel = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		User u = panel.getUserSelected();
		
		if(u == null){
			JOptionPane.showMessageDialog(panel, "You have to select an employee");
			return;
		}
		if (JOptionPane.showConfirmDialog(panel, TEXT + u.getUserName()+"?", TITLE, JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE) == 0) {
		
			Shop.getShop().getUsers().remove(u);
			panel.setUserSelected(null);
			panel.updateEmployees();
			
		}
	}

}
