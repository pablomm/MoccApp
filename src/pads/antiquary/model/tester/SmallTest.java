package pads.antiquary.model.tester;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import pads.antiquary.model.item.*;

public class SmallTest {

	private Small Small1;
	private Small Small2;
	private Small Small3;

	@Before
	public void newSmall() {
		Small1 = new Small("Glass bottle (19th century) Hand holding a colt", "0", LocalDate.of(2013, 5, 11), 55.00,
				125.00, 5.00);
		Small2 = new Small("Two small French table lamps", "0", LocalDate.of(2014, 12, 21), 60.00, 120.00, 0.00);
		Small3 = new Small("African ethnic Wooden mask", "0", LocalDate.of(2013, 5, 11), 2.00, 100.00, 10.00);

	}

	@Test
	public void testSmall1() {
		assertEquals("Two small French table lamps", Small2.getDescription());
		assertEquals("0", Small1.getManufactureYear());
		assertEquals(LocalDate.of(2014, 12, 21), Small2.getAcquisitionDate());
		assertEquals(Double.valueOf(55.00), Small1.getAcquisitionCost());
		assertEquals(Double.valueOf(125.00), Small1.getTargetPrice());
		assertEquals(Double.valueOf(5.00), Small1.getDiscount());
	}

	@Test
	public void testSmall2() {
		Small SmallStr = new Small(Small1.toString().split(";"));

		assertEquals(SmallStr.getDescription(), Small1.getDescription());
		assertEquals(SmallStr.getManufactureYear(), Small1.getManufactureYear());
		assertEquals(SmallStr.getAcquisitionDate(), Small1.getAcquisitionDate());
		assertEquals(SmallStr.getAcquisitionCost(), Small1.getAcquisitionCost());
		assertEquals(SmallStr.getTargetPrice(), Small1.getTargetPrice());
		assertEquals(SmallStr.getDiscount(), Small1.getDiscount());
		assertEquals(Double.valueOf(5.00), SmallStr.getDiscount());
	}

	@Test
	public void testGetDiscount() {
		assertEquals(Double.valueOf(0.00), Small2.getDiscount());
		assertEquals(Double.valueOf(10.00), Small3.getDiscount());
	}

	@Test
	public void testDeliveryCost() {
		assertEquals(Double.valueOf(0.00), Small2.deliveryCost());
		assertEquals(Double.valueOf(0.00), Small3.deliveryCost());

	}

}