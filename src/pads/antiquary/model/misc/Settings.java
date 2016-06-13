package pads.antiquary.model.misc;

/**
 * Class with the setting information
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 * 
 */
public final class Settings {

	/* Delivery Cost settings */
	/** Min spent to get the discount */
	private static int MIN_SPEND = 200;
	/** Extra cost per Kg */
	private static Double COST_PER_KG = 0.4;
	/** MAX cost */
	private static Integer MAX_COST = 50;
	/** Base cost */
	private static Integer MIN_COST = 20;
	/** Min height in cm */
	private static Integer MIN_HEIGHT = 200;
	/** Min weight in kgs */
	private static Integer MIN_WEIGHT = 15;

	/* Contract Settings */
	/** Duration of the contract in days */
	private static Integer CONTRACT_DURATION = 365;
	/** Standard free entries */
	private static Integer STANDARD_FREE_ENTRYS = 5;

	/* Security settings */
	/** code to hash the manager */
	public static final String MANAGER_CODE = "MoccapManager";
	/** code to hash the employee */
	public static final String EMPLOYEE_CODE = "MoccapEmployee";

	/* Notification Settings */

	/** Subject of bid messages */
	private static String BID_SUBJECT = "[MoccApp]New Bid in auction ";
	/** Body of bid messages */
	private static String BID_BODY = "There is a new bid in the auction.";

	/* Folder settings */
	/** folder with the files */
	private static String DEFAULT_FOLDER = "files/";
	/** items path */
	private static String DEFAULT_ITEM_FILE = DEFAULT_FOLDER + "items.csv";
	/** user path */
	private static String DEFAULT_USER_FILE = DEFAULT_FOLDER + "users.csv";
	/** auctions path */
	private static String DEFAULT_AUCTION_FILE = DEFAULT_FOLDER + "auctions.csv";
	/** customer path */
	private static String DEFAULT_CUSTOMER_FILE = DEFAULT_FOLDER + "customers.csv";
	/** sales path */
	private static String DEFAULT_SALE_FILE = DEFAULT_FOLDER + "sales.csv";
	/** file path */
	private static final String DEFAULT_SETTINGS_FILE = DEFAULT_FOLDER + "settings.log";

	/**
	 * @return the mIN_SPEND
	 */
	public static int getMIN_SPEND() {
		return MIN_SPEND;
	}

	/**
	 * @param mIN_SPEND
	 *            the mIN_SPEND to set
	 */
	public static void setMIN_SPEND(int mIN_SPEND) {
		MIN_SPEND = mIN_SPEND;
	}

	/**
	 * @return the cOST_PER_KG
	 */
	public static Double getCOST_PER_KG() {
		return COST_PER_KG;
	}

	/**
	 * @param cOST_PER_KG
	 *            the cOST_PER_KG to set
	 */
	public static void setCOST_PER_KG(Double cOST_PER_KG) {
		COST_PER_KG = cOST_PER_KG;
	}

	/**
	 * @return the mAX_COST
	 */
	public static Integer getMAX_COST() {
		return MAX_COST;
	}

	/**
	 * @param mAX_COST
	 *            the mAX_COST to set
	 */
	public static void setMAX_COST(Integer mAX_COST) {
		MAX_COST = mAX_COST;
	}

	/**
	 * @return the mIN_COST
	 */
	public static Integer getMIN_COST() {
		return MIN_COST;
	}

	/**
	 * @param mIN_COST
	 *            the mIN_COST to set
	 */
	public static void setMIN_COST(Integer mIN_COST) {
		MIN_COST = mIN_COST;
	}

	/**
	 * @return the mIN_HEIGHT
	 */
	public static Integer getMIN_HEIGHT() {
		return MIN_HEIGHT;
	}

	/**
	 * @param mIN_HEIGHT
	 *            the mIN_HEIGHT to set
	 */
	public static void setMIN_HEIGHT(Integer mIN_HEIGHT) {
		MIN_HEIGHT = mIN_HEIGHT;
	}

	/**
	 * @return the mIN_WEIGHT
	 */
	public static Integer getMIN_WEIGHT() {
		return MIN_WEIGHT;
	}

	/**
	 * @param mIN_WEIGHT
	 *            the mIN_WEIGHT to set
	 */
	public static void setMIN_WEIGHT(Integer mIN_WEIGHT) {
		MIN_WEIGHT = mIN_WEIGHT;
	}

	/**
	 * @return the cONTRACT_DURATION
	 */
	public static Integer getCONTRACT_DURATION() {
		return CONTRACT_DURATION;
	}

	/**
	 * @param cONTRACT_DURATION
	 *            the cONTRACT_DURATION to set
	 */
	public static void setCONTRACT_DURATION(Integer cONTRACT_DURATION) {
		CONTRACT_DURATION = cONTRACT_DURATION;
	}

	/**
	 * @return the sTANDARD_FREE_ENTRYS
	 */
	public static Integer getSTANDARD_FREE_ENTRYS() {
		return STANDARD_FREE_ENTRYS;
	}

	/**
	 * @param sTANDARD_FREE_ENTRYS
	 *            the sTANDARD_FREE_ENTRYS to set
	 */
	public static void setSTANDARD_FREE_ENTRYS(Integer sTANDARD_FREE_ENTRYS) {
		STANDARD_FREE_ENTRYS = sTANDARD_FREE_ENTRYS;
	}

	/**
	 * @return the bID_SUBJECT
	 */
	public static String getBID_SUBJECT() {
		return BID_SUBJECT;
	}

	/**
	 * @param bID_SUBJECT
	 *            the bID_SUBJECT to set
	 */
	public static void setBID_SUBJECT(String bID_SUBJECT) {
		BID_SUBJECT = bID_SUBJECT;
	}

	/**
	 * @return the bID_BODY
	 */
	public static String getBID_BODY() {
		return BID_BODY;
	}

	/**
	 * @param bID_BODY
	 *            the bID_BODY to set
	 */
	public static void setBID_BODY(String bID_BODY) {
		BID_BODY = bID_BODY;
	}

	/**
	 * @return the dEFAULT_FOLDER
	 */
	public static String getDEFAULT_FOLDER() {
		return DEFAULT_FOLDER;
	}

	/**
	 * @param dEFAULT_FOLDER
	 *            the dEFAULT_FOLDER to set
	 */
	public static void setDEFAULT_FOLDER(String dEFAULT_FOLDER) {
		DEFAULT_FOLDER = dEFAULT_FOLDER;
	}

	/**
	 * @return the dEFAULT_ITEM_FILE
	 */
	public static String getDEFAULT_ITEM_FILE() {
		return DEFAULT_ITEM_FILE;
	}

	/**
	 * @param dEFAULT_ITEM_FILE
	 *            the dEFAULT_ITEM_FILE to set
	 */
	public static void setDEFAULT_ITEM_FILE(String dEFAULT_ITEM_FILE) {
		DEFAULT_ITEM_FILE = dEFAULT_ITEM_FILE;
	}

	/**
	 * @return the dEFAULT_USER_FILE
	 */
	public static String getDEFAULT_USER_FILE() {
		return DEFAULT_USER_FILE;
	}

	/**
	 * @param dEFAULT_USER_FILE
	 *            the dEFAULT_USER_FILE to set
	 */
	public static void setDEFAULT_USER_FILE(String dEFAULT_USER_FILE) {
		DEFAULT_USER_FILE = dEFAULT_USER_FILE;
	}

	/**
	 * @return the dEFAULT_AUCTION_FILE
	 */
	public static String getDEFAULT_AUCTION_FILE() {
		return DEFAULT_AUCTION_FILE;
	}

	/**
	 * @param dEFAULT_AUCTION_FILE
	 *            the dEFAULT_AUCTION_FILE to set
	 */
	public static void setDEFAULT_AUCTION_FILE(String dEFAULT_AUCTION_FILE) {
		DEFAULT_AUCTION_FILE = dEFAULT_AUCTION_FILE;
	}

	/**
	 * @return the dEFAULT_CUSTOMER_FILE
	 */
	public static String getDEFAULT_CUSTOMER_FILE() {
		return DEFAULT_CUSTOMER_FILE;
	}

	/**
	 * @param dEFAULT_CUSTOMER_FILE
	 *            the dEFAULT_CUSTOMER_FILE to set
	 */
	public static void setDEFAULT_CUSTOMER_FILE(String dEFAULT_CUSTOMER_FILE) {
		DEFAULT_CUSTOMER_FILE = dEFAULT_CUSTOMER_FILE;
	}

	/**
	 * @return the dEFAULT_SALE_FILE
	 */
	public static String getDEFAULT_SALE_FILE() {
		return DEFAULT_SALE_FILE;
	}

	/**
	 * @param dEFAULT_SALE_FILE
	 *            the dEFAULT_SALE_FILE to set
	 */
	public static void setDEFAULT_SALE_FILE(String dEFAULT_SALE_FILE) {
		DEFAULT_SALE_FILE = dEFAULT_SALE_FILE;
	}

	/**
	 * @return the managerCode
	 */
	public static String getManagerCode() {
		return MANAGER_CODE;
	}

	/**
	 * @return the employeeCode
	 */
	public static String getEmployeeCode() {
		return EMPLOYEE_CODE;
	}

	/**
	 * @return the defaultSettingsFile
	 */
	public static String getDefaultSettingsFile() {
		return DEFAULT_SETTINGS_FILE;
	}

	private Settings() {
	}

	/**
	 * Allow writes all the settings in a file
	 * 
	 * @return String to print
	 */
	public static String toPrint() {
		return MIN_SPEND + "\n" + COST_PER_KG + "\n" + MAX_COST + "\n" + MIN_COST + "\n" + MIN_HEIGHT + "\n"
				+ MIN_WEIGHT + "\n" + CONTRACT_DURATION + "\n" + STANDARD_FREE_ENTRYS + "\n" + BID_SUBJECT + "\n"
				+ BID_BODY + "\n" + DEFAULT_FOLDER + "\n" + DEFAULT_ITEM_FILE + "\n" + DEFAULT_USER_FILE + "\n"
				+ DEFAULT_AUCTION_FILE + "\n" + DEFAULT_CUSTOMER_FILE + "\n" + DEFAULT_SALE_FILE + "\n"
				+ DEFAULT_SETTINGS_FILE;
	}

}
