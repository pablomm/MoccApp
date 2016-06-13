package pads.antiquary.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pads.antiquary.view.CustomerPanel;

/**
 * Controller of the panel
 * clear customer panel
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class CustomerClearController implements ActionListener {

	/**
	 * Panel of the controller
	 */
	private CustomerPanel panel;

	public CustomerClearController(CustomerPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		panel.clear();

	}

}
