package pads.antiquary.view;

import java.awt.FlowLayout;

import javax.swing.*;

import pads.antiquary.controller.EmployeeClearController;
import pads.antiquary.controller.NewEmployeeController;

/**
 * Class with the panel to create new employees
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class NewEmployeePanel extends JPanel {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 564909846717763305L;

	/**
	 * userName
	 */
	private JTextField username = new JTextField(20);

	/**
	 * userName
	 */
	private JTextField name = new JTextField(20);

	/**
	 * userName
	 */
	private JTextField surname = new JTextField(20);

	/**
	 * email
	 */
	private JTextField email = new JTextField(20);

	/**
	 * phone
	 */
	private JTextField phone = new JTextField(20);

	/**
	 * gender button group
	 */
	private ButtonGroup gender = new ButtonGroup();

	/**
	 * adress
	 */
	private JTextField adress = new JTextField(20);
	/**
	 * password
	 */
	private JPasswordField pass = new JPasswordField(20);

	/**
	 * confirm password
	 */
	private JPasswordField pass2 = new JPasswordField(20);

	/**
	 * male radio button
	 */
	private JRadioButton male = new JRadioButton("MALE");

	/**
	 * female radio button
	 */
	private JRadioButton female = new JRadioButton("FEMALE");

	/**
	 * table with employees
	 */
	private JTable employees = new CustomTable();
	
	/**
	 * Types of querys
	 */
	private static final String TYPES[] = { "Employee","Manager" };
	
	/**
	 * Combo box with type of employees
	 */
	private JComboBox<String> type = new JComboBox<String>(TYPES);

	/**
	 * @param modpanel Panel to modificate employees with 
	 * associated table
	 */
	public NewEmployeePanel(ModEmployeePanel modpanel) {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createTitledBorder("Employees"));

		// Adding Fields
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
		fieldPanel.setBorder(BorderFactory.createTitledBorder("Information"));

		//Adding combo box
		type.setLightWeightPopupEnabled(false);
		JPanel subpanel = new JPanel(new FlowLayout());
		subpanel.add(type);
		fieldPanel.add(subpanel);
		
		
		
		// Adding User Name
		JPanel subpanel1 = new JPanel(new FlowLayout());
		subpanel1.add(new JLabel("User name  "));
		subpanel1.add(username);
		fieldPanel.add(subpanel1);

		// Adding Name
		JPanel subpanel2 = new JPanel(new FlowLayout());
		subpanel2.add(new JLabel("Name         "));
		subpanel2.add(name);
		fieldPanel.add(subpanel2);

		// Adding Surname
		JPanel subpanel3 = new JPanel(new FlowLayout());
		subpanel3.add(new JLabel("Surname    "));
		subpanel3.add(surname);
		fieldPanel.add(subpanel3);

		// Adding email
		JPanel subpanel4 = new JPanel(new FlowLayout());
		subpanel4.add(new JLabel("Email        "));
		subpanel4.add(email);
		fieldPanel.add(subpanel4);

		// Adding adress
		JPanel subpanel5 = new JPanel(new FlowLayout());
		subpanel5.add(new JLabel("Adress      "));
		subpanel5.add(this.adress);
		fieldPanel.add(subpanel5);

		// Adding phone
		JPanel subpanel6 = new JPanel(new FlowLayout());
		subpanel6.add(new JLabel("Phone       "));
		subpanel6.add(this.phone);
		fieldPanel.add(subpanel6);

		// Adding gender
		this.gender.add(male);
		this.gender.add(female);
		JPanel subpanel7 = new JPanel(new FlowLayout());
		subpanel7.add(male);
		subpanel7.add(female);
		fieldPanel.add(subpanel7);

		// Password Panel
		JPanel passwordPanel = new JPanel();
		passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.Y_AXIS));
		passwordPanel.setBorder(BorderFactory.createTitledBorder("Password"));

		// Adding Password field 1
		JPanel subpanel8 = new JPanel(new FlowLayout());
		subpanel8.add(new JLabel("Password              "));
		subpanel8.add(this.pass);
		passwordPanel.add(subpanel8);

		// Adding Password field 2
		JPanel subpanel9 = new JPanel(new FlowLayout());
		subpanel9.add(new JLabel("Confirm password"));
		subpanel9.add(this.pass2);
		passwordPanel.add(subpanel9);

		// Adding buttons
		JPanel buttons = new JPanel(new FlowLayout());

		// Save button
		JButton save = new JButton("Save");
		save.addActionListener(new NewEmployeeController(this,modpanel));
		buttons.add(save);

		// Clear button
		JButton clear = new JButton("Clear");
		clear.addActionListener(new EmployeeClearController(this));
		buttons.add(clear);

		this.add(fieldPanel);
		this.add(passwordPanel);
		this.add(buttons);

	}

	/**
	 * Clear the fields
	 */
	public void clearFields() {
		username.setText("");
		name.setText("");
		surname.setText("");
		email.setText("");
		phone.setText("");
		adress.setText("");
		pass.setText("");
		pass2.setText("");
		male.setSelected(false);
		female.setSelected(false);
	}

	/**
	 * @return the employees
	 */
	public JTable getEmployees() {
		return employees;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username.getText();
	}

	/**
	 * @return The name
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname.getText();
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email.getText();
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone.getText();
	}

	/**
	 * @return the adress
	 */
	public String getAdress() {
		return adress.getText();
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return String.valueOf(pass.getPassword());
	}

	/**
	 * @return the second pass
	 */
	public String getPass2() {
		return String.valueOf(pass2.getPassword());
	}

	/**
	 * @return male check
	 */
	public boolean getMale() {
		return male.isSelected();
	}

	/**
	 * @return female check
	 */
	public boolean getFemale() {
		return female.isSelected();
	}

	/**
	 * @return type of employee index (0 employee 1 manager)
	 */
	public int getType() {
		return type.getSelectedIndex();
	}

}
