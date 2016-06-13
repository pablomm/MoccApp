package pads.antiquary.view;

import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import pads.antiquary.controller.ChangeCustomerActionListener;
import pads.antiquary.controller.CustomerClearController;
import pads.antiquary.controller.CustomerMouseListener;
import pads.antiquary.controller.NewCustomerController;
import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.people.Customer;
import pads.antiquary.model.people.Gender;
import pads.antiquary.model.people.Notificate;
import pads.antiquary.model.sales.ContractType;

/**
 * Class with the panel to create and modificate customers
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class CustomerPanel extends JSplitPane {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 1004301204352755399L;

	/**
	 * Header to the customer table
	 */
	private static final String[] HEADER = { "Name", "Contract", "ID" };

	/**
	 * Customer selected
	 */
	private Customer customer = null;

	/**
	 * Table with customers
	 */
	private JTable table = new CustomTable();

	/**
	 * name
	 */
	private JTextField name = new JTextField(20);

	/**
	 * surname
	 */
	private JTextField surname = new JTextField(20);

	/**
	 * adress
	 */
	private JTextField adress = new JTextField(20);

	/**
	 * mail
	 */
	private JTextField mail = new JTextField(20);

	/**
	 * phone
	 */
	private JTextField phone = new JTextField(20);

	/**
	 * id
	 */
	private JTextField id = new JTextField(3);

	/**
	 * contract
	 */
	private JComboBox<ContractType> contract = new JComboBox<>(ContractType.values());

	/**
	 * notification
	 */
	private JComboBox<Notificate> notification = new JComboBox<>(Notificate.values());

	/**
	 * gender
	 */
	private ButtonGroup gender = new ButtonGroup();

	/**
	 * male
	 */
	private JRadioButton male = new JRadioButton("MALE");

	/**
	 * female
	 */
	private JRadioButton female = new JRadioButton("FEMALE");

	/**
	 * it launches the panel
	 */
	public CustomerPanel() {
		// Setting main split panel
		super(JSplitPane.HORIZONTAL_SPLIT, true);
		this.setDividerLocation(250);
		this.setResizeWeight(0.5);
		this.setOneTouchExpandable(true);
		this.setEnabled(false);

		this.updateTable();
		table.addMouseListener(new CustomerMouseListener(this));
		this.add(new JScrollPane(table));

		// Adding Fields
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
		fieldPanel.setBorder(BorderFactory.createTitledBorder("Information"));

		JPanel subpanel1 = new JPanel(new FlowLayout());
		subpanel1.add(new JLabel("Customer ID"));
		id.setEnabled(false);
		subpanel1.add(this.id);
		fieldPanel.add(subpanel1);

		JPanel subpanel2 = new JPanel(new FlowLayout());
		subpanel2.add(new JLabel("Name   "));
		subpanel2.add(this.name);
		fieldPanel.add(subpanel2);

		JPanel subpanel3 = new JPanel(new FlowLayout());
		subpanel3.add(new JLabel("Surame"));
		subpanel3.add(this.surname);
		fieldPanel.add(subpanel3);

		JPanel subpanel8 = new JPanel(new FlowLayout());
		contract.setLightWeightPopupEnabled(false);
		notification.setLightWeightPopupEnabled(false);
		subpanel8.add(this.contract);
		subpanel8.add(this.notification);
		fieldPanel.add(subpanel8);

		JPanel subpanel4 = new JPanel(new FlowLayout());
		subpanel4.add(new JLabel("Email "));
		subpanel4.add(this.mail);
		fieldPanel.add(subpanel4);

		JPanel subpanel5 = new JPanel(new FlowLayout());
		subpanel5.add(new JLabel("Adress"));
		subpanel5.add(this.adress);
		fieldPanel.add(subpanel5);

		JPanel subpanel6 = new JPanel(new FlowLayout());
		subpanel6.add(new JLabel("Phone "));
		subpanel6.add(this.phone);
		fieldPanel.add(subpanel6);

		JPanel subpanel7 = new JPanel(new FlowLayout());
		gender.add(male);
		gender.add(female);
		subpanel7.add(this.male);
		subpanel7.add(this.female);
		fieldPanel.add(subpanel7);

		JPanel buttons = new JPanel(new FlowLayout());
		JButton clear = new JButton("Clear");
		clear.addActionListener(new CustomerClearController(this));
		buttons.add(clear);

		JButton update = new JButton("Update");
		update.addActionListener(new ChangeCustomerActionListener(this));
		buttons.add(update);

		JButton newc = new JButton("New");
		newc.addActionListener(new NewCustomerController(this));
		buttons.add(newc);

		// Adding panel into right panel
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.setBorder(BorderFactory.createTitledBorder("Customers"));

		rightPanel.add(fieldPanel);
		rightPanel.add(buttons);

		this.add(rightPanel);

	}

	/**
	 * refresh the fields
	 */
	public void refreshPanel() {
		if (this.customer == null) {
			clear();
		} else {

			name.setText(customer.getName());
			surname.setText(customer.getSurname());
			adress.setText(customer.getAdress());
			mail.setText(customer.getEmail());
			phone.setText(customer.getPhone()+"");
			id.setText(customer.getCustomerID()+"");
			contract.setSelectedItem(customer.getContract().getType());
			notification.setSelectedItem(customer.getNotification());
			male.setSelected((customer.getGender().equals(Gender.MALE)));
			female.setSelected((customer.getGender().equals(Gender.FEMALE)));
		}
	}

	/**
	 * clear the fields
	 */
	public void clear() {
		name.setText("");
		surname.setText("");
		adress.setText("");
		mail.setText("");
		phone.setText("");
		id.setText(" - ");
		contract.setSelectedIndex(0);
		notification.setSelectedIndex(0);
		male.setSelected(false);
		female.setSelected(false);
	}

	/**
	 * update the customer table
	 */
	public void updateTable() {
		DefaultTableModel dtm = new DefaultTableModel();

		dtm.setColumnIdentifiers(HEADER);

		for (Customer customer : Shop.getShop().getCustomers()) {

			Vector<Object> data = new Vector<Object>();
			data.add(customer.getName() + " " + customer.getSurname());

			data.add(customer.getContract().getType());
			data.add(Integer.valueOf(customer.getCustomerID()).toString());
			dtm.addRow(data);

		}
		table.setModel(dtm);

	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * @return the name
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
	 * @return the adress
	 */
	public String getAdress() {
		return adress.getText();
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail.getText();
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone.getText();
	}

	
	/**
	 * @return the type of contract
	 */
	public ContractType getContract() {
		return (ContractType)contract.getSelectedItem();
	}

	/**
	 * @return the type of notification
	 */
	public Notificate getNotification() {
		return (Notificate)notification.getSelectedItem();
	}
	
	/**
	 * @return the male state
	 */
	public boolean getMale() {
		return male.isSelected();
	}

	/**
	 * @return the fenale state
	 */
	public boolean getFemale() {
		return female.isSelected();
	}

}
