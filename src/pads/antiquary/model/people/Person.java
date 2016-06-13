package pads.antiquary.model.people;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class to store people data
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public abstract class Person {

	/** Name of the person */
	private String name;
	/** Surname of the person */
	private String surname;
	/** Birthday of the person */
	private LocalDate birthDay;
	/** email of the person */
	private String email;
	/** phone number with 9 digits */
	private int phone;
	/** male/female */
	private Gender gender;
	/** adress */
	private String adress;

	/**
	 * Constructor of the class person
	 * 
	 * @param name
	 * @param surname
	 * @param birthDay
	 * @param email
	 * @param phone
	 * @param gender
	 * @param adress
	 */
	public Person(String name, String surname, LocalDate birthDay, String email, int phone, Gender gender,
			String adress) {
		this.name = name;
		this.surname = surname;
		this.birthDay = birthDay;
		this.email = email;
		this.phone = phone;
		this.gender = gender;
		this.adress = adress;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @param surname
	 *            the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * @return the birthDay
	 */
	public LocalDate getBirthDay() {
		return birthDay;
	}

	/**
	 * @param birthDay
	 *            the birthDay to set
	 */
	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public int getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(int phone) {
		this.phone = phone;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the adress
	 */
	public String getAdress() {
		return adress;
	}

	/**
	 * @param adress
	 *            the adress to set
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}

	@Override
	public String toString() {
		return name + ";" + surname + ";" + birthDay.format(DateTimeFormatter.ofPattern("d/L/yyyy")) + ";" + email + ";"
				+ phone + ";" + gender + ";" + adress;
	}

}
