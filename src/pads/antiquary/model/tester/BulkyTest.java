package pads.antiquary.model.tester;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import pads.antiquary.model.item.Bulky;

/**
 * Testing the right runing of Bulky class and their functions
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class BulkyTest {

	private Bulky bulky1;
	private Bulky bulky2;
	private Bulky bulky3;

	@Before
	public void newBulky() {
		bulky1 = new Bulky("Stand in mahogany", "198x", LocalDate.of(2013, 7, 7), 50.00, 100.00, 12, 110, 45, 45);
		bulky2 = new Bulky("Four-seat wooden pew", "1916", LocalDate.of(2016, 1, 15), 75.00, 100.00, 32, 86, 175, 50);
		bulky3 = new Bulky("Stand in mahogany", "1899", LocalDate.of(2016, 8, 8), 60.00, 110.00, 13, 120, 44, 44);
	}

	@Test
	public void testBulky() {
		assertEquals("Stand in mahogany", bulky1.getDescription());
		assertEquals("198x", bulky1.getManufactureYear());
		assertEquals(LocalDate.of(2013, 7, 7), bulky1.getAcquisitionDate());
		assertEquals(Double.valueOf(50.00), bulky1.getAcquisitionCost());
		assertEquals(Double.valueOf(100), bulky1.getTargetPrice());
	}

	@Test
	public void testBulky1() {
		assertEquals(Integer.valueOf(12), bulky1.getDimension().getWeight());
		assertEquals(Integer.valueOf(110), bulky1.getDimension().getHeight());
		assertEquals(Integer.valueOf(45), bulky1.getDimension().getWidth());
		assertEquals(Integer.valueOf(45), bulky1.getDimension().getLength());
	}

	@Test
	public void testBulky2() {
		Bulky bulkyStr = new Bulky(bulky1.toString().split(";"));

		assertEquals(bulkyStr.getDescription(), bulky1.getDescription());
		assertEquals(bulkyStr.getManufactureYear(), bulky1.getManufactureYear());
		assertEquals(bulkyStr.getAcquisitionDate(), bulky1.getAcquisitionDate());
		assertEquals(bulkyStr.getAcquisitionCost(), bulky1.getAcquisitionCost());
		assertEquals(bulkyStr.getTargetPrice(), bulky1.getTargetPrice());
		assertEquals(Integer.valueOf(12), bulkyStr.getDimension().getWeight());
		assertEquals(Integer.valueOf(110), bulkyStr.getDimension().getHeight());
		assertEquals(Integer.valueOf(45), bulkyStr.getDimension().getWidth());
		assertEquals(Integer.valueOf(45), bulkyStr.getDimension().getLength());
	}

	@Test
	public void testEqualsObject() {
		assertEquals(false, bulky1.equals(bulky2));
		assertEquals(false, bulky1.equals(null));
		assertEquals(false, bulky1.equals(""));
		assertEquals(true, bulky1.equals(bulky3));
	}

}