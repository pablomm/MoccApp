package pads.antiquary.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import pads.antiquary.controller.AddItemAuctionControler;
import pads.antiquary.controller.AuctionManagerMouseListener;
import pads.antiquary.controller.ChangeAuctionControler;
import pads.antiquary.controller.ClearManagerAuction;
import pads.antiquary.controller.ItemAuctionMouseListener;
import pads.antiquary.controller.NewAuctionControler;
import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.item.Item;
import pads.antiquary.model.item.Lot;
import pads.antiquary.model.sales.Auction;

/**
 * Class with the panel to create and modificate the auctions
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class AuctionManagerPanel extends JSplitPane {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -2008846218373576900L;

	/**
	 * Header to the item table
	 */
	private static final String[] ITEM_HEADER = { "Description", "Date", "Price" };

	/**
	 * Header to the auction table
	 */
	private static final String[] AUCTION_HEADER = { "Auction", "Higest Bid", "Active" };

	/**
	 * Selected auction
	 */
	private Auction auction = null;

	/**
	 * Selected lot
	 */
	private Lot lot = new Lot();
	
	/**
	 * Selected item
	 */
	private Item item = null;

	/**
	 * Table with auctions
	 */
	private JTable auctionTable = new CustomTable();

	/**
	 * Table with items
	 */
	private JTable itemTable = new CustomTable();

	/**
	 * Table with lots
	 */
	private JTable lotTable = new CustomTable();

	/**
	 * Name
	 */
	private JTextField name = new JTextField(20);

	/**
	 * Description
	 */
	private JTextField description = new JTextField(20);

	/**
	 * Target price
	 */
	private JTextField targetPrice = new JTextField(20);

	/**
	 * Entry price
	 */
	private JTextField entryPrice = new JTextField(20);
	
	/**
	 * duration
	 */
	private JTextField duration = new JTextField(20);

	/**
	 * button group for active
	 */
	private ButtonGroup act = new ButtonGroup();

	/**
	 * active state
	 */
	private JRadioButton active = new JRadioButton("Active");

	/**
	 * finished state
	 */
	private JRadioButton finished = new JRadioButton("Finished");

	/**
	 * launches the panel
	 */
	public AuctionManagerPanel() {
		// Setting main split panel
		super(JSplitPane.HORIZONTAL_SPLIT, true);
		this.setDividerLocation(250);
		this.setResizeWeight(0.5);
		this.setOneTouchExpandable(true);
		this.setEnabled(false);

		this.auctionTable.addMouseListener(new AuctionManagerMouseListener(this));
		this.itemTable.addMouseListener(new ItemAuctionMouseListener(this));
		
		this.add(new TableSplitPanel(this.auctionTable, this.itemTable, 250, true));

		// Adding Fields
		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
		fieldPanel.setBorder(BorderFactory.createTitledBorder("Information"));

		JPanel subpanel1 = new JPanel(new FlowLayout());
		subpanel1.add(new JLabel("Name          "));
		subpanel1.add(this.name);
		fieldPanel.add(subpanel1);

		JPanel subpanel2 = new JPanel(new FlowLayout());
		subpanel2.add(new JLabel("Description"));
		subpanel2.add(this.description);
		fieldPanel.add(subpanel2);

		JPanel subpanel3 = new JPanel(new FlowLayout());
		subpanel3.add(new JLabel("Target price"));
		subpanel3.add(this.targetPrice);
		fieldPanel.add(subpanel3);

		JPanel subpanel4 = new JPanel(new FlowLayout());
		subpanel4.add(new JLabel("Entry Price  "));
		subpanel4.add(this.entryPrice);
		fieldPanel.add(subpanel4);
		
		JPanel subpanel12 = new JPanel(new FlowLayout());
		subpanel12.add(new JLabel("Duration  "));
		subpanel12.add(this.duration);
		fieldPanel.add(subpanel12);

		JPanel subpanel7 = new JPanel(new FlowLayout());
		act.add(active);
		act.add(finished);
		subpanel7.add(active);
		subpanel7.add(finished);
		fieldPanel.add(subpanel7);

		JPanel buttons = new JPanel(new FlowLayout());
		JButton it = new JButton("Add Item");
		it.addActionListener(new AddItemAuctionControler(this));
		buttons.add(it);
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ClearManagerAuction(this));
		buttons.add(clear);

		JButton update = new JButton("Update");
		update.addActionListener(new ChangeAuctionControler(this));
		buttons.add(update);

		JButton newc = new JButton("New");
		newc.addActionListener(new NewAuctionControler(this));
		buttons.add(newc);

		
		JPanel panelr = new JPanel();
		panelr.setLayout(new BoxLayout(panelr,BoxLayout.Y_AXIS));
		panelr.add(fieldPanel);
		panelr.add(buttons);
		
		JSplitPane rightPanel= new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		rightPanel.setDividerLocation(350);
		rightPanel.setEnabled(true);
		rightPanel.add(panelr);
		
		JPanel panelA = new JPanel();
		panelA.setLayout(new BorderLayout());
		panelA.add(lotTable, BorderLayout.CENTER);
		panelA.add(lotTable.getTableHeader(), BorderLayout.NORTH);
		this.add(panelA);
		
		
		rightPanel.add(panelA);
		
		this.add(rightPanel);
		
		refreshPanel();
		updateAuction();
		updateItem();
		updateLot();

	}

	/**
	 * Refresh the fields
	 */
	public void refreshPanel() {
		if (this.auction == null) {
			clear();

		} else {
			name.setText(auction.getName());
			description.setText(auction.getDescription());
			targetPrice.setText(auction.getTargetPrice() + "");
			entryPrice.setText(auction.getEntryPrice() + "");
			active.setSelected(auction.isActive());
			finished.setSelected(!auction.isActive());
			duration.setText(auction.getDuration()+"");

		}
		

		
	}

	/**
	 * clear the fields
	 */
	public void clear() {
		this.auction = null;
		name.setText("");
		description.setText("");
		targetPrice.setText("");
		entryPrice.setText("");
		active.setSelected(false);
		finished.setSelected(false);
		this.lot = new Lot();
	}

	/**
	 * update the auction's table
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
	 * update the item's table
	 */
	public void updateItem() {

		DefaultTableModel dtm = new DefaultTableModel();

		dtm.setColumnIdentifiers(ITEM_HEADER);

		for (Item item : Shop.getShop().getStock()) {

			if (this.lot != null) {
				if (lot.getItems().contains(item))
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
	 * update the lot table
	 */
	public void updateLot() {
		DefaultTableModel dtm = new DefaultTableModel();

		dtm.setColumnIdentifiers(ITEM_HEADER);
		
		if (this.lot == null) {
			lotTable.setModel(dtm);
			return;
		}

		for (Item item : lot.getItems()) {

			Vector<Object> data = new Vector<Object>();
			data.add(item.getDescription());
			data.add(item.getManufactureYear());
			data.add(item.getTargetPrice());

			dtm.addRow(data);
		}
		lotTable.setModel(dtm);
	}

	/**
	 * @return the auction
	 */
	public Auction getAuction() {
		return auction;
	}

	/**
	 * @param auction the auction to set
	 */
	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	/**
	 * @return the lot
	 */
	public Lot getLot() {
		return lot;
	}

	/**
	 * @param lot the lot to set
	 */
	public void setLot(Lot lot) {
		this.lot = lot;
	}

	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * @return the auctionTable
	 */
	public JTable getAuctionTable() {
		return auctionTable;
	}

	/**
	 * @return the itemTable
	 */
	public JTable getItemTable() {
		return itemTable;
	}

	/**
	 * @return the lotTable
	 */
	public JTable getLotTable() {
		return lotTable;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name.getText();
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description.getText();
	}

	/**
	 * @return the targetPrice
	 */
	public Integer getTargetPrice() {
		return Integer.valueOf(targetPrice.getText());
	}

	/**
	 * @return the entryPrice
	 */
	public Integer getEntryPrice() {
		return Integer.valueOf(entryPrice.getText());
	}

	/**
	 * @return the duration
	 */
	public Integer getDuration(){
		return Integer.valueOf(duration.getText());
	}
	/**
	 * @return the active
	 */
	public boolean getActive() {
		return active.isSelected();
	}



}