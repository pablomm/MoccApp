package pads.antiquary.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import pads.antiquary.model.exceptions.Duplicated;
import pads.antiquary.view.AuctionManagerPanel;

/**
 * Controller of the panel
 * adds an auction
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class AddItemAuctionControler implements ActionListener {

	/**
	 * Panel of the controller
	 */
	private AuctionManagerPanel panel;

	public AddItemAuctionControler(AuctionManagerPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(panel.getLot() == null){
			JOptionPane.showMessageDialog(panel, "You have to select a lot");
			return;
		}
		
		if(panel.getItem() == null){
			JOptionPane.showMessageDialog(panel, "You have to select an item");
			return;
		}
		
		
		try {
			panel.getLot().addItem(panel.getItem());
		} catch (Duplicated e1) {
			JOptionPane.showMessageDialog(panel, "Invalid Item");
			return;
		}
		panel.refreshPanel();
		panel.updateItem();
		panel.updateLot();
		JOptionPane.showMessageDialog(panel, "Item added");
		

	}

}