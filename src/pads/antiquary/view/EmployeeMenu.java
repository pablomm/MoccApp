package pads.antiquary.view;

import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import pads.antiquary.controller.LogoutController;

/**
 * Class with the Employee Menu
 * It contains the tab menu, and
 * a scroll bar panel, that appears
 * when the frame it is too small
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 *
 */
public class EmployeeMenu extends ScrollPane {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 8548104987316958707L;
	
	/**
	 * it launches the panel
	 * @param window
	 */
	public EmployeeMenu(JFrame window){
		
		JTabbedPane tab = new JTabbedPane();
		
		//Adding diferents tabs
		tab.addTab("Search", null, new SearchPanel(), "Find objects");
		tab.addTab("Customers", null, new CustomerPanel(), "Register of clientes");
		tab.addTab("Sales", null, new SalesPanel(), "Where M$NEY is maked");
		tab.addTab("Auctions", null, new AuctionPanel(), "Make bids");
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
	    
	    //Adding tab menu
	    this.add(tab);
	}
}
