package pads.antiquary.model.sales;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import pads.antiquary.model.exceptions.*;
import pads.antiquary.model.factory.Shop;
import pads.antiquary.model.item.*;
import pads.antiquary.model.misc.*;
import pads.antiquary.model.people.Customer;

/**
 * Class with the methods relative to the auctions
 * 
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class Auction {
	/** Name of the auction */
	private String name;
	/** Description of the auction */
	private String description;
	/** Initial price of the auction */
	private int targetPrice;
	/** Entry price of the auction */
	private int entryPrice;
	/** Indicates if the auction is active */
	private boolean active;
	/** Highest bid */
	private Bid lastBid;
	/** Date of the first bid */
	private LocalDateTime firstBidDate;
	/** Duration of the auction */
	private int duration;
	/** Array with all the bids */
	private ArrayList<Bid> bid;
	/** Array with all the customer that paid the entry */
	private ArrayList<Customer> bidder;
	/** Object to be auctioned */
	private Auctionable item;
	/** Timer of the auction */
	private AuctionTimer timer;

	/**
	 * Constructor of the auction class
	 * 
	 * @param name
	 *            Name of the auction
	 * @param description
	 *            Description of the auction
	 * @param targetPrice
	 *            Initial price of the auction
	 * @param entryPrice
	 *            Entry price of the auction
	 * @param duration
	 *            Duration of the auction in Hours
	 * @param item
	 *            Object to be auctioned
	 */
	public Auction(String name, String description, int targetPrice, int entryPrice, int duration, Auctionable item)
			throws Exception {
		this.name = name;
		this.description = description;
		this.targetPrice = targetPrice;
		this.entryPrice = entryPrice;
		this.duration = duration;
		if (item.isEnable()) {
			this.item = item;
		} else {
			throw new Exception();
		}
		this.bid = new ArrayList<Bid>();
		this.bidder = new ArrayList<Customer>();
		this.active = true;
		item.setInAuction(true); // Change the state of the items
	}

	public Auction(String[] auctionSplit) throws NotFound, InvalidBid, NotInAuction {
		this.name = auctionSplit[0];
		this.description = auctionSplit[1];
		this.targetPrice = Integer.parseInt(auctionSplit[2]);
		this.entryPrice = Integer.parseInt(auctionSplit[3]);
		this.active = Boolean.getBoolean(auctionSplit[4]);
		this.bidder = new ArrayList<Customer>();
		this.bid = new ArrayList<Bid>();
		// this.lastBid = new Bid(auctionSplit[5].split("#"));
		if(this.firstBidDate != null)
			this.firstBidDate = LocalDateTime.parse(auctionSplit[6]);
		this.duration = Integer.parseInt(auctionSplit[7]);

		Lot lot = new Lot();
		for (String itemStr : auctionSplit[10].split(",")) {
			try {
				lot.addItem(Shop.getShop().searchExactItem(itemStr));
			} catch (Duplicated e) {
				// If an item is duplicated in the file we ignore them
			}
		}

		this.item = lot;

		for (String userID : auctionSplit[8].split(",")) {
			try {
				this.entry(Shop.getShop().searchCustomerbyID(userID));
			} catch (Duplicated e) {
				// If an item is duplicated in the file we ignore them
			}
		}

		for (String bidSplit : auctionSplit[9].split(",")) {
			this.bid.add(new Bid(bidSplit.split("#")));

		}
		// If the auction is not active we put the items in sold state
		if (!this.active) {
			this.item.setSold(true);

			// Case the auction finished whe the application was close
		} else if (!this.checkAuction()) {
			this.active = false;
			this.item.setSold(true);
			this.item.setInAuction(false);
			this.lastBid.getCustomer().increaseSpend(this.lastBid.getPrice());
			NotificationSystem.notificateAuctionFinish(this);
			// Case the auction is yet active
		} else {
			// throws a timer with the remainig time duration
			// to calculate it uses the difference of the Epoch seconds
			this.timer = new AuctionTimer(this, (LocalDateTime.now().getLong(ChronoField.INSTANT_SECONDS)
					- this.firstBidDate.getLong(ChronoField.INSTANT_SECONDS)) / 3600);
		}

	}

	/**
	 * @param timer the timer to set
	 */
	public void setTimer(AuctionTimer timer) {
		this.timer = timer;
	}

	/**
	 * @return the timer
	 */
	public AuctionTimer getTimer() {
		return timer;
	}

	/**
	 * Add new bid to the auction, the customer have to be in the auction and
	 * the bid have to be higher than the current bid
	 * 
	 * @param bid
	 *            The new bid
	 * @throws InvalidBid
	 *             In case that the auction isn't active or if the new bid is
	 *             lower than the current one
	 * @throws NotInAuction
	 *             due to the customer is not in the auction
	 */
	public void addBid(Bid bid) throws InvalidBid, NotInAuction {

		if (this.checkAuction() == false)
			throw new InvalidBid();
		if (!this.bidder.contains(bid.getCustomer()))
			throw new NotInAuction();

		else if (this.bid.isEmpty()) { /* First bid case */
			if (bid.getPrice() < this.targetPrice)
				throw new InvalidBid();
			this.firstBidDate = LocalDateTime.now();
			this.timer = new AuctionTimer(this); // Throw the timer

		} else if (bid.getPrice() <= this.lastBid.getPrice())
			throw new InvalidBid();

		this.bid.add(bid);
		this.lastBid = bid;
		NotificationSystem.notificateBid(this);
	}

	/**
	 * Check if the auction is active
	 * 
	 * @return true if it is active
	 */
	public boolean checkAuction() {
		if (this.firstBidDate == null || this.active == false)
			return this.active;
		if (firstBidDate.plusHours(this.duration).isAfter(LocalDateTime.now()))
			return true;
		this.active = false;
		return this.active;
	}

	/**
	 * Cancel the auction, only can be canceled if there aren't any bids
	 * 
	 * @throws InvalidCancel
	 *             In case exist bids
	 */
	public void cancelAuction() throws InvalidCancel {
		if (this.lastBid != null)
			throw new InvalidCancel();
		this.active = false;
		this.item.setInAuction(false);
	}

	/**
	 * Introduce a customer in the auction, calculates the price
	 * 
	 * @param customer
	 *            that will enter
	 * @return The price to enter in the auction
	 * @throws Duplicated
	 *             In case the customer is in the auction yet
	 */
	public Integer entry(Customer customer) throws Duplicated {

		if (this.bidder.contains(customer))
			throw new Duplicated();
		this.bidder.add(customer);
		customer.increaseNumberAuctions();
		// Calculating the price
		if (customer.getContract().getType() == ContractType.Vip) {
			return Integer.valueOf(0);
		} else if (customer.getContract().getType() == ContractType.Standard
				&& customer.getNumberAuctions() <= Settings.getSTANDARD_FREE_ENTRYS()) {
			return Integer.valueOf(0);
		} else
			return this.entryPrice;
	}

	/**
	 * Change the state of the auction, only can be changed if ther aren't any
	 * bids
	 * 
	 * @param state
	 *            new state
	 * @throws Exception
	 *             In case it's not empty
	 */
	public void changeActive(boolean state) throws Exception {
		if (this.bid.isEmpty())
			this.active = state;
		else
			throw new Exception();
	}

	/**
	 * Finalize the auction and send a notification to the customers
	 * 
	 */
	public void finish() {
		this.active = false;
		this.item.setSold(true);
		this.item.setInAuction(false);
		this.lastBid.getCustomer().increaseSpend(this.lastBid.getPrice());
		NotificationSystem.notificateAuctionFinish(this);
		this.timer.getTimer().cancel();
	}

	/** @return the description of the auction */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/** @return the name of the auction */
	public String getName() {
		return name;
	}

	/** @return the initial price of the auction */
	public int getTargetPrice() {
		return targetPrice;
	}

	/** @return the price to enter */
	public int getEntryPrice() {
		return entryPrice;
	}

	/** @return the state of the auction */
	public boolean isActive() {
		return active;
	}

	/** @return The highest bid */
	public Bid getLastBid() {
		return lastBid;
	}

	/** @return the date of the first bid */
	public LocalDateTime getFirstBidDate() {
		return firstBidDate;
	}

	/** @return the duration in hours of the bid */
	public int getDuration() {
		return duration;
	}

	/** @return an array with all the bids */
	public ArrayList<Bid> getBid() {
		return bid;
	}

	/** @return the item to be auctioned */
	public Auctionable getItem() {
		return item;
	}

	/** @return an array with all the bidders */
	public ArrayList<Customer> getBidder() {
		return this.bidder;
	}

	/** @return a String to print in the notification */
	public String toPrint() {

		return "-----------------------------\n" + this.name + "\n" + this.description + "\n"
				+ "The auction finish  at " + firstBidDate.plusHours(this.duration) + "\n" + "Highest Bid: \n"
				+ this.lastBid.toPrint() + "-----------------------------";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Auction other = (Auction) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String ret = name + ";" + description + ";" + targetPrice + ";" + entryPrice + ";" + active + ";"
				+ /* lastBid */" " + ";" + firstBidDate + ";" + duration + ";";

		// Prints the bidders
		for (Customer c : this.getBidder()) {
			ret += Integer.valueOf(c.getCustomerID()).toString() + ",";
		}
		ret += ";";
		// Prints the bids
		for (Bid b : this.getBid()) {
			ret += b.toString();
		}
		ret += ";";
		// Prints the description of the items to be auctioned
		for (Item i : this.getItem().getItems()) {
			ret += i.getDescription() + ",";
		}

		return ret;
	}

}
