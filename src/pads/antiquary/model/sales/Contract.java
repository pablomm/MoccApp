package pads.antiquary.model.sales;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import pads.antiquary.model.misc.Settings;

/**
 * Class to implements the antiquary contracts
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class Contract {

	/** Type of the contract */
	private ContractType type;
	/** The start date of the contract */
	private LocalDate contractDate;

	/**
	 * Constructor of the class
	 * 
	 * @param type
	 *            Type of the contract
	 * @param contractDate
	 *            start date of the contract
	 */
	public Contract(ContractType type, LocalDate contractDate) {
		super();
		this.type = type;
		this.contractDate = contractDate;
	}

	/**
	 * Constructor of the class
	 * 
	 * @param type
	 *            type of contract
	 */
	public Contract(ContractType type) {
		this(type, LocalDate.now());
	}

	/**
	 * @return the type
	 */
	public ContractType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(ContractType type) {
		this.type = type;
	}

	/**
	 * @return the contractDate
	 */
	public LocalDate getContractDate() {
		return contractDate;
	}

	/**
	 * @param contractDate
	 *            the contractDate to set
	 */
	public void setContractDate(LocalDate contractDate) {
		this.contractDate = contractDate;
	}

	/** Renovates the contract */
	public void renovate() {
		this.contractDate = LocalDate.now();
	}

	/** Renovates the contract changing the type */
	public void renovate(ContractType type) {
		this.type = type;
		renovate();
	}

	/**
	 * Checks if the contract is valid
	 * 
	 * @return true if it's vigent
	 */
	public boolean isValid() {
		return this.contractDate.plusDays(Settings.getCONTRACT_DURATION()).isAfter(LocalDate.now());
	}

	@Override
	public String toString() {
		return type + ";" + contractDate.format(DateTimeFormatter.ofPattern("d/L/yyyy"));
	}

}
