package pads.antiquary.view;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.*;

import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.people.*;

/**
 * Main frame of the application
 * It contains the employee/manager menu
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 *
 */
public class MainWindow extends JFrame {

	/**
	 * Generated serial Version UID
	 */
	private static final long serialVersionUID = 838748157767064427L;
	
	/**
	 * Path of the icon of the window
	 */
	private static final String ICON = "icons/favicon.png";
	
	/**
	 * Title of the frame
	 */
	private static final String TITLE = "MoccApp";
	
	/**
	 * Width of the frame
	 */
	private static final int WIDTH = 880;
	
	/**
	 * Height of the frame
	 */
	private static final int HEIGHT = 660;
	

	/**
	 * Constructor of the class, launch a new frame
	 */
	public MainWindow(){
		
		//Configurating the window
		setTitle(TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ICON));

		//Getting the user login
		User user = Shop.getShop().getUserLogin();
		
		//Error case
		if(user == null)
			JOptionPane.showMessageDialog(this, "You have to be login",TITLE, JOptionPane.ERROR_MESSAGE);
		
		//Case Manager
		else if(user.isManager())
			this.add(new ManagerMenu(this),BorderLayout.CENTER);
		
		//Case Employee
		else
			this.add(new EmployeeMenu(this),BorderLayout.CENTER);
		
		//Setting Visible
		setVisible(true);
	}
	
}
