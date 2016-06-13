package pads.antiquary.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import pads.antiquary.model.misc.Settings;
import pads.antiquary.view.SettingsPanel;

/**
 * Controller of the panel
 * save the settings changes
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class SettingsController  implements ActionListener{

	/**
	 * Panel of the controller
	 */
	private SettingsPanel panel;
	
	public SettingsController(SettingsPanel panel){
		this.panel = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
		Settings.setMIN_SPEND(panel.getMinSpend());
		Settings.setCOST_PER_KG(panel.getCostPerKg());
		Settings.setMAX_COST(panel.getMaxCost());
		Settings.setMIN_COST(panel.getMinCost());
		Settings.setMIN_HEIGHT(panel.getMinHeight());
		Settings.setMIN_WEIGHT(panel.getMinWeight());
		Settings.setCONTRACT_DURATION(panel.getContractDuration());
		Settings.setSTANDARD_FREE_ENTRYS(panel.getStandardFreeEntrys());
		Settings.setBID_SUBJECT(panel.getBidSubject());
		Settings.setBID_BODY(panel.getBidBody());
		Settings.setDEFAULT_FOLDER(panel.getDefaultFolder());
		Settings.setDEFAULT_ITEM_FILE(panel.getDefaultItemFile());
		Settings.setDEFAULT_USER_FILE(panel.getDefaultUserFile());
		Settings.setDEFAULT_AUCTION_FILE(panel.getDefaultAuctionFile());
		Settings.setDEFAULT_CUSTOMER_FILE(panel.getDefaultCustomerFile());
		Settings.setDEFAULT_SALE_FILE(panel.getDefaultSaleFile());
		
		} catch (Exception e1){
			JOptionPane.showMessageDialog(panel, "Invalid value");
			panel.updateFields();
			return;
		}
		panel.updateFields();
		JOptionPane.showMessageDialog(panel, "Changes saved");
		
	}

}
