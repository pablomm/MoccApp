package pads.antiquary.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import pads.antiquary.view.AuctionManagerPanel;

/**
 * Controller of the panel
 * updates the auction
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class ChangeAuctionControler implements ActionListener {

	/**
	 * Panel of the controller
	 */
	private AuctionManagerPanel panel;

	public ChangeAuctionControler(AuctionManagerPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(panel.getAuction() == null){
			JOptionPane.showMessageDialog(panel, "You have to select an auction");
			return;
		}
		
		if(!panel.getAuction().getBid().isEmpty()){
			JOptionPane.showMessageDialog(panel, "You can't change a auction that started");
			return;
		}
		
		
		try{
			
			panel.getAuction().setDescription(panel.getDescription());
			
			
		}catch(Exception e1){}
		

	}

}