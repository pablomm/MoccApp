package pads.antiquary.model.people;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import pads.antiquary.model.misc.Password;
import pads.antiquary.model.misc.Settings;

/**
 * @author Pablo Marcos Manchon
 * @author Diana Rojas
 */
public class Employee extends User {
	/**
	 * Constructor of the class
	 * 
	 * @param userName
	 * @param name
	 * @param surname
	 * @param birthDay
	 * @param email
	 * @param phone
	 * @param gender
	 * @param adress
	 * @param password
	 */
	public Employee(String userName, String name, String surname, LocalDate birthDay, String email, int phone,
			Gender gender, String adress, String password) {
		super(userName, name, surname, birthDay, email, phone, gender, adress, password);
	}

	/**
	 * Alternative constructor
	 * 
	 * @param userName
	 * @param name
	 * @param surname
	 * @param birthDay
	 * @param email
	 * @param phone
	 * @param gender
	 * @param adress
	 * @param password
	 * @param hash
	 *            true to hash the password
	 * @throws Exception
	 */
	public Employee(String userName, String name, String surname, LocalDate birthDay, String email, int phone,
			Gender gender, String adress, String password, boolean hash) throws Exception {
		super(userName, name, surname, birthDay, email, phone, gender, adress, password, hash);
	}

	/**
	 * Parses the employee from a array string
	 * 
	 * @param userSplit
	 */
	public Employee(String[] userSplit) {
		this(userSplit[7], userSplit[0], userSplit[1],
				LocalDate.parse(userSplit[2], DateTimeFormatter.ofPattern("d/L/yyyy")), userSplit[3],
				Integer.parseInt(userSplit[4]), Gender.valueOf(userSplit[5]), userSplit[6], userSplit[8]);
	}

	@Override
	public String toString() {
		try {
			return super.toString() + ";" + Password.getSaltedHash(Settings.EMPLOYEE_CODE + this.getUserName());
		} catch (Exception e) {
			return "Error validating User: " + this.getUserName();
		}
	}

	/**
	 * Prints the employee without employee hash code
	 */
	public String toPrint() {
		return super.toString();
	}

	@Override
	public boolean isManager() {
		return false;
	}
}
