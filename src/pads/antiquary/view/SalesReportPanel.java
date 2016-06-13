package pads.antiquary.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import pads.antiquary.controller.ReportSaleController;

/**
 * Class with the panel to create and modificate the auctions
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class SalesReportPanel extends JPanel {

	/**
	 * serial version
	 */
	private static final long serialVersionUID = -1399430307206040011L;

	/**
	 * Months options
	 */
	private static final Integer[] monthsnum = {1,2,3,4,5,6,7,8,9};
	
	/**
	 * Search types
	 */
	private static final String[] searchtype = {"Auction","Sale"};
	
	/**
	 * Table with results
	 */
	private JTable table = new CustomTable();
	
	/**
	 * Month combobox
	 */
	private JComboBox<Integer> months = new JComboBox<Integer>(monthsnum);
	
	/**
	 * Types combobox
	 */
	private JComboBox<String> type = new JComboBox<String>(searchtype);
	
	/**
	 * Creates the panel
	 */
	public SalesReportPanel(){
		this.setLayout(new BorderLayout());
		
		JPanel upper = new JPanel(new FlowLayout());
		type.setLightWeightPopupEnabled(false);
		months.setLightWeightPopupEnabled(false);
		upper.add(new JLabel("Search"));
		upper.add(type);
		upper.add(new JLabel("Months"));
		upper.add(months);
		
		JButton search = new JButton("Search");
		ActionListener controller = new ReportSaleController(this);
		search.addActionListener(controller);
		upper.add(search);
		
		this.add(upper,BorderLayout.NORTH);
		this.add(new JScrollPane(table), BorderLayout.CENTER);
		controller.actionPerformed(null);
		
	}

	/**
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * @return the months
	 */
	public Integer getMonths() {
		return (Integer)months.getSelectedItem();
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type.getSelectedIndex();
	}
	
	
}
