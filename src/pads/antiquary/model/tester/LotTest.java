package pads.antiquary.model.tester;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import pads.antiquary.model.exceptions.Duplicated;
import pads.antiquary.model.item.Authenticity;
import pads.antiquary.model.item.Bulky;
import pads.antiquary.model.item.Item;
import pads.antiquary.model.item.Lot;
import pads.antiquary.model.item.Small;
import pads.antiquary.model.item.TypeOfWork;
import pads.antiquary.model.item.WorkOfArt;

public class LotTest {

	private Item item1;
	private Item item2;
	private Item item3;

	private Lot lot1;

	@Before
	public void newLot() {
		item1 = new WorkOfArt("Girl #1 in bronze", "19xx", LocalDate.of(2014, 8, 10), 200.00, 600.00, "French School",
				TypeOfWork.Sculpture, Authenticity.N);
		item2 = new Bulky("Four-seat wooden pew;", "1916", LocalDate.of(2016, 1, 15), 75.00, 100.00, 32, 86, 175, 50);
		item3 = new Small("African ethnic Wooden mask", "0", LocalDate.of(2013, 5, 11), 2.00, 100.00, 10.00);

		lot1 = new Lot();

		item2.setSold(true);
	}

	@Test(expected = Duplicated.class)

	public void testAddItem1() throws Duplicated {
		lot1.addItem(item2);
		lot1.addItem(item2);

	}

	@Test
	public void testAddItem2() throws Duplicated {
		lot1.addItem(item2);
		lot1.addItem(item3);

	}

	@Test
	public void testIsEnable1() throws Duplicated {
		lot1.addItem(item2);
		lot1.addItem(item3);
		assertEquals(false, lot1.isEnable());

	}

	@Test
	public void testIsEnable2() throws Duplicated {
		lot1.addItem(item1);
		assertEquals(true, lot1.isEnable());

	}

	@Test
	public void testgetItems() throws Duplicated {
		lot1.addItem(item2);
		lot1.addItem(item3);
		assertEquals(true, lot1.getItems().contains(item2));
		assertEquals(true, lot1.getItems().contains(item3));

	}

}
