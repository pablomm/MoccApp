package pads.antiquary.view;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 * Custom JTable that has disabled the edition
 * and has single selection
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 *
 */
public class CustomTable extends JTable {
	
	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = 4121185250502047193L;
	
	/**
	 * New custom table
	 */
	public CustomTable(){
		super();
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	@Override
    public boolean isCellEditable(int i, int i1) {
        return false;
    }
	
	

}
