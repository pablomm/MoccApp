package pads.antiquary;

import javax.swing.JOptionPane;

import pads.antiquary.model.factory.Shop;
import pads.antiquary.view.Loading;
import pads.antiquary.view.LoginWindow;

/**
 * Class with a main to launch the application
 * It loads the files and show the login window
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class Launcher {

	/**
	 * Main function to launch the application
	 * @param argv the params are ignored
	 */
	public static void main(String argv[]){
		
		Loading loading = new Loading();
		//Load the stored files
		try {
			Shop.getShop().loadFiles();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error loading the files");
		}
		//Showing the login window
		new LoginWindow();
		loading.close();
	}
	
}
