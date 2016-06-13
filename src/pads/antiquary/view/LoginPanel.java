package pads.antiquary.view;

import java.awt.*;

import javax.swing.*;

import pads.antiquary.controller.LoginController;

/**
 * Class of the LoginPanel
 * This panel contains two fields, one for the username
 * an other for the password. And a submit button too  
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class LoginPanel extends JPanel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -307524897592695421L;
	
	/**
	 * Path of the favicon
	 */
	private static final String ICON = "icons/favicon.png";
	
	
	/**
	 * Field to store the useraram window that contains the panel
	 */
	private JTextField user = new JTextField(20);
	
	/**
	 * Field to store the password
	 */
	private JPasswordField pass = new JPasswordField(20);
	
	/**
	 * Constructor of the LoginPanel
	 * This panel contains two fields, one for the username
	 * an other for the password. And a submit button too  
	 * @param window that contains the panel
	 */
	public LoginPanel(LoginWindow window){
		
		this.setBounds(0, 0, 400, 500);
		this.setLayout(null);
		
		//Adding the image
		JLabel icon = new JLabel(new ImageIcon(ICON));
		icon.setBounds(152, 25, 95, 95);
		this.add(icon);
			
		//Adding the user label
		JLabel userlabel = new JLabel("USERNAME");
		userlabel.setBounds(162, 170, 75, 25);
		this.add(userlabel);

		//Adding user text field
		user.setBounds(80, 190, 240, 25);
		user.addActionListener(new LoginController(window,this));
		this.add(user);
	
		//Adding password label
		JLabel passLabel = new JLabel("PASSWORD");
		passLabel.setBounds(160, 230, 80, 25);
		this.add(passLabel);
	
		//Adding password field
		pass.setBounds(80, 250, 240, 25);
		pass.addActionListener(new LoginController(window,this));
		this.add(pass);
		
		//Adding the login button
		JButton confirmar = new JButton("LOGIN");
		confirmar.addActionListener(new LoginController(window,this));
		confirmar.setBounds(160, 320, 80, 30);
		this.add(confirmar);
	
	}
	
	/**
	 * @return the username introduced by the user
	 */
	public String getLogin() {
		return this.user.getText();
	}
	
	/**
	 * @return the password introduced by the user
	 */
	public String getPassword() {
		return String.valueOf(this.pass.getPassword());
	}
	
	@Override
    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        GradientPaint gp = new GradientPaint(0, 0, Color.ORANGE, 0, getHeight(), Color.GRAY);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

}
