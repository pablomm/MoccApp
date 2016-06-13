package pads.antiquary.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pads.antiquary.view.NewEmployeePanel;

/**
 * Controller of the panel
 * clear the employee panel
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class EmployeeClearController implements ActionListener{

	/**
	 * Panel of the controller
	 */
	private NewEmployeePanel panel;
	
	public EmployeeClearController(NewEmployeePanel panel){
		this.panel = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		panel.clearFields();
	}
}
