package view;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Product;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;




public class ListPane extends JPanel {
	
	private List<Product> listOfProducts;
	private DefaultTableModel model;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public ListPane() {
		this.listOfProducts = Main.getProductsDataService().getAllProducts();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{31, 1080, 31, 0};
		gridBagLayout.rowHeights = new int[]{58, 502, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		model = new DefaultTableModel();
		model.addColumn("BARCODE");
		model.addColumn("NAME");
		model.addColumn("BRAND");
		model.addColumn("TYPE");
		model.addColumn("CONTENT");
		model.addColumn("UNIT");
		model.addColumn("MAXIMUM STOCK");
		model.addColumn("MINIMUM STOCK");
		model.addColumn("PRESENTATION");
		model.addColumn("DESCRIPTION");
		model.addColumn("ON INVENTORY");
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
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		table = new JTable(model);
		table.setRowHeight(32);
		table.setFont(new Font("Roboto", Font.PLAIN, 15));
		scrollPane.setViewportView(table);

	}
	
	
	
	public List<Product> getListOfProducts() {
		return listOfProducts;
	}



	public void setListOfProducts(List<Product> listOfProducts) {
		this.listOfProducts = listOfProducts;
	}



	public DefaultTableModel getModel() {
		return model;
	}



	public void setModel(DefaultTableModel model) {
		this.model = model;
	}



	public JTable getTable() {
		return table;
	}



	public void setTable(JTable table) {
		this.table = table;
	}



	public void fillTable() {
		List<Product> list = listOfProducts;
		for (Product product : list) {
			Object[] row = new Object[11];
			row[0] = product.getBarcode();
			row[1] = product.getName();
			row[2] = product.getBrand();
			row[3]= product.getType();
			row[4]= product.getContent();
			row[5]= product.getMeasureUnit();
			row[6]= product.getStockMax();
			row[7]= product.getStockMin();
			row[8]=product.getPresentation();
			row[9]=product.getDescription();
			row[10]=product.getOnInventory();
			model.addRow(row);
		}
	}
}
