package pads.antiquary.model.tester;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import pads.antiquary.model.item.Bulky;

public class DimensionTest {

	private Bulky item1;

	@Before
	public void newItem() {
		item1 = new Bulky("Four-seat wooden pew;", "1916", LocalDate.of(2016, 1, 15), 75.00, 100.00, 32, 86, 175, 50);
	}

	@Test
	public void testDimension() {
		assertEquals(Integer.valueOf(32), item1.getDimension().getWeight());
		assertEquals(Integer.valueOf(86), item1.getDimension().getHeight());
		assertEquals(Integer.valueOf(175), item1.getDimension().getWidth());
		assertEquals(Integer.valueOf(50), item1.getDimension().getLength());
	}

}
