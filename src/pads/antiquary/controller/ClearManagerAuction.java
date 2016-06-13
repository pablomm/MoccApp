package pads.antiquary.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pads.antiquary.view.AuctionManagerPanel;

/**
 * Controller of the panel
 * clear manager auction panel
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class ClearManagerAuction implements ActionListener {

	/**
	 * Panel of the controller
	 */
	private AuctionManagerPanel panel;

	public ClearManagerAuction(AuctionManagerPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		panel.clear();
		panel.updateAuction();
		panel.updateItem();
		panel.updateLot();

	}

}
