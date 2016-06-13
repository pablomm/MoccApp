package pads.antiquary.model.people;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import pads.antiquary.model.misc.Password;
import pads.antiquary.model.misc.Settings;

/**
 * Class to the manager users
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class Manager extends User {

	/**
	 * Constructor of the class Manager
	 * 
	 * @param userName
	 *            user name of the manager
	 * @param name
	 *            name of the manager
	 * @param surname
	 *            surname of the manager
	 * @param birthDay
	 *            birthday of the manager
	 * @param email
	 *            email of the manager
	 * @param phone
	 *            phone of the manager
	 * @param gender
	 *            of the manager
	 * @param adress
	 *            of the manager
	 * @param password
	 *            of the manager
	 */
	public Manager(String userName, String name, String surname, LocalDate birthDay, String email, int phone,
			Gender gender, String adress, String password) {
		super(userName, name, surname, birthDay, email, phone, gender, adress, password);
	}

	/**
	 * Alternative constructor, that alows to hash the password directly
	 * 
	 * @param userName
	 *            of the manager
	 * @param name
	 *            of the manager
	 * @param surname
	 *            of the manager
	 * @param birthDay
	 *            of the manager
	 * @param email
	 *            of the manager
	 * @param phone
	 *            of the manager
	 * @param gender
	 *            of the manager
	 * @param adress
	 *            of the manager
	 * @param password
	 *            of the manager
	 * @param hash
	 *            true to hash the passoword
	 * @throws Exception
	 */
	public Manager(String userName, String name, String surname, LocalDate birthDay, String email, int phone,
			Gender gender, String adress, String password, boolean hash) throws Exception {
		super(userName, name, surname, birthDay, email, phone, gender, adress, password, hash);
	}

	/**
	 * Constructor to parse the Manager
	 * 
	 * @param userSplit
	 *            Split with the manager atributes
	 */
	public Manager(String[] userSplit) {
		this(userSplit[7], userSplit[0], userSplit[1],
				LocalDate.parse(userSplit[2], DateTimeFormatter.ofPattern("d/L/yyyy")), userSplit[3],
				Integer.parseInt(userSplit[4]), Gender.valueOf(userSplit[5]), userSplit[6], userSplit[8]);
	}

	@Override
	public String toString() {
		try {
			return super.toString() + ";" + Password.getSaltedHash(Settings.MANAGER_CODE + this.getUserName());
		} catch (Exception e) {
			return "Error generating user: " + this.getUserName();
		}
	}

	public String toPrint() {
		return super.toString();
	}

	@Override
	public boolean isManager() {
		return true;
	}

}
