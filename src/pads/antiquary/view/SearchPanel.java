package pads.antiquary.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import pads.antiquary.controller.QueryController;

/**
 * Class with the search panels
 * It contains a table with the results
 * and a field to relalize querys
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class SearchPanel extends JPanel {

	/**
	 * Generated serial Version UID
	 */
	private static final long serialVersionUID = -5366197433959530864L;

	/**
	 * Types of querys
	 */
	private static final String TYPES[] = { "Item", "Item by Date", "Auctions", "Customers" };

	/**
	 * Field for the query
	 */
	private JTextField queryText = new JTextField(30);

	/**
	 * Combo box with the options
	 */
	private JComboBox<String> type = new JComboBox<String>(TYPES);

	/**
	 * Table to show the results, overrided to disable edition
	 */
	private JTable table = new CustomTable();

	/**
	 * Controler of the tables
	 */
	private ActionListener controller = new QueryController(this);
	
	/**
	 * Controller of the table show
	 */
	//private ActionListener tableController = new TableController(table);

	public SearchPanel() {

		this.setLayout(new BorderLayout());

		// Panel with the Query options
		JPanel query = new JPanel();
		query.setLayout(new FlowLayout());

		// Adding text field
		queryText.addActionListener(controller);
		query.add(queryText);

		// Adding Combo box
		type.setLightWeightPopupEnabled(false);
		query.add(type);

		// Adding search button
		JButton search = new JButton("Search");
		search.setSize(10, 30);
		search.addActionListener(controller);
		query.add(search);

		this.add(query, BorderLayout.NORTH);

		// Adding panel with the results
		JScrollPane results = new JScrollPane(table);
		results.setBorder(new EmptyBorder(15, 15, 10, 15));
		this.add(results, BorderLayout.CENTER);

		// Updating table
		controller.actionPerformed(null);
		
	}

	/**
	 * @return the text of the search
	 */
	public String getQuery() {
		return queryText.getText();
	}

	/**
	 * @return the table with the results
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * @return the combo box to select the search
	 */
	public JComboBox<String> getType() {
		return this.type;
	}

}
