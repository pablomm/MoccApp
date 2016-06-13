package pads.antiquary.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import pads.antiquary.view.SalesPanel;

/**
 * Controller of the panel
 * purchase the sale
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class PurchaseActionListener implements ActionListener{

	/**
	 * Panel of the controller
	 */
	private SalesPanel panel;
	
	public PurchaseActionListener(SalesPanel panel){
		this.panel = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (panel.getSale() == null){
			JOptionPane.showMessageDialog(panel, "You have to create a sale before.");
		}
		else if(panel.getSale().getItems().isEmpty())
			JOptionPane.showMessageDialog(panel, "There are not any item in the sale.");
		else{
			panel.getSale().purchase();
			panel.setSale(null);
			panel.refreshPanel();
		}
			
	}
	

}
