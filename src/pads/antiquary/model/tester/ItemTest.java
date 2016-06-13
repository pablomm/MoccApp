package pads.antiquary.model.tester;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import pads.antiquary.model.item.Authenticity;
import pads.antiquary.model.item.Bulky;
import pads.antiquary.model.item.Item;
import pads.antiquary.model.item.Small;
import pads.antiquary.model.item.TypeOfWork;
import pads.antiquary.model.item.WorkOfArt;

/**
 * Testing the right runing of Item class and their functions
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class ItemTest {

	private Item item1;
	private Item item2;
	private Item item3;
	private Item item4;

	@Before
	public void newItem() {
		item1 = new WorkOfArt("Girl #1 in bronze", "19xx", LocalDate.of(2014, 8, 10), 200.00, 600.00, "French School",
				TypeOfWork.Sculpture, Authenticity.N);
		item2 = new Bulky("Four-seat wooden pew;", "1916", LocalDate.of(2016, 1, 15), 75.00, 100.00, 32, 86, 175, 50);
		item3 = new Small("African ethnic Wooden mask", "0", LocalDate.of(2013, 5, 11), 2.00, 100.00, 10.00);
		item4 = new WorkOfArt("Girl #1 in bronze", "19xx", LocalDate.of(2010, 1, 21), 190.00, 610.00, "Italian School",
				TypeOfWork.Sculpture, Authenticity.N);

		item1.setInAuction(true);
		item2.setSold(true);

	}

	/**
	 * Tests the running of the constructor of Item
	 * 
	 * @author Pablo Marcos Manchon
	 * @author Diana Rojas Lopez
	 */
	@Test
	public void testItem() {
		assertEquals("Girl #1 in bronze", item1.getDescription());
		assertEquals("19xx", item1.getManufactureYear());
		assertEquals(LocalDate.of(2014, 8, 10), item1.getAcquisitionDate());
		assertEquals(Double.valueOf(200), item1.getAcquisitionCost());
		assertEquals(Double.valueOf(600), item1.getTargetPrice());
	}

	@Test
	public void testIsEnable() {
		assertFalse(item1.isEnable());
		assertFalse(item2.isEnable());
		assertTrue(item3.isEnable());

	}

	@Test
	public void testGetDiscount() {
		assertEquals(Double.valueOf(0), item1.getDiscount());
		assertEquals(Double.valueOf(0), item2.getDiscount());
		assertEquals(Double.valueOf(10), item3.getDiscount());

	}

	@Test
	public void testDeliveryCost() {
		assertEquals(Double.valueOf(0), item1.deliveryCost());
		assertEquals(Double.valueOf(26.8), item2.deliveryCost());
		assertEquals(Double.valueOf(0), item3.deliveryCost());
		assertEquals(true, true);

	}

	@Test
	public void testEqualsObject() {
		assertFalse(item1.equals(item2));
		assertFalse(item1.equals(null));
		assertFalse(item1.equals(""));
		assertTrue(item1.equals(item4));

	}

}