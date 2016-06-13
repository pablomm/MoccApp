package pads.antiquary.view;

import java.awt.Toolkit;
import javax.swing.*;

/**
 * JFrame with a loading gif
 * It's show when the application is loading the files
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 *
 */
public class Loading extends JFrame{
	
	/**
	 * Generated serial version
	 */
	private static final long serialVersionUID = -299278655756667042L;
	
	/**
	 * Title of the Window
	 */
	private static final String TITLE = "MoccApp";

	/**
	 * Path of the favicon
	 */
	private static final String ICON = "icons/favicon.png";
	
	/**
	 * Path of the loading animation
	 */
	private static final String LOADING = "icons/loading.gif";
	
	
	public Loading(){
		
		//Creating a window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(TITLE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(ICON));
		this.setSize(320, 330);
		
		//Adding the loading gif
		JLabel b = new JLabel(new ImageIcon(LOADING));
		b.setBounds(130, 100, 100, 40);
		this.add(b);
		
		//Opening in the center
		this.setLocationRelativeTo(null);
		
		//Setting visible the window
		this.setResizable(false);
		this.setVisible(true);
	}
	
	/**
	 * Closes the frame
	 */
	public void close(){
		this.setVisible(false);
		this.dispose();
	}
}
