package pads.antiquary.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import pads.antiquary.model.exceptions.NotFound;
import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.item.Item;
import pads.antiquary.model.people.Customer;
import pads.antiquary.model.sales.Auction;
import pads.antiquary.view.SearchPanel;

/**
 * Controller of the panel
 * controller of the queries
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class QueryController implements ActionListener {

	/**
	 * Panel of the controller
	 */
	private SearchPanel panel;
	
	/**
	 * table associated
	 */
	private JTable table;

	/**
	 * header of the table
	 */
	private final String itemHeader[] = new String[] { "Description", "Date", "Price", "Sold" };

	/**
	 * header of the table
	 */
	private final String auctionHeader[] = new String[] { "Name", "Description", "Target Price", "Entry Price",
			"Active" };

	/**
	 * header of the table
	 */
	private final String customerHeader[] = new String[] { "ID", "Name", "Surname", "Email", "Phone" };

	public QueryController(SearchPanel searchPanel) {
		this.panel = searchPanel;
		this.table = searchPanel.getTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DefaultTableModel dtm = new DefaultTableModel();
		try {
			switch ((String) panel.getType().getSelectedItem()) {

			case "Item":
				ItemQuery(dtm);

				break;

			case "Item by Date":
				ItemDateQuery(dtm);
				break;

			case "Auctions":
				auctionQuery(dtm);

				break;

			default:
				customerQuery(dtm);
			}
			table.setModel(dtm);
		} catch (NotFound e1) {
			JOptionPane.showMessageDialog(panel, e1.toString());
		}

	}

	public void ItemQuery(DefaultTableModel dtm) throws NotFound {
		dtm.setColumnIdentifiers(itemHeader);
		for (Item item : Shop.getShop().searchItemByStr(panel.getQuery())) {
			Vector<Object> data = new Vector<Object>();
			data.add(item.getDescription());
			data.add(item.getManufactureYear());
			data.add(item.getTargetPrice().toString());
			data.add(item.isSold() ? "available" : "sold");
			dtm.addRow(data);
		}
	}

	public void ItemDateQuery(DefaultTableModel dtm) throws NotFound {
		dtm.setColumnIdentifiers(itemHeader);
		for (Item item : Shop.getShop().searchItemByDate(panel.getQuery())) {
			Vector<Object> data = new Vector<Object>();
			data.add(item.getDescription());
			data.add(item.getManufactureYear());
			data.add(item.getTargetPrice().toString());
			data.add(item.isSold() ? "available" : "sold");
			dtm.addRow(data);
		}
	}

	public void auctionQuery(DefaultTableModel dtm) throws NotFound {
		dtm.setColumnIdentifiers(auctionHeader);
		for (Auction auction : Shop.getShop().searchAuction(panel.getQuery())) {
			Vector<Object> data = new Vector<Object>();
			data.add(auction.getName());
			data.add(auction.getDescription());
			data.add(auction.getTargetPrice());
			data.add(auction.getEntryPrice());
			data.add(auction.isActive() ? "Active" : "Finished");
			dtm.addRow(data);
		}
	}

	public void customerQuery(DefaultTableModel dtm) throws NotFound {
		dtm.setColumnIdentifiers(customerHeader);
		for (Customer c : Shop.getShop().searchCustomer(panel.getQuery())) {
			Vector<Object> data = new Vector<Object>();
			data.add(c.getCustomerID());
			data.add(c.getName());
			data.add(c.getSurname());
			data.add(c.getEmail());
			data.add(c.getPhone());
			dtm.addRow(data);
		}
	}
}
