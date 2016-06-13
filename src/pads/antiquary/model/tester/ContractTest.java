package pads.antiquary.model.tester;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import pads.antiquary.model.sales.Contract;
import pads.antiquary.model.sales.ContractType;

public class ContractTest {

	private Contract contract1;

	@Before
	public void newContract() {

		contract1 = new Contract(ContractType.Standard, LocalDate.now());
	}

	@Test
	public void testContract1() {
		assertEquals(ContractType.Standard, contract1.getType());
		assertEquals(LocalDate.now(), contract1.getContractDate());

	}

	@Test
	public void testContract2() {
		assertEquals(ContractType.Standard, contract1.getType());
	}

	@Test
	public void testRenovate1() {
		contract1.renovate();

		assertEquals(LocalDate.now(), contract1.getContractDate());
	}

	@Test
	public void testRenovate2() {
		contract1.renovate(ContractType.Vip);

		assertEquals(ContractType.Vip, contract1.getType());

	}

}
