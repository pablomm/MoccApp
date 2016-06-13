package pads.antiquary.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import pads.antiquary.model.exceptions.Duplicated;
import pads.antiquary.model.exceptions.InvalidSale;
import pads.antiquary.view.SalesPanel;

/**
 * Controller of the panel
 * adds a new item
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class AddItemActionListener implements ActionListener{
	
	/**
	 * Panel of the controller
	 */
	private SalesPanel panel; 
	
	/**
	 * Creates the panel
	 * @param panel
	 */
	public AddItemActionListener(SalesPanel panel){
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(this.panel.getSale()== null)
			JOptionPane.showMessageDialog(panel, "You have to create a sale before.");
		else{
			try {
				panel.getSale().addItem(panel.getSelectItem());
				panel.refreshPanel();
				panel.updateItem();
			} catch (Duplicated e1) {
				JOptionPane.showMessageDialog(panel, "The item is already in the sale.");
			} catch (InvalidSale e1) {
				JOptionPane.showMessageDialog(panel, "The item cannot be add to the sale.");
			}
			
		}
			
	}

}
