package pads.antiquary.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import pads.antiquary.controller.AuctionBidController;
import pads.antiquary.controller.AuctionEntryController;
import pads.antiquary.controller.AuctionTableMouseListener;
import pads.antiquary.controller.CustomerTableMouseListener;
import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.item.Item;
import pads.antiquary.model.misc.Settings;
import pads.antiquary.model.people.Customer;
import pads.antiquary.model.sales.*;

/**
 * Class with the panel to make bids
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class AuctionPanel extends JSplitPane {

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -4773055158247811742L;
	
	/**
	 * Header to the bid table
	 */
	private static final String[] BID_HEADER = { "Customer", "Manufactured date", "Price" };
	
	/**
	 * Header to the auction table
	 */
	private static final String[] AUCTION_HEADER = { "Auction", "Higest Bid", "Active" };
	
	/**
	 * Header to the customer table
	 */	
	private static final String[] CUSTOMER_HEADER = { "Name", "Contract","ID"};
	
	/**
	 * Header to the item table
	 */	
	private static final String[] ITEM_HEADER = { "Description", "Date","Price"};
	
	/**
	 * Location of the vertical divider1
	 */
	private static final int VERTICAL1 = 350;
	
	/**
	 * Location of the vertical divider1
	 */
	private static final int VERTICAL2 = 280;
	
	/**
	 * Location of the horizontal divider
	 */
	private static final int HORIZONTAL = 250;
	
	/**
	 * Table with the auctions
	 */
	private JTable auctionTable = new CustomTable();

	/**
	 * Table with the customers
	 */
	private JTable customerTable = new CustomTable();
	
	/**
	 * Table with the customers
	 */
	private JTable itemTable = new CustomTable();

	/**
	 * Table with the bids of the selected auction
	 */
	private JTable bidTable = new CustomTable();

	/**
	 * Customer selected
	 */
	private Customer customerSelected = null;

	/**
	 * Auction selected
	 */
	private Auction auctionSelected = null;
	
	/**
	 * Auction name label
	 */
	private JTextField auctionName = new JTextField(20);
	
	/**
	 * Auction description
	 */
	private JTextField auctionDescription = new JTextField(20);
	
	/**
	 * Auction target price
	 */
	private JTextField targetPrice = new JTextField(20);
	
	/**
	 * Auction entry price
	 */
	private JTextField entryPrice = new JTextField(20);
	
	/**
	 * Customer name
	 */
	private JTextField customerName = new JTextField(20);
	
	/**
	 * Number of free entries
	 */
	private JTextField freeEntrys = new JTextField(20);
	
	/**
	 * Auction date
	 */
	private JTextField auctionDate = new JTextField(20);
	
	/**
	 * Highest bid
	 */
	private JTextField highestBid = new JTextField(20);
	
	/**
	 * Auction bid
	 */
	private JTextField active = new JTextField(20);
	
	/**
	 * Bid Price
	 */
	private JTextField bidPrice = new JFormattedTextField("                                        ");

	/**
	 * Creates a new Auction panel
	 */
	public AuctionPanel() {
		
		// Setting main split panel
		super(JSplitPane.HORIZONTAL_SPLIT, true);
		this.setDividerLocation(HORIZONTAL);
		this.setResizeWeight(0.25);
		this.setOneTouchExpandable(true);
		this.setEnabled(false);
		
		//Adding table controllers
		auctionTable.addMouseListener(new AuctionTableMouseListener(this));
		customerTable.addMouseListener(new CustomerTableMouseListener(this));
		
		// Updating tables
		refreshPanel();
		updateAuction();
		updateCustomer();

		// Adding left tables
		this.add(new TableSplitPanel(auctionTable, customerTable, VERTICAL2, false));

		// Adding bids split panel
		JSplitPane bidPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		bidPanel.setDividerLocation(VERTICAL1);
		bidPanel.setEnabled(true);
		
		//Adding panel with buttons
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createTitledBorder("Auction"));
		
		//Adding labels
		
		JPanel subpanel1 = new JPanel(new FlowLayout());
		subpanel1.add(new JLabel("Auction Name"));
		subpanel1.add(auctionName);
		auctionName.setEditable(false);
		panel.add(subpanel1);
		
		JPanel subpanel2 = new JPanel(new FlowLayout());
		subpanel2.add(new JLabel("Description   "));
		subpanel2.add(auctionDescription);
		auctionDescription.setEditable(false);
		panel.add(subpanel2);
		
		
		JPanel subpanel3 = new JPanel(new FlowLayout());
		subpanel3.add(new JLabel("Target Price  "));
		subpanel3.add(targetPrice);
		targetPrice.setEditable(false);
		panel.add(subpanel3);
		
		JPanel subpanel4 = new JPanel(new FlowLayout());
		subpanel4.add(new JLabel("Entry Price     "));
		subpanel4.add(entryPrice);
		entryPrice.setEditable(false);
		panel.add(subpanel4);
		
		
		JPanel subpanel5= new JPanel(new FlowLayout());
		subpanel5.add(new JLabel("Active             "));
		subpanel5.add(active);
		active.setEditable(false);
		panel.add(subpanel5);
		
		JPanel subpanel6= new JPanel(new FlowLayout());
		subpanel6.add(new JLabel("Date              "));
		subpanel6.add(auctionDate);
		auctionDate.setEditable(false);
		panel.add(subpanel6);
		
		JPanel subpanel7= new JPanel(new FlowLayout());
		subpanel7.add(new JLabel("Highest bid   "));
		subpanel7.add(highestBid);
		highestBid.setEditable(false);
		panel.add(subpanel7);
		
		JPanel subpanel8= new JPanel(new FlowLayout());
		subpanel8.add(new JLabel("Customer       "));
		subpanel8.add(customerName);
		customerName.setEditable(false);
		panel.add(subpanel8);
		
		JPanel subpanel9= new JPanel(new FlowLayout());
		subpanel9.add(new JLabel("Free entries  "));
		subpanel9.add(freeEntrys);
		freeEntrys.setEditable(false);
		panel.add(subpanel9);
		
		
		//Adding bid price
		AuctionBidController bidController = new AuctionBidController(this);
		
		JPanel form = new JPanel(new FlowLayout());
		bidPrice.addActionListener(bidController);
		
		JLabel bidLabel = new JLabel ("Bid Price: ");
		form.add(bidLabel);
		form.add(bidPrice);
		panel.add(form);
		
		//Adding Buttons
		JPanel buttons = new JPanel(new FlowLayout());
		
		//Adding entry button
		JButton entry = new JButton("Pay entry");
		entry.addActionListener(new AuctionEntryController(this));
		entry.setSize(30, 20);
		buttons.add(entry);
		
		//Adding New Bid button
		JButton bid = new JButton("New bid");
		bid.addActionListener(bidController);
		bid.setSize(30, 20);
		buttons.add(bid);
		
		panel.add(buttons);
		
		//Adding panel with buttons
		bidPanel.add(panel);
		
		//Adding panel with bidTable
		
		JTabbedPane lowPane = new JTabbedPane();
		
		JPanel panelI = new JPanel();
		panelI.setLayout(new BorderLayout());
		panelI.add(itemTable, BorderLayout.CENTER);
		panelI.add(itemTable.getTableHeader(), BorderLayout.NORTH);
		lowPane.addTab("Items", panelI);
		
		JPanel panelT = new JPanel();
		panelT.setLayout(new BorderLayout());
		panelT.add(bidTable, BorderLayout.CENTER);
		panelT.add(bidTable.getTableHeader(), BorderLayout.NORTH);
		lowPane.addTab("Bid", panelT);
		
		bidPanel.add(lowPane);

		//Adding right panel
		this.add(bidPanel);
	}

	/**
	 * @return the customer's table
	 */
	public JTable getCustomerTable() {
		return customerTable;
	}

	/**
	 * @return the customer selected
	 */
	public Customer getCustomerSelected() {
		return customerSelected;
	}

	/**
	 * @param customerSelected the customer selected
	 */
	public void setCustomerSelected(Customer customerSelected) {
		this.customerSelected = customerSelected;
	}

	/**
	 * @return the auction table
	 */
	public JTable getAuctionTable() {
		return auctionTable;
	}

	/**
	 * @return the auction selected
	 */
	public Auction getAuctionSelected() {
		return auctionSelected;
	}

	/**
	 * @param auctionSelected the new auction
	 */
	public void setAuctionSelected(Auction auctionSelected) {
		this.auctionSelected = auctionSelected;
	}

	/**
	 * Refresh the fields
	 */
	public void refreshPanel() {
		
		if(this.auctionSelected != null){
			auctionName.setText(this.auctionSelected.getName());
			auctionDescription.setText(auctionSelected.getDescription() );
			targetPrice.setText(""+ auctionSelected.getTargetPrice());
			entryPrice.setText("" + auctionSelected.getEntryPrice());
			
			active.setText( auctionSelected.isActive() ? "Active" : "Finished");
			Bid highestBid = auctionSelected.getLastBid();
			if(highestBid != null){
			
			auctionDate.setText("" + highestBid.getDate().plusHours(auctionSelected.getDuration()));
			this.highestBid.setText("" + highestBid.getPrice());
			} else {
				auctionDate.setText("");
				this.highestBid.setText("");
			}
			
		} else {
			auctionName.setText("");
			auctionDescription.setText("");
			targetPrice.setText("");
			entryPrice.setText("");
			active.setText("");
			auctionDate.setText("");
			this.highestBid.setText("");
		}
		
		if(this.customerSelected != null){
			customerName.setText("" + customerSelected.getName() + " " + customerSelected.getSurname());
			
			if(customerSelected.getContract().getType().equals(ContractType.Vip)){
				freeEntrys.setText("All");
			} else {
			
			int n = Settings.getSTANDARD_FREE_ENTRYS() - customerSelected.getNumberAuctions();
			freeEntrys.setText("" + ((n < 0) ? "0" : n));
			}
			
			
		} else {
			customerName.setText(" -");
			freeEntrys.setText(" -");
		}
		
		updateItem();

		DefaultTableModel dtm = new DefaultTableModel();

		
		dtm.setColumnIdentifiers(BID_HEADER);

		if (this.auctionSelected == null){
			this.bidTable.setModel(dtm);
			return;
		}
			

		for (Bid bid : this.auctionSelected.getBid()) {

			Vector<Object> data = new Vector<Object>();
			data.add(bid.getCustomer().getName() + " " + bid.getCustomer().getSurname());

			data.add(bid.getDate());
			data.add(bid.getPrice());
			dtm.addRow(data);

		}
		this.bidTable.setModel(dtm);

	}
	
	/**
	 * Update the auction Table
	 */
	public void updateAuction() {
		
		DefaultTableModel dtm = new DefaultTableModel();

		dtm.setColumnIdentifiers(AUCTION_HEADER);

		for (Auction auction : Shop.getShop().getAuctions()) {

			Vector<Object> data = new Vector<Object>();
			data.add(auction.getName());

			if (auction.getLastBid() == null)
				data.add(auction.getTargetPrice());
			else
				data.add(auction.getLastBid().getPrice());

			data.add(auction.isActive() ? "Active" : "Finished");
			dtm.addRow(data);

		}
		auctionTable.setModel(dtm);

	}
	
	/**
	 * Update the item Table
	 */
	public void updateItem() {
		
		DefaultTableModel dtm = new DefaultTableModel();

		dtm.setColumnIdentifiers(ITEM_HEADER);
		
		if(this.auctionSelected == null){
			itemTable.setModel(dtm);
			return;
		}

		for (Item item : this.auctionSelected.getItem().getItems()) {

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
	 * @return the value of the field
	 */
	public Double getBidPrice() {
		try {
			Double.parseDouble(bidPrice.getText());
		} catch (Exception e){
			JOptionPane.showMessageDialog(this, "Invalid Value");
			return -1.0;
		}
		
		return Double.parseDouble(bidPrice.getText());
	}

}
