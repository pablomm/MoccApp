package pads.antiquary.view;

import javax.swing.JLabel;

import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import pads.antiquary.controller.ChangePasswordController;
import pads.antiquary.controller.DeleteEmployeeControler;
import pads.antiquary.controller.SaveEmployeeChangeController;
import pads.antiquary.controller.UserTableMouseListener;
import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.people.Gender;
import pads.antiquary.model.people.User;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Class with the panel to modificate employees
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 *
 */
public class ModEmployeePanel extends JSplitPane {

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
	 * gender
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
	 * second password
	 */
	private JPasswordField pass2 = new JPasswordField(20);

	/**
	 * male radiobutton
	 */
	private JRadioButton male = new JRadioButton("MALE");

	/**
	 * female radiobutton
	 */
	private JRadioButton female = new JRadioButton("FEMALE");

	/**
	 * Employees table
	 */
	private JTable employees = new CustomTable();

	/**
	 * header to the table
	 */
	private static final String[] HEADER = { "UserName", "Name", "Type" };

	/**
	 * User selected
	 */
	private User userSelected = null;

	/**
	 * Creates the panel
	 */
	public ModEmployeePanel() {

		// Setting main split panel
		super(JSplitPane.HORIZONTAL_SPLIT, true);
		this.setDividerLocation(250);
		this.setResizeWeight(0.5);
		this.setOneTouchExpandable(true);
		this.setEnabled(false);

		// Adding table
		employees.addMouseListener(new UserTableMouseListener(this));
		this.add(new JScrollPane(employees));

		// Adding Fields
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
		fieldPanel.setBorder(BorderFactory.createTitledBorder("Information"));

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
		subpanel8.add(new JLabel("Old Password"));
		subpanel8.add(this.pass);
		passwordPanel.add(subpanel8);

		// Adding Password field 2
		JPanel subpanel9 = new JPanel(new FlowLayout());
		subpanel9.add(new JLabel("New Password"));
		subpanel9.add(this.pass2);
		passwordPanel.add(subpanel9);

		// Adding buttons
		JPanel buttons = new JPanel(new FlowLayout());

		// Delete button
		JButton delete = new JButton("Delete");
		delete.addActionListener(new DeleteEmployeeControler(this));
		buttons.add(delete);

		// Save button
		JButton save = new JButton("Save");
		save.addActionListener(new SaveEmployeeChangeController(this));
		buttons.add(save);

		// Change password button
		JButton change = new JButton("Change Password");
		change.addActionListener(new ChangePasswordController(this));
		buttons.add(change);

		// Adding panel into right panel
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.setBorder(BorderFactory.createTitledBorder("Employees"));

		rightPanel.add(fieldPanel);
		rightPanel.add(passwordPanel);
		rightPanel.add(buttons);

		this.add(rightPanel);

		// Updating table
		updateEmployees();
	}

	/**
	 * Update the employees table
	 */
	public void updateEmployees() {

		DefaultTableModel dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(HEADER);

		for (User u : Shop.getShop().getUsers()) {
			Vector<Object> data = new Vector<Object>();
			data.add(u.getUserName());
			data.add(u.getName() + " " + u.getSurname());
			data.add((u.isManager()) ? "Manager" : "Employee");

			dtm.addRow(data);
		}

		this.employees.setModel(dtm);
		updateFields();
	}

	/**
	 * update the fields
	 */
	public void updateFields() {
		if (this.userSelected == null) {
			clearFields();
		} else {
			username.setText(userSelected.getUserName());
			name.setText(userSelected.getName());
			surname.setText(userSelected.getSurname());
			email.setText(userSelected.getEmail());
			phone.setText(userSelected.getPhone() + "");
			adress.setText(userSelected.getAdress());
			pass.setText("");
			pass2.setText("");
			male.setSelected(userSelected.getGender().equals(Gender.MALE));
			female.setSelected(userSelected.getGender().equals(Gender.FEMALE));
		}
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
	 * @return the selected user
	 */
	public User getUserSelected() {
		return userSelected;
	}

	/**
	 * @param userSelected new selected user
	 */
	public void setUserSelected(User userSelected) {
		this.userSelected = userSelected;
	}

	/**
	 * @return employees table
	 */
	public JTable getEmployees() {
		return employees;
	}

	/**
	 * @return username 
	 */
	public String getUsername() {
		return username.getText();
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * @return surname
	 */
	public String getSurname() {
		return surname.getText();
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email.getText();
	}

	/**
	 * @return phone
	 */
	public String getPhone() {
		return phone.getText();
	}

	/**
	 * @return adress
	 */
	public String getAdress() {
		return adress.getText();
	}

	/**
	 * @return pass
	 */
	public String getPass() {
		return String.valueOf(pass.getPassword());
	}

	/**
	 * @return second pass
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

}
