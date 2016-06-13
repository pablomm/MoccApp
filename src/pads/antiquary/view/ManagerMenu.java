package pads.antiquary.view;

import java.awt.ScrollPane;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import pads.antiquary.controller.LogoutController;

/**
 * Class with the tab panel of the manager menu
 * It contains the subpanels. If the size of the 
 * frame is too small a scroll bar appears
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 *
 */
public class ManagerMenu extends ScrollPane {

	/**
	 * Generated Serial version UID
	 */
	private static final long serialVersionUID = -7720524145146660826L;

	public ManagerMenu(JFrame window){
		
		
		JTabbedPane tab = new JTabbedPane();
		
		//Adding diferents tabs
		tab.addTab("Employees", null, new EmployeesPanel(), "Management of employees");
		tab.addTab("Auctions", null, new AuctionManagerPanel(), "Create and cancel auctions");
		tab.addTab("Sales Report", null, new SalesReportPanel(), "Sales history");
		tab.addTab("Settings", null, new SettingsPanel(), "Change the configuration");
		tab.addTab("", null, null);
		
		//Setting Border
		tab.setBorder(new EmptyBorder(15, 5, 5, 5));
		
		//Adding Logout button
		JButton logout = new JButton("Logout");
		logout.addActionListener(new LogoutController(window));
		tab.setTabComponentAt(tab.getTabCount() - 1, logout);
		tab.setEnabledAt(tab.getTabCount() - 1, false);
		logout.setOpaque (false); //
	    logout.setBorder (null);
	    logout.setContentAreaFilled (false);
	    logout.setFocusPainted (false);
	    logout.setFocusable (false);
	    
	    this.add(tab);
		
	}
}
