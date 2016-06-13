/**
 * 
 */
package pads.antiquary.model.misc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.item.Item;
import pads.antiquary.model.sales.Auction;
import pads.antiquary.model.sales.Sale;

/**
 * Class to provide information about all the sales and auctions
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 * 
 */
public class ReportSales {

	/**
	 * Search sales in a time interval
	 * 
	 * @param first
	 *            first date
	 * @param last
	 *            Last date
	 * @return array with the dates
	 */
	public static ArrayList<Sale> salesInDate(String first, String last) {
		ArrayList<Sale> array = new ArrayList<Sale>();

		for (Sale s : Shop.getShop().getSales()) {
			if (s.getDate().isAfter(LocalDate.parse(first, DateTimeFormatter.ofPattern("d/L/yyyy")))
					&& s.getDate().isBefore(LocalDate.parse(last, DateTimeFormatter.ofPattern("d/L/yyyy")))) {
				array.add(s);
			}
		}
		return array;
	}

	/**
	 * Search auctions in a time interval
	 * 
	 * @param first
	 *            First date
	 * @param last
	 *            Last date
	 * @return array with the dates
	 */
	public static ArrayList<Auction> auctionInDate(String first, String last) {
		ArrayList<Auction> array = new ArrayList<Auction>();
		for (Auction a : Shop.getShop().getAuctions()) {
			if (a.getFirstBidDate() != null)
				if (a.getFirstBidDate().toLocalDate()
						.isAfter(LocalDate.parse(first, DateTimeFormatter.ofPattern("d/L/y")))
						&& a.getFirstBidDate().toLocalDate()
								.isBefore(LocalDate.parse(last, DateTimeFormatter.ofPattern("d/L/y")))) {
					array.add(a);
				}
		}
		return array;
	}

	/**
	 * Calculates the money ingresed in the shop
	 * 
	 * @param first
	 *            first date
	 * @param last
	 *            last date
	 * @return money ingresed
	 */
	public static Double ingresedIn(String first, String last) {
		ArrayList<Auction> arrayA = auctionInDate(first, last);
		ArrayList<Sale> arrayS = salesInDate(first, last);
		Double ingresed = 0.0;

		for (Auction a : arrayA) {
			if (!a.isActive() && a.getLastBid() != null)
				ingresed += a.getLastBid().getPrice();
		}

		for (Sale s : arrayS) {
			ingresed += s.getFinalPrice();
		}
		return ingresed;

	}

	/**
	 * Calculates the money spent in the shop
	 * 
	 * @param first
	 *            first date
	 * @param last
	 *            last date
	 * @return money ingresed
	 */
	public static Double adquisitionCost(String first, String last) {
		ArrayList<Auction> arrayA = auctionInDate(first, last);
		ArrayList<Sale> arrayS = salesInDate(first, last);
		Double ingresed = 0.0;

		for (Auction a : arrayA) {
			if (!a.isActive() && a.getLastBid() != null)
				for (Item i : a.getItem().getItems()) {
					ingresed += i.getAcquisitionCost();
				}
		}

		for (Sale s : arrayS) {
			for (Item i : s.getItems()) {
				ingresed += i.getAcquisitionCost();
			}
		}
		return ingresed;

	}

	/**
	 * Calculates the benefits of the shop
	 * 
	 * @param first
	 *            first date
	 * @param last
	 *            last date
	 * @return money ingresed
	 */
	public static Double benefits(String first, String last) {
		return ingresedIn(first, last) - adquisitionCost(first, last);

	}

}
