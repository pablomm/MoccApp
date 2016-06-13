package pads.antiquary.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import pads.antiquary.model.exceptions.Duplicated;
import pads.antiquary.view.AuctionPanel;

/**
 * Controller of the panel
 * Pay entry
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class AuctionEntryController implements ActionListener {

	/**
	 * Panel of the controller
	 */
	private AuctionPanel panel;

	public AuctionEntryController(AuctionPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (panel.getCustomerSelected() == null) {
			JOptionPane.showMessageDialog(panel, "There isn't any customer selected");
			return;
		}

		if (panel.getAuctionSelected() == null) {
			JOptionPane.showMessageDialog(panel, "There isn't any auction selected");
			return;
		}
		if(panel.getAuctionSelected().isActive() == false){
			JOptionPane.showMessageDialog(panel, "The auction is not active");
			return;
		}
		double d = 0;
		try {
			d = panel.getAuctionSelected().entry(panel.getCustomerSelected());
		} catch (Duplicated e1) {
			JOptionPane.showMessageDialog(panel, "The customer paid the entry yet");
			return;
		}

		JOptionPane.showMessageDialog(panel, "Now the customer can bid in the auction.\nHas to pay " + d + ".");
		panel.refreshPanel();
	}

}
