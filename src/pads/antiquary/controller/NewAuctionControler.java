package pads.antiquary.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import pads.antiquary.model.exceptions.Duplicated;
import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.sales.Auction;
import pads.antiquary.view.AuctionManagerPanel;

/**
 * Controller of the panel
 * creates new auction
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class NewAuctionControler implements ActionListener {

	/**
	 * Panel of the controller
	 */
	private AuctionManagerPanel panel;

	public NewAuctionControler(AuctionManagerPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (panel.getLot() == null) {
			JOptionPane.showMessageDialog(panel, "You have to create a lot");
			return;
		}

		if (panel.getLot().getItems().isEmpty()) {
			JOptionPane.showMessageDialog(panel, "You have to add items to the lot");
			return;
		}
		Auction a = null;

		try {
			a = new Auction(panel.getName(), panel.getDescription(), panel.getTargetPrice(), panel.getEntryPrice(),
					panel.getDuration(), panel.getLot());
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(panel, "Invalid values");
			return;
		}

		try {
			Shop.getShop().addAuction(a);
		} catch (Duplicated e1) {
			JOptionPane.showMessageDialog(panel, "Auction Duplicated");
			return;
		}
		JOptionPane.showMessageDialog(panel, "Auction added");
		panel.clear();
		panel.updateAuction();
		panel.updateItem();
		panel.updateLot();
	}

}
