package pads.antiquary.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import pads.antiquary.model.exceptions.InvalidPassword;
import pads.antiquary.model.exceptions.NotFound;
import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.people.User;
import pads.antiquary.view.LoginPanel;
import pads.antiquary.view.LoginWindow;
import pads.antiquary.view.MainWindow;

/**
 * Class that contains the controller
 * of the login window
 * Checks the user and the password and throws
 * a new window if it's all right
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class LoginController implements ActionListener {

	/**
	 * Panel of the login window
	 */
	private LoginPanel panel;
	
	/**
	 * Window that contains the panel
	 */
	private LoginWindow window;
	
	/**
	 * Constructor of the class LoginController
	 * @param window that contains the panel
	 * @param panel of the login window
	 */
	public LoginController(LoginWindow window, LoginPanel panel){
		this.panel = panel;
		this.window = window;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String usr = String.valueOf(panel.getLogin());
		
		try {
			if (Shop.getShop().login(usr, String.valueOf(panel.getPassword()) ) == true ){
				JOptionPane.showMessageDialog(panel, "Welcome " + usr);
				
				User user = Shop.getShop().getUserLogin();					
				
				if(user != null){
					new MainWindow();
					window.setVisible(false);
					window.dispose();
					
				} else 
					JOptionPane.showMessageDialog(panel, "Error in the login");
				
			} else //Incorrect password case

				JOptionPane.showMessageDialog(panel, "Incorrect password, please try again");
		
		//User not found
		} catch (NotFound e1) {
			JOptionPane.showMessageDialog(panel, "User " + usr + " not found\n" + "Please check the username");
			
		//Password field empty
		} catch (InvalidPassword e1) {
			JOptionPane.showMessageDialog(panel, "Please introduce the password");
		}
	}

}
