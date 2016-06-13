package pads.antiquary.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import pads.antiquary.model.exceptions.NotFound;
import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.sales.Auction;
import pads.antiquary.view.AuctionPanel;

/**
 * Controller of the panel
 * select the auction
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class AuctionTableMouseListener implements MouseListener {

	/**
	 * Panel of the controller
	 */
	private AuctionPanel panel;

	public AuctionTableMouseListener(AuctionPanel panel) {
		this.panel = panel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (panel.getAuctionTable().getSelectedRow() == -1)
			return;

		String s = (String) panel.getAuctionTable().getModel().
				getValueAt(panel.getAuctionTable().getSelectedRow(), 0);
		try {
			for(Auction a : Shop.getShop().searchAuction(s)){
				panel.setAuctionSelected(a);
				break;
			}
			
			
		} catch (NotFound e1) {}
		panel.refreshPanel();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
