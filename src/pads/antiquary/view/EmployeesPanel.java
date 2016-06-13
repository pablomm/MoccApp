package pads.antiquary.view;

import javax.swing.JTabbedPane;
/**
 * Class with the Employee Panel
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 *
 */
public class EmployeesPanel extends JTabbedPane {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -733238821669760609L;
	/**
	 * creates the panel
	 */
	public EmployeesPanel(){
		ModEmployeePanel modPanel = new ModEmployeePanel();
		this.addTab("Modify", null, modPanel, "Modify a new employee");
		this.addTab("New", null, new NewEmployeePanel(modPanel), "Create a new employee");

		
	}

}
