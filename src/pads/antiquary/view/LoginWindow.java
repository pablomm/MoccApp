package pads.antiquary.view;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * Class that contains the login window
 * This is the first window in the application
 * It is used to login in the application
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class LoginWindow extends JFrame {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -4730431597229348258L;
	
	/**
	 * Title of the window
	 */
	private static final String TITLE = "MoccApp";
	
	/**
	 * Path of the favicon
	 */
	private static final String ICON = "icons/favicon.png";
	
	/**
	 * Width of the window
	 */
	private static final int WIDTH = 400;
	
	/**
	 * Height of the window
	 */
	private static final int HEIGHT = 500;
	

	/**
	 * Constructor of the class
	 * Launches the login window
	 */
	public LoginWindow() {
		
		// creating a window
		this.setTitle(TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(ICON));
		this.setResizable(false);
		
		//Opening the window in the center of the screen
		this.setSize(WIDTH, HEIGHT);
		
		//Setting Layout
		this.getContentPane().setLayout(new BorderLayout());

		//Adding the panel
		this.getContentPane().add(new LoginPanel(this));

		//Opening in the center
		this.setLocationRelativeTo(null);
		
		//Setting visible
		this.setVisible(true);
	}
}
