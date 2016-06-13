package pads.antiquary.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import pads.antiquary.model.factory.Shop;
import pads.antiquary.view.Loading;
import pads.antiquary.view.LoginWindow;

/**
 * Class to control the action of logout
 * Erases the login user and launches a new login window
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 *
 */
public class LogoutController implements ActionListener {

	/**
	 * Window to be closed
	 */
	private JFrame window;

	/**
	 * Text of the Dialog
	 */
	private static final String TEXT = "Do you want to exit?";

	/**
	 * Title of the dialog
	 */
	private static final String TITLE = "MoccApp";

	public LogoutController(JFrame window) {
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (JOptionPane.showConfirmDialog(window, TEXT, TITLE, JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE) == 0) {
			Shop.getShop().logout();
			window.setVisible(false);
			window.dispose();
			JFrame load = new Loading();
			try {
				Shop.save();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(load, "Error saving data in the files");
				load.setVisible(false);
				new LoginWindow();
				return;
			}
			load.setVisible(false);
			load.dispose();
			JOptionPane.showMessageDialog(new LoginWindow(), "Information save, bye!");
			
		}

	}

}
