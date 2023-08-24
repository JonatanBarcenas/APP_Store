package view;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Product;
import model.Restock;

import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;




public class ListRestockPane extends JPanel {
	
	private List<Restock> restocks;
	private DefaultTableModel model;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public ListRestockPane() {
		this.restocks = Main.getRestocksDataService().getAllRestocks();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{207, 1069, 199, 0};
		gridBagLayout.rowHeights = new int[]{58, 502, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		model = new DefaultTableModel();
		model.addColumn("FOLIO");
		model.addColumn("DATE");
		model.addColumn("PROVIDER");
		model.addColumn("TOTAL");
		fillTable();
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		table = new JTable(model);
		table.setRowHeight(32);
		table.setFont(new Font("Roboto", Font.PLAIN, 15));
		scrollPane.setViewportView(table);

	}
	
	
	
	public JTable getTable() {
		return table;
	}



	public void fillTable() {
		List<Restock> list = restocks;
		for (Restock restock : restocks) {
			Object[] row = new Object[4];
			row[0] = restock.getFolio();
			row[1] = restock.getDate();
			row[2] = restock.getProvider().getName();
			row[3]= restock.getTotal();
			model.addRow(row);
		}
	}
}
