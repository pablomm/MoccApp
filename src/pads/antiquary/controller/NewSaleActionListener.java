package pads.antiquary.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import pads.antiquary.model.sales.Sale;
import pads.antiquary.view.SalesPanel;

/**
 * Controller of the panel
 * creates new sale
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class NewSaleActionListener implements ActionListener{
	
	/**
	 * Panel of the controller
	 */
	private SalesPanel panel;
	
	public NewSaleActionListener(SalesPanel panel){
		this.panel = panel;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (panel.getSelectCustomer() == null){
			JOptionPane.showMessageDialog(panel, "There are not any selected customer.");
		}else
			panel.setSale(new Sale(panel.getSelectCustomer()));
		panel.refreshPanel();
		panel.updateItem();
	}


}
