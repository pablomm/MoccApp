package pads.antiquary.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;

/**
 * Class with a standard struct with two tables in a splitpanel
 * and a field to relalize querys
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class TableSplitPanel extends JSplitPane {

	/**
	 * Generated serial version UID
	 */
	private static final long serialVersionUID = -613586473075189845L;

	/**
	 * Creates the panel
	 * @param table1 first table
	 * @param table2 second table
	 * @param divider position
	 * @param enabled enable move
	 */
	public TableSplitPanel(JTable table1, JTable table2, int divider,boolean enabled){
		
				super(JSplitPane.VERTICAL_SPLIT);
				this.setDividerLocation(divider);
				this.setEnabled(enabled);
				
				//Adding first Table
				JPanel panelA = new JPanel();
				panelA.setLayout(new BorderLayout());
				panelA.add(table1, BorderLayout.CENTER);
				panelA.add(table1.getTableHeader(), BorderLayout.NORTH);
				this.add(panelA);
				
				//Adding Second table
				JPanel panelB = new JPanel();
				panelB.setLayout(new BorderLayout());
				panelB.add(table2, BorderLayout.CENTER);
				panelB.add(table2.getTableHeader(), BorderLayout.NORTH);
				this.add(panelB);
		
	}
	
	
	
}
