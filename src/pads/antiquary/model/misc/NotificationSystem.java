package pads.antiquary.model.misc;

import es.uam.eps.padsof.emailconnection.*;
import pads.antiquary.model.people.*;
import pads.antiquary.model.sales.Auction;

/**
 * Class to notificate customers about auctions
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class NotificationSystem {

	private NotificationSystem() {
	};

	/**
	 * Notificates the new bid to clients interested
	 * 
	 * @param auction
	 *            the auction with the customers
	 */
	public static void notificateBid(Auction auction) {
		for (Customer c : auction.getBidder()) {
			if (c.getNotification().equals(Notificate.ALWAYS)) {
				try {
					EmailSystem.send(c.getEmail(), Settings.getBID_SUBJECT() + auction.getName(),
							Settings.getBID_BODY() + auction.toPrint());
				} catch (InvalidEmailAddressException | FailedInternetConnectionException e) {
				}
			}
		}
	}

	/**
	 * Notificates the finish of the auction to clients interested
	 * 
	 * @param auction
	 *            the auction with the customers
	 */
	public static void notificateAuctionFinish(Auction auction) {
		for (Customer c : auction.getBidder()) {
			if (!c.getNotification().equals(Notificate.NEVER)) {
				try {
					EmailSystem.send(c.getEmail(), Settings.getBID_SUBJECT() + auction.getName(),
							Settings.getBID_BODY() + auction.toPrint());
				} catch (InvalidEmailAddressException | FailedInternetConnectionException e) {
				}
			}
		}
	}
}
