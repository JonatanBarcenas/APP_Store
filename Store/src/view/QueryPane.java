package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.Product;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class QueryPane extends JPanel {
	private JTextField txtSelectProduct;
	private List<Product> productList;
	private DefaultListModel<String> model;
	private List<String> source;
	private List<String> barcodes;
	private List<String> ProductsByBrands;
	private List<String> ProductsByTypes;
	private DefaultTableModel defaultTableModel;
	private JPanel panel;
	private JPanel panel_1;
	private JTable table;
	private String by = "";
	private JPanel panelByName;
	private JPanel panelByBarcode;
	private ModifyPanel modifyPanel;
	private Product tmpProduct;



	/**
	 * Create the panel.
	 */
	public QueryPane(List<Product> productList) {
		this.productList = Main.getProductsDataService().getAllProducts();
		tmpProduct = null;
		model = new DefaultListModel<>();
		defaultTableModel = new DefaultTableModel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{877, 200, 402, 0};
		gridBagLayout.rowHeights = new int[]{58, 44, 335, 58, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		var brands = new ArrayList<String>();
		var types = new ArrayList<String>();
		types.add("By Type");
		brands.add("By Brand");
		for (Product product : Main.getProductsDataService().getAllProducts()) {
			brands.add(product.getBrand());
			types.add(product.getType());
		}

		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);




		JComboBox comboBox_1 = new JComboBox(brands.toArray());
		comboBox_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				by = "By brand";
			}
		});
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		comboBox_1.setFont(new Font("Roboto", Font.PLAIN, 13));
		comboBox_1.setBounds(954, 11, 117, 31);
		panel.add(comboBox_1);

		JComboBox comboBox = new JComboBox(types.toArray());
		comboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				by = "By type";
			}
		});
		comboBox.setFont(new Font("Roboto", Font.PLAIN, 13));
		comboBox.setBounds(827, 11, 117, 31);
		panel.add(comboBox);

		txtSelectProduct = new JTextField();
		txtSelectProduct.setHorizontalAlignment(SwingConstants.CENTER);
		txtSelectProduct.setFont(new Font("Roboto", Font.PLAIN, 15));
		txtSelectProduct.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txtSelectProduct.setBorder(null);
		txtSelectProduct.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String filter = txtSelectProduct.getText();	

				switch (by) {
				case "By barcode":
					model.removeAllElements();
					if (filter.isEmpty()) {
						model.addAll(barcodes);

					}else {
						var filterElemets = barcodes.stream().filter(s -> s.toString().toLowerCase().contains(filter.toLowerCase())).toList();
						model.addAll(filterElemets);

					}
					break;
				case "By name":
					model.removeAllElements();
					if (filter.isEmpty()) {
						model.addAll(source);

					}else {
						var filterElemets = source.stream().filter(s -> s.toString().toLowerCase().contains(filter.toLowerCase())).toList();
						model.addAll(filterElemets);

					}
					break;
				case "By type":

					model.removeAllElements();
					List<String> list1 = new ArrayList<>();
					for (Product product : Main.getProductsDataService().getAllProducts()) {
						if (product.getType().equalsIgnoreCase((String)comboBox.getSelectedItem())) {
							list1.add(product.getName());
						}
					}
					model.addAll(list1);
					break;
				case "By brand":
					model.removeAllElements();
					List<String> list = new ArrayList<>();
					for (Product product : Main.getProductsDataService().getAllProducts()) {
						if (product.getBrand().equalsIgnoreCase((String)comboBox_1.getSelectedItem())) {
							list.add(product.getName());
						}
					}
					model.addAll(list);

					break;

				default:
					break;
				}


			}
		});



		panelByName = new JPanel();
		panelByName.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelByName.setBackground(Color.LIGHT_GRAY);
		panelByName.setBounds(1208, 11, 117, 31);
		panel.add(panelByName);

		JLabel lblNewLabel = new JLabel("By Name");
		lblNewLabel.setFont(new Font("Roboto", Font.PLAIN, 13));
		panelByName.add(lblNewLabel);

		panelByName.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				by = "By name";
				panelByBarcode.setBackground(Color.LIGHT_GRAY);
				panelByName.setBackground(Color.GRAY);

			}
		});



		panelByBarcode = new JPanel();
		panelByBarcode.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelByBarcode.setBackground(Color.LIGHT_GRAY);
		panelByBarcode.setBounds(1081, 11, 117, 31);
		panel.add(panelByBarcode);

		JLabel lblByBarcode = new JLabel("By Barcode");
		lblByBarcode.setFont(new Font("Roboto", Font.PLAIN, 13));
		panelByBarcode.add(lblByBarcode);

		panelByBarcode.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				by = "By barcode";
				panelByBarcode.setBackground(Color.GRAY);
				panelByName.setBackground(Color.LIGHT_GRAY);


			}
		});

		JLabel lblNewLabel_1 = new JLabel("Products");
		lblNewLabel_1.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(134, 11, 518, 31);
		panel.add(lblNewLabel_1);


		txtSelectProduct.setColumns(10);
		GridBagConstraints gbc_txtSelectProduct = new GridBagConstraints();
		gbc_txtSelectProduct.insets = new Insets(0, 0, 5, 5);
		gbc_txtSelectProduct.fill = GridBagConstraints.BOTH;
		gbc_txtSelectProduct.gridx = 0;
		gbc_txtSelectProduct.gridy = 1;
		add(txtSelectProduct, gbc_txtSelectProduct);

		source = new ArrayList<String>();
		barcodes = new ArrayList<String>();
		types = new ArrayList<String>();
		brands = new ArrayList<String>();

		for (Product product : productList) {
			source.add(product.getName());
			barcodes.add(product.getBarcode());
			types.add(product.getType());
			brands.add(product.getBrand());
		}

		defaultTableModel.addColumn("");
		defaultTableModel.addColumn("");


		table = new JTable(defaultTableModel);
		table.setRowHeight(32);

		table.getColumnModel().getColumn(0).setPreferredWidth(1);

		table.setFont(new Font("Roboto Medium", Font.PLAIN, 17));

		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 2;
		add(table, gbc_table);




		JList jListProduct = new JList(model);
		jListProduct.setCellRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				label.setHorizontalAlignment(JLabel.RIGHT); // Centrar el texto en el label
				label.setBorder(new EmptyBorder(7, 7, 7, 20)); // Agregar un margen alrededor del texto
				return label;
			}
		});
		jListProduct.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jListProduct.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		jListProduct.setFont(new Font("Roboto", Font.PLAIN, 15));
		jListProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String key = (String) jListProduct.getSelectedValue();
				Product selectedProduct = new Product();

				for (Product product : productList) {
					if (product.getName().equalsIgnoreCase(key) || product.getBarcode().equalsIgnoreCase(key)) {
						selectedProduct = product;
						tmpProduct = product;
					}
				}

				fillTable(selectedProduct);

			}
		});
		jListProduct.setBorder(new LineBorder(Color.LIGHT_GRAY));
		GridBagConstraints gbc_jListProduct = new GridBagConstraints();
		gbc_jListProduct.gridheight = 2;
		gbc_jListProduct.insets = new Insets(0, 0, 5, 5);
		gbc_jListProduct.fill = GridBagConstraints.BOTH;
		gbc_jListProduct.gridx = 1;
		gbc_jListProduct.gridy = 1;
		add(jListProduct, gbc_jListProduct);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(SystemColor.control);
		panel_1_1.setLayout(null);
		GridBagConstraints gbc_panel_1_1 = new GridBagConstraints();
		gbc_panel_1_1.gridheight = 2;
		gbc_panel_1_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1_1.gridx = 2;
		gbc_panel_1_1.gridy = 1;
		add(panel_1_1, gbc_panel_1_1);

		JButton btnModify = new JButton("Modify\r\n");
		btnModify.setBackground(Color.LIGHT_GRAY);
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {


					if (tmpProduct != null) {
						modifyPanel = new ModifyPanel();

						modifyPanel.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						modifyPanel.setLocationRelativeTo(null);

						modifyPanel.getTxtName().setText(tmpProduct.getName());
						modifyPanel.getTxtBrand().setText(tmpProduct.getBrand());
						modifyPanel.getComboBoxType().setSelectedItem(tmpProduct.getType());
						modifyPanel.getTxtContent().setText(tmpProduct.getContent());
						modifyPanel.getComboBoxUnit().setSelectedItem(tmpProduct.getMeasureUnit());
						modifyPanel.getTxtStockMax().setText(String.valueOf(tmpProduct.getStockMax()));
						modifyPanel.getTxtStockMin().setText(String.valueOf(tmpProduct.getStockMin()));
						modifyPanel.getComboBoxPresentation().setSelectedItem(tmpProduct.getPresentation());
						modifyPanel.getTxtDescription().setText(tmpProduct.getDescription());

						modifyPanel.getOkButton().addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {

								Product product = new Product();
								product.setBarcode(tmpProduct.getBarcode());
								product.setName(modifyPanel.getTxtName().getText());
								product.setBrand(modifyPanel.getTxtBrand().getText());
								product.setType((String) modifyPanel.getComboBoxType().getSelectedItem());
								product.setContent(modifyPanel.getTxtContent().getText());
								product.setMeasureUnit((String) modifyPanel.getComboBoxUnit().getSelectedItem());
								product.setStockMax(Integer.parseInt(modifyPanel.getTxtStockMax().getText()));
								product.setStockMin(Integer.parseInt(modifyPanel.getTxtStockMin().getText()));
								product.setPresentation((String) modifyPanel.getComboBoxPresentation().getSelectedItem());
								product.setDescription(modifyPanel.getTxtDescription().getText());
								product.setPrice(tmpProduct.getPrice());
								product.setOnInventory(tmpProduct.getOnInventory());

								Main.getProductsDataService().update(product);

								modifyPanel.dispose();
								clear();
								JOptionPane.showMessageDialog(null, "Done");
							}
						});

						modifyPanel.getCancelButton().addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								modifyPanel.dispose();
							}
						});

						modifyPanel.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "You haven't selected any product");
					}



				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnModify.setBounds(78, 78, 193, 65);
		panel_1_1.add(btnModify);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBackground(Color.LIGHT_GRAY);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int op = 0;
				if (tmpProduct != null) {
					op = JOptionPane.showConfirmDialog(null, "Are you sure to delete this product?", null, JOptionPane.YES_NO_OPTION);
					if (op == JOptionPane.YES_OPTION) {
						Main.getProductsDataService().delete(tmpProduct.getBarcode());
						JOptionPane.showMessageDialog(null, "Done");
						barcodes.remove(tmpProduct.getBarcode());
						source.remove(tmpProduct.getName());
						productList.remove(tmpProduct);
						model.removeAllElements();
						clear();
						
					}
				}else {
					JOptionPane.showMessageDialog(null,"You haven't selected any product" );
				}
			}
		});
		btnDelete.setBounds(78, 194, 193, 65);
		panel_1_1.add(btnDelete);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridwidth = 3;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 3;
		add(panel_1, gbc_panel_1);




	}

	public void fillTable(Product product) {
		clear();
		Object[] row = {"Barcode", product.getBarcode()};
		defaultTableModel.addRow(row);
		Object[] row2 = {"Name", product.getName()};
		defaultTableModel.addRow(row2);
		Object[] row3 = {"Brand", product.getBrand()};
		defaultTableModel.addRow(row3);
		Object[] row4 = {"Type", product.getType()};
		defaultTableModel.addRow(row4);
		Object[] row5 = {"Content", product.getContent()};
		defaultTableModel.addRow(row5);
		Object[] row6 = {"Unit", product.getMeasureUnit()};
		defaultTableModel.addRow(row6);
		Object[] row8 = {"Maximum stock", product.getStockMax()};
		defaultTableModel.addRow(row8);
		Object[] row9 = {"Minimum stock", product.getStockMin()};
		defaultTableModel.addRow(row9);
		Object[] row10 = {"Presentation", product.getPresentation()};
		defaultTableModel.addRow(row10);
		Object[] row11 = {"Description", product.getDescription()};
		defaultTableModel.addRow(row11);


	}

	public void clear () {
		int i = 0;
		while (defaultTableModel.getRowCount()!=0) {
			defaultTableModel.removeRow(0);
			i++;
		}
	}
}
