package pads.antiquary.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import pads.antiquary.model.exceptions.InvalidBid;
import pads.antiquary.model.exceptions.NotInAuction;
import pads.antiquary.model.sales.*;
import pads.antiquary.view.AuctionPanel;

/**
 * Controller of the panel
 * adds a bid
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class AuctionBidController implements ActionListener {
	/**
	 * Panel of the controller
	 */
	private AuctionPanel panel;
	
	public AuctionBidController(AuctionPanel panel){
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
		Bid bid = new Bid(panel.getBidPrice(), panel.getCustomerSelected());
		
		try {
			panel.getAuctionSelected().addBid(bid);
		} catch (InvalidBid e1) {
			JOptionPane.showMessageDialog(panel, "The bid is invalid");
		} catch (NotInAuction e1) {
			JOptionPane.showMessageDialog(panel, "The customer is not in the entry");
		}
		
	}

}
