package pads.antiquary.view;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import pads.antiquary.controller.SettingsController;
import pads.antiquary.model.misc.Settings;

/**
 * Class with the settings options
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class SettingsPanel extends JTabbedPane {

	/**
	 * Serial Version UId
	 */
	private static final long serialVersionUID = -7349244423083924939L;
	
	
	private JTextField minSpend = new JTextField(10);
	/** Extra cost per Kg */
	private JTextField costPerKg = new JTextField(10);
	/** MAX cost */
	private JTextField maxCost = new JTextField(10);
	/** Base cost */
	private JTextField minCost = new JTextField(10);
	/** Min height in cm */
	private JTextField minHeight = new JTextField(10);
	/** Min weight in kgs */
	private JTextField minWeight = new JTextField(10);
	/** Duration of the contract in days */
	private JTextField contractDuration = new JTextField(10);
	/** Standard free entries */
	private JTextField standardFreeEntrys = new JTextField(10);
	/** Subject of bid messages */
	private JTextField bidSubject = new JTextField(20);
	/** Body of bid messages */
	private JTextField bidBody = new JTextField(20);
	/** folder with the files */
	private  JTextField defaultFolder = new JTextField(20);
	/** items path */
	private JTextField defaultItemFile = new JTextField(20);
	/** user path */
	private JTextField defaultUserFile = new JTextField(20);
	/** auctions path */
	private JTextField defaultAuctionFile = new JTextField(20);
	/** customer path */
	private JTextField defaultCustomerFile = new JTextField(20);
	/** sales path */
	private JTextField defaultSaleFile = new JTextField(20);

	/**
	 * creates the panel
	 */
	public SettingsPanel(){
		
		super(JTabbedPane.LEFT);
		ActionListener controler = new SettingsController(this);
		
		
		JPanel sales = new JPanel();
		sales.setLayout(new BoxLayout(sales, BoxLayout.Y_AXIS));
		sales.setBorder(BorderFactory.createTitledBorder("Sales parameters"));
		
		JPanel delivery = new JPanel();
		delivery.setLayout(new BoxLayout(delivery, BoxLayout.Y_AXIS));
		delivery.setBorder(BorderFactory.createTitledBorder("Delivery Cost"));
		
		JPanel subpanel1 = new JPanel(new FlowLayout());
		subpanel1.add(new JLabel("Minimun Spend"));
		subpanel1.add(this.minSpend);
		delivery.add(subpanel1);
		
		JPanel subpanel2 = new JPanel(new FlowLayout());
		subpanel2.add(new JLabel("Cost per KG"));
		subpanel2.add(this.costPerKg);
		delivery.add(subpanel2);
		
		JPanel subpanel3 = new JPanel(new FlowLayout());
		subpanel3.add(new JLabel("Max Cost"));
		subpanel3.add(this.maxCost);
		delivery.add(subpanel3);
		
		JPanel subpanel4 = new JPanel(new FlowLayout());
		subpanel4.add(new JLabel("Min Cost"));
		subpanel4.add(this.minCost);
		delivery.add(subpanel4);
		
		JPanel subpanel5 = new JPanel(new FlowLayout());
		subpanel5.add(new JLabel("Min Height"));
		subpanel5.add(this.minHeight);
		delivery.add(subpanel5);
		
		JPanel subpanel6 = new JPanel(new FlowLayout());
		subpanel6.add(new JLabel("Min Weight"));
		subpanel6.add(this.minWeight);
		delivery.add(subpanel6);
		
		sales.add(delivery);
		

		
		JPanel contract = new JPanel();
		contract.setLayout(new BoxLayout(contract, BoxLayout.Y_AXIS));
		contract.setBorder(BorderFactory.createTitledBorder("Contracts"));
		
		JPanel subpanel7 = new JPanel(new FlowLayout());
		subpanel7.add(new JLabel("Contract Duration"));
		subpanel7.add(this.contractDuration);
		contract.add(subpanel7);
		
		JPanel subpanel8 = new JPanel(new FlowLayout());
		subpanel8.add(new JLabel("Standart free entries"));
		subpanel8.add(this.standardFreeEntrys);
		contract.add(subpanel8);
		
		sales.add(contract);
		
		JPanel buttons = new JPanel(new FlowLayout());
		JButton save = new JButton("Save changes");
		save.addActionListener(controler);
		buttons.add(save);
		sales.add(buttons);
		
		this.addTab("Sales parameters", sales);
		
		JPanel other = new JPanel();
		other.setLayout(new BoxLayout(other, BoxLayout.Y_AXIS));
		other.setBorder(BorderFactory.createTitledBorder("Other settings"));
		
		JPanel files = new JPanel();
		files.setLayout(new BoxLayout(files, BoxLayout.Y_AXIS));
		files.setBorder(BorderFactory.createTitledBorder("Files"));
		
		JPanel subpanel11 = new JPanel(new FlowLayout());
		subpanel11.add(new JLabel("Default Folder"));
		subpanel11.add(this.defaultFolder);
		files.add(subpanel11);
		
		JPanel subpanel9 = new JPanel(new FlowLayout());
		subpanel9.add(new JLabel("Auction File"));
		subpanel9.add(this.defaultAuctionFile);
		files.add(subpanel9);
		
		JPanel subpanel10 = new JPanel(new FlowLayout());
		subpanel10.add(new JLabel("Customer File"));
		subpanel10.add(this.defaultCustomerFile);
		files.add(subpanel10);
		
		JPanel subpanel12 = new JPanel(new FlowLayout());
		subpanel12.add(new JLabel("Item File"));
		subpanel12.add(this.defaultItemFile);
		files.add(subpanel12);
		
		JPanel subpanel13 = new JPanel(new FlowLayout());
		subpanel13.add(new JLabel("Sale File"));
		subpanel13.add(this.defaultSaleFile);
		files.add(subpanel13);
		
		JPanel subpanel14 = new JPanel(new FlowLayout());
		subpanel14.add(new JLabel("user File"));
		subpanel14.add(this.defaultUserFile);
		files.add(subpanel14);
		
		other.add(files);
		
		JPanel notification = new JPanel();
		notification.setLayout(new BoxLayout(notification, BoxLayout.Y_AXIS));
		notification.setBorder(BorderFactory.createTitledBorder("Files"));
		
		JPanel subpanel15 = new JPanel(new FlowLayout());
		subpanel15.add(new JLabel("Message Subject"));
		subpanel15.add(this.bidSubject);
		notification.add(subpanel15);
		
		JPanel subpanel16 = new JPanel(new FlowLayout());
		subpanel16.add(new JLabel("Message Body"));
		subpanel16.add(this.bidBody);
		notification.add(subpanel16);
		other.add(notification);
		
		JPanel buttons2 = new JPanel(new FlowLayout());
		JButton save2 = new JButton("Save changes");
		buttons2.add(save2);
		save2.addActionListener(controler);
		other.add(buttons2);
		
		this.addTab("Other settings",other);
		updateFields();
	}
	
	/**
	 * update the fields
	 */
	public void updateFields(){
		
		minSpend.setText(Settings.getMIN_SPEND()+"");
		costPerKg.setText(Settings.getCOST_PER_KG()+"");
		maxCost.setText(Settings.getMAX_COST()+"");
		minCost.setText(Settings.getMIN_COST()+"");
		minHeight.setText(Settings.getMIN_HEIGHT()+"");
		minWeight.setText(Settings.getMIN_WEIGHT()+"");
		contractDuration.setText(Settings.getCONTRACT_DURATION()+"");
		standardFreeEntrys.setText(Settings.getSTANDARD_FREE_ENTRYS()+"");
		bidSubject.setText(Settings.getBID_SUBJECT());
		bidBody.setText(Settings.getBID_BODY()+"");
		defaultFolder.setText(Settings.getDEFAULT_FOLDER());
		defaultItemFile.setText(Settings.getDEFAULT_ITEM_FILE());
		defaultUserFile.setText(Settings.getDEFAULT_USER_FILE());
		defaultAuctionFile.setText(Settings.getDEFAULT_AUCTION_FILE());
		defaultCustomerFile.setText(Settings.getDEFAULT_CUSTOMER_FILE());
		defaultSaleFile.setText(Settings.getDEFAULT_SALE_FILE());
		
	}

	/**
	 * @return the minSpend
	 */
	public Integer getMinSpend() {
		return Integer.parseInt(minSpend.getText());
	}

	/**
	 * @return the costPerKg
	 */
	public Double getCostPerKg() {
		return Double.valueOf(costPerKg.getText());
	}

	/**
	 * @return the maxCost
	 */
	public Integer getMaxCost() {
		return Integer.parseInt(maxCost.getText());
	}

	/**
	 * @return the minCost
	 */
	public Integer getMinCost() {
		return Integer.parseInt(minCost.getText());
	}

	/**
	 * @return the minHeight
	 */
	public Integer getMinHeight() {
		return Integer.parseInt(minHeight.getText());
	}

	/**
	 * @return the minWeight
	 */
	public Integer getMinWeight() {
		return Integer.parseInt(minWeight.getText());
	}

	/**
	 * @return the contractDuration
	 */
	public Integer getContractDuration() {
		return Integer.parseInt(contractDuration.getText());
	}

	/**
	 * @return the standardFreeEntrys
	 */
	public Integer getStandardFreeEntrys() {
		return Integer.parseInt(standardFreeEntrys.getText());
	}

	/**
	 * @return the bidSubject
	 */
	public String getBidSubject() {
		return bidSubject.getText();
	}

	/**
	 * @return the bidBody
	 */
	public String getBidBody() {
		return bidBody.getText();
	}

	/**
	 * @return the defaultFolder
	 */
	public String getDefaultFolder() {
		return defaultFolder.getText();
	}

	/**
	 * @return the defaultItemFile
	 */
	public String getDefaultItemFile() {
		return defaultItemFile.getText();
	}

	/**
	 * @return the defaultUserFile
	 */
	public String getDefaultUserFile() {
		return defaultUserFile.getText();
	}

	/**
	 * @return the defaultAuctionFile
	 */
	public String getDefaultAuctionFile() {
		return defaultAuctionFile.getText();
	}

	/**
	 * @return the defaultCustomerFile
	 */
	public String getDefaultCustomerFile() {
		return defaultCustomerFile.getText();
	}

	/**
	 * @return the defaultSaleFile
	 */
	public String getDefaultSaleFile() {
		return defaultSaleFile.getText();
	}
}
