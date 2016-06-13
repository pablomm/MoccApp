package pads.antiquary.model.tester;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import pads.antiquary.model.item.Authenticity;
import pads.antiquary.model.item.TypeOfWork;
import pads.antiquary.model.item.WorkOfArt;

public class WorkOfArtTest {

	private WorkOfArt workOfArt1;
	private WorkOfArt workOfArt2;

	@Before
	public void newWorkOfArt() {
		workOfArt1 = new WorkOfArt("Mater Amabilis Ora Pro Novis", "1951", LocalDate.of(2014, 9, 15), 65.00, 120.00,
				"Joan Llimona Bruguera", TypeOfWork.Print, Authenticity.Y);
		workOfArt2 = new WorkOfArt("Girl #1 in bronze", "19xx", LocalDate.of(2014, 8, 10), 200.00, 600.00,
				"French School", TypeOfWork.Sculpture, Authenticity.N);
	}

	@Test
	public void testWorkOfArt() {
		assertEquals("Girl #1 in bronze", workOfArt2.getDescription());
		assertEquals("19xx", workOfArt2.getManufactureYear());
		assertEquals(LocalDate.of(2014, 8, 10), workOfArt2.getAcquisitionDate());
		assertEquals(Double.valueOf(200.00), workOfArt2.getAcquisitionCost());
		assertEquals(Double.valueOf(600.00), workOfArt2.getTargetPrice());
		assertEquals("French School", workOfArt2.getAuthor());
		assertEquals(TypeOfWork.Sculpture, workOfArt2.getType());
		assertEquals(Authenticity.N, workOfArt2.getCertificate());
	}

	@Test
	public void testWorkOfArt2() {
		WorkOfArt workOfArtStr = new WorkOfArt(workOfArt1.toString().split(";"));

		assertEquals(workOfArtStr.getDescription(), workOfArt1.getDescription());
		assertEquals(workOfArtStr.getManufactureYear(), workOfArt1.getManufactureYear());
		assertEquals(workOfArtStr.getAcquisitionDate(), workOfArt1.getAcquisitionDate());
		assertEquals(workOfArtStr.getAcquisitionCost(), workOfArt1.getAcquisitionCost());
		assertEquals(workOfArtStr.getTargetPrice(), workOfArt1.getTargetPrice());
		assertEquals(workOfArtStr.getAuthor(), workOfArt1.getAuthor());
		assertEquals(workOfArtStr.getType(), workOfArt1.getType());
		assertEquals(workOfArtStr.getCertificate(), workOfArt1.getCertificate());

	}

	@Test
	public void testGetDiscount() {
		assertEquals(Double.valueOf(0.00), workOfArt1.getDiscount());
		assertEquals(Double.valueOf(0.00), workOfArt2.getDiscount());

	}

	@Test
	public void testDeliveryCost() {
		assertEquals(Double.valueOf(0.00), workOfArt1.getDiscount());
		assertEquals(Double.valueOf(0.00), workOfArt2.getDiscount());
	}

	@Test
	public void testGetItems() {
		assertEquals(true, workOfArt1.getItems().contains(workOfArt1));
	}

}
