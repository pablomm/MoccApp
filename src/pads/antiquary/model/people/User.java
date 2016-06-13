package pads.antiquary.model.people;

import java.time.LocalDate;

import pads.antiquary.model.exceptions.InvalidPassword;
import pads.antiquary.model.misc.Password;

/**
 * Class of the application users
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public abstract class User extends Person {

	/** Username to login in the app */
	private String userName;

	/**
	 * Password of the user, it has to be stored hashed according the Password
	 * class methods
	 */
	private String password;

	public User(String userName, String name, String surname, LocalDate birthDay, String email, int phone,
			Gender gender, String adress, String passwordhashed) {
		super(name, surname, birthDay, email, phone, gender, adress);
		this.userName = userName;
		this.password = passwordhashed;
	}

	public User(String userName, String name, String surname, LocalDate birthDay, String email, int phone,
			Gender gender, String adress, String password, boolean hash) throws Exception {
		super(name, surname, birthDay, email, phone, gender, adress);
		this.userName = userName;
		if (hash == true)
			this.password = Password.getSaltedHash(password);
		else
			this.password = password;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Method to change the password
	 * 
	 * @param password
	 *            the new password
	 * @throws InvalidPassword
	 */
	public void changePassword(String password) throws InvalidPassword {
		if(password.equals("")) throw new InvalidPassword();
		try {
			this.password = Password.getSaltedHash(password);
		} catch (Exception e) {
			throw new InvalidPassword();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	public abstract String toPrint();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + ";" + userName + ";" + password;
	}
	
	public abstract boolean isManager();
}