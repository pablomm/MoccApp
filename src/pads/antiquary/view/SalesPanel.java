package pads.antiquary.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import pads.antiquary.controller.AddItemActionListener;
import pads.antiquary.controller.CustomerSaleTableMouseListener;
import pads.antiquary.controller.ItemSaleTableMouseListener;
import pads.antiquary.controller.NewSaleActionListener;
import pads.antiquary.controller.PurchaseActionListener;
import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.item.Item;
import pads.antiquary.model.people.Customer;
import pads.antiquary.model.sales.*;

/**
 * Class with the sales Panel
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 *
 */
public class SalesPanel extends JSplitPane {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = 5481630336762897344L;

	/**
	 * selected customer
	 */
	private Customer selectCustomer = null;

	/**
	 * selected item
	 */
	private Item selectItem = null;

	/**
	 * second selected item
	 */
	private Item selectItem2 = null;

	/**
	 * Location of the horizontal divider
	 */
	private static final int HORIZONTAL = 250;

	/**
	 * Location of the vertical divider1
	 */
	private static final int VERTICAL1 = 320;

	/**
	 * Location of the vertical divider1
	 */
	private static final int VERTICAL2 = 280;

	/**
	 * Header to the item table
	 */
	private static final String[] ITEM_HEADER = { "Description", "Date", "Price" };

	/**
	 * Header to the customer table
	 */
	private static final String[] CUSTOMER_HEADER = { "Name", "Contract", "ID" };

	/**
	 * item table
	 */
	private JTable itemTable = new CustomTable();

	/**
	 * customer table
	 */
	private JTable customerTable = new CustomTable();

	/**
	 * sales table
	 */
	private JTable saleTable = new CustomTable();

	/**
	 * selected sale
	 */
	private Sale sale = null;

	/**
	 * customer information
	 */
	private JTextField customerLabel = new JTextField(20);

	/**
	 * total price of the sale
	 */
	private JTextField totalPrice = new JTextField(20);

	/**
	 * price of the sale
	 */
	private JTextField price = new JTextField(20);

	/**
	 * discount
	 */
	private JTextField discount = new JTextField(20);

	/**
	 * delivery cost
	 */
	private JTextField delivery = new JTextField(20);

	/**
	 * creates the panel
	 */
	public SalesPanel() {

		// Setting main split panel
		super(JSplitPane.HORIZONTAL_SPLIT, true);
		this.setDividerLocation(HORIZONTAL);
		this.setResizeWeight(0.25);
		this.setOneTouchExpandable(true);
		this.setEnabled(false);

		this.itemTable.addMouseListener(new ItemSaleTableMouseListener(this));
		this.customerTable.addMouseListener(new CustomerSaleTableMouseListener(this));

		// Updating tables
		refreshPanel();
		updateItem();
		updateCustomer();
		

		// Adding left tables
		this.add(new TableSplitPanel(itemTable, customerTable, VERTICAL2, false));

		// Adding bids split panel
		JSplitPane salesPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		salesPanel.setDividerLocation(VERTICAL1);
		salesPanel.setEnabled(false);

		// Adding panel with buttons
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createTitledBorder("Sales"));

		// Adding labels

		JPanel subpanel1= new JPanel(new FlowLayout());
		subpanel1.add(new JLabel("Customer         "));
		subpanel1.add(customerLabel);
		customerLabel.setEditable(false);
		panel.add(subpanel1);
		
		JPanel subpanel2= new JPanel(new FlowLayout());
		subpanel2.add(new JLabel("Total price       "));
		subpanel2.add(totalPrice);
		totalPrice.setEditable(false);
		panel.add(subpanel2);
		
		JPanel subpanel3= new JPanel(new FlowLayout());
		subpanel3.add(new JLabel("Price                 "));
		subpanel3.add(price);
		price.setEditable(false);
		panel.add(subpanel3);
		
		JPanel subpanel4= new JPanel(new FlowLayout());
		subpanel4.add(new JLabel("Discount           "));
		subpanel4.add(discount);
		discount.setEditable(false);
		panel.add(subpanel4);
		
		JPanel subpanel5= new JPanel(new FlowLayout());
		subpanel5.add(new JLabel("Delivery cost    "));
		subpanel5.add(delivery);
		delivery.setEditable(false);
		panel.add(subpanel5);
		

		// Adding Buttons
		JPanel buttons = new JPanel(new FlowLayout());

		// Adding clear button
		JButton clear = new JButton("New Sale");
		clear.addActionListener(new NewSaleActionListener(this));
		clear.setSize(30, 20);
		buttons.add(clear);

		JButton addItem = new JButton("Add item");
		addItem.addActionListener(new AddItemActionListener(this));
		addItem.setSize(30, 20);
		buttons.add(addItem);

		// Adding New Bid button
		JButton purchase = new JButton("Purchase");
		purchase.addActionListener(new PurchaseActionListener(this));
		purchase.setSize(30, 20);
		buttons.add(purchase);

		panel.add(buttons);

		// Adding panel with buttons
		salesPanel.add(panel);

		// Adding panel with bidTable

		JPanel panelI = new JPanel();
		panelI.setLayout(new BorderLayout());
		panelI.add(saleTable, BorderLayout.CENTER);
		panelI.add(saleTable.getTableHeader(), BorderLayout.NORTH);
		salesPanel.add(panelI);

		// Adding right panel
		this.add(salesPanel);
	}

	/**
	 * refresh the panel
	 */
	public void refreshPanel() {
		if (this.sale == null) {
			this.totalPrice.setText("0");
			this.price.setText("0");
			this.discount.setText("0");
			this.delivery.setText("0");
		} else {
			this.totalPrice.setText("" + this.sale.getFinalPrice());
			this.price.setText("" + sale.getPrice());
			this.discount.setText("" + sale.getDiscount());
			this.delivery.setText("" + sale.getDeliveryCost());
		}

		if (this.sale != null) {
			this.customerLabel.setText(
					"Customer: " + this.sale.getCustomer().getName() + " " + this.sale.getCustomer().getSurname());

		} else {
			if (this.selectCustomer == null) {
				this.customerLabel.setText("Customer: -");
			} else
				this.customerLabel
						.setText("" + this.selectCustomer.getName() + " " + this.selectCustomer.getSurname());
		}

		updateSaleTable();
	}

	/**
	 * update the table
	 */
	public void updateSaleTable() {
		DefaultTableModel dtm = new DefaultTableModel();
		dtm.setColumnIdentifiers(ITEM_HEADER);
		if (this.sale != null)
			for (Item item : this.sale.getItems()) {
				Vector<Object> data = new Vector<Object>();
				data.add(item.getDescription());
				data.add(item.getManufactureYear());
				data.add(item.getTargetPrice());

				dtm.addRow(data);
			}

		saleTable.setModel(dtm);
	}

	/**
	 * Update the item Table
	 */
	public void updateItem() {

		DefaultTableModel dtm = new DefaultTableModel();

		dtm.setColumnIdentifiers(ITEM_HEADER);

		for (Item item : Shop.getShop().getStock()) {

			if (this.sale != null) {
				if (sale.getItems().contains(item))
					continue;
			}

			if (!item.isEnable())
				continue;
			Vector<Object> data = new Vector<Object>();
			data.add(item.getDescription());
			data.add(item.getManufactureYear());
			data.add(item.getTargetPrice());

			dtm.addRow(data);
		}
		itemTable.setModel(dtm);
	}

	/**
	 * Update the customer table
	 */
	public void updateCustomer() {
		DefaultTableModel dtm = new DefaultTableModel();

		dtm.setColumnIdentifiers(CUSTOMER_HEADER);

		for (Customer customer : Shop.getShop().getCustomers()) {

			Vector<Object> data = new Vector<Object>();
			data.add(customer.getName() + " " + customer.getSurname());

			data.add(customer.getContract().getType());
			data.add(Integer.valueOf(customer.getCustomerID()).toString());
			dtm.addRow(data);

		}
		customerTable.setModel(dtm);

	}

	/**
	 * @return the sale
	 */
	public Sale getSale() {
		return sale;
	}

	/**
	 * @param sale new sale
	 */
	public void setSale(Sale sale) {
		this.sale = sale;
	}

	/**
	 * @return the item table
	 */
	public JTable getItemTable() {
		return itemTable;
	}

	/**
	 * @return the customer table
	 */
	public JTable getCustomerTable() {
		return customerTable;
	}

	/**
	 * @return the sales table
	 */
	public JTable getSaleTable() {
		return saleTable;
	}

	/**
	 * @return the customer selected
	 */
	public Customer getSelectCustomer() {
		return selectCustomer;
	}

	/**
	 * @param selectCustomer new user selected
	 */
	public void setSelectCustomer(Customer selectCustomer) {
		this.selectCustomer = selectCustomer;
	}

	/**
	 * @return item selected
	 */
	public Item getSelectItem() {
		return selectItem;
	}

	/**
	 * @param selectItem new selected item
	 */
	public void setSelectItem(Item selectItem) {
		this.selectItem = selectItem;
	}

	/**
	 * @return item2 selected
	 */
	public Item getSelectItem2() {
		return selectItem2;
	}
	
	/**
	 * @param selectItem2 new second selected item
	 */
	public void setSelectItem2(Item selectItem2) {
		this.selectItem2 = selectItem2;
	}

}