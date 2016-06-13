package pads.antiquary.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import pads.antiquary.model.misc.ReportSales;
import pads.antiquary.model.sales.Auction;
import pads.antiquary.model.sales.Sale;
import pads.antiquary.view.SalesReportPanel;

/**
 * Controller of the panel
 * controller of the reports
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class ReportSaleController implements ActionListener {

	/**
	 * Header to the auction table
	 */
	private static final String[] AUCTION_HEADER = { "Auction", "Higest Bid", "Active" };
	
	/**
	 * Header to the sale table
	 */
	private static final String[] SALE_HEADER = { "Customer", "Date", "Price" };

	/**
	 * Panel of the controller
	 */
	private SalesReportPanel panel;

	public ReportSaleController(SalesReportPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (panel.getType()) {
		case 0:
			updateAuction();
			break;

		default:
			updateSales();
			break;
		}

	}

	public void updateAuction() {

		DefaultTableModel dtm = new DefaultTableModel();

		dtm.setColumnIdentifiers(AUCTION_HEADER);

		for (Auction auction : ReportSales.auctionInDate(
				LocalDate.now().minusMonths(panel.getMonths()).format(DateTimeFormatter.ofPattern("d/L/yyyy")),
				LocalDate.now().format(DateTimeFormatter.ofPattern("d/L/yyyy")))) {

			Vector<Object> data = new Vector<Object>();
			data.add(auction.getName());

			if (auction.getLastBid() == null)
				data.add(auction.getTargetPrice());
			else
				data.add(auction.getLastBid().getPrice());

			data.add(auction.isActive() ? "Active" : "Finished");
			dtm.addRow(data);

		}
		panel.getTable().setModel(dtm);

	}
	
	public void updateSales() {
	
		DefaultTableModel dtm = new DefaultTableModel();

		dtm.setColumnIdentifiers(SALE_HEADER);

		for (Sale s : ReportSales.salesInDate(
				LocalDate.now().minusMonths(panel.getMonths()).format(DateTimeFormatter.ofPattern("d/L/yyyy")),
				LocalDate.now().format(DateTimeFormatter.ofPattern("d/L/yyyy")))) {

			Vector<Object> data = new Vector<Object>();
			data.add(s.getCustomer().getName() + " " + s.getCustomer().getSurname());


				data.add(s.getDate().format(DateTimeFormatter.ofPattern("d/L/yyyy")));

			data.add(s.getFinalPrice());
			dtm.addRow(data);

		}
		panel.getTable().setModel(dtm);

	}

}
