package pads.antiquary.model.sales;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class with the methods to control the auction time in background It's
 * designed to be created when the auction's time start, when it finish the
 * auctionTimer invokes the finish method
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class AuctionTimer {

	/** Timer of the class */
	private Timer timer;

	/**
	 * Constructor of the class AuctionTimer
	 * 
	 * @param auction
	 *            to be controled
	 * @param time
	 *            remaining time to finish the auction
	 * @param isTest
	 *            changes the hours to seconds to test the function
	 */
	public AuctionTimer(Auction auction, Long time, boolean isTest) {
		timer = new Timer();
		if (!isTest) {
			timer.schedule(new RemindTask(auction), time * 1000 * 3600);
		} else
			timer.schedule(new RemindTask(auction), time * 1000);

	}

	/**
	 * Constructor of the class AuctionTimer
	 * 
	 * @param auction
	 *            to be controled
	 */
	public AuctionTimer(Auction auction) {
		this(auction, Long.valueOf(auction.getDuration()), false);

	}

	/**
	 * Constructor of the class AuctionTimer
	 * 
	 * @param auction
	 *            to be controled
	 */
	public AuctionTimer(Auction auction, Long time) {
		this(auction, time, false);
	}

	/**
	 * @return the timer
	 */
	public Timer getTimer() {
		return timer;
	}

	/** Internal class to configure the action that the timer have to do */
	private class RemindTask extends TimerTask {

		/** Auction to finish */
		private Auction auction;

		/**
		 * Constructor of the RemindTaske
		 * 
		 * @param auction
		 *            to invoke the finish method
		 */
		public RemindTask(Auction auction) {
			this.auction = auction;
		}

		@Override
		public void run() {
			auction.finish();
		}
	}
}
