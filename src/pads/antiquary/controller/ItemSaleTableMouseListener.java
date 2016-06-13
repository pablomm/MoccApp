package pads.antiquary.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import pads.antiquary.model.exceptions.NotFound;
import pads.antiquary.model.factory.Shop;
import pads.antiquary.view.SalesPanel;

/**
 * Controller of the panel
 * select the sale of the table
 * @author Pablo Marcos Manchon
 * @author Diana Rojas Lopez
 */
public class ItemSaleTableMouseListener implements MouseListener {

	/**
	 * Panel of the controller
	 */
	private SalesPanel panel;

	public ItemSaleTableMouseListener(SalesPanel panel) {
		this.panel = panel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (panel.getItemTable().getSelectedRow() == -1)
			return;

		try {
			panel.setSelectItem(Shop.getShop().searchExactItem(
					(String) panel.getItemTable().getValueAt(panel.getItemTable().getSelectedRow(), 0)));
			panel.refreshPanel();
		} catch (NotFound e1) {
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
