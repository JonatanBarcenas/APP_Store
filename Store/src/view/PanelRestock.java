package view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import model.Product;
import model.Provider;
import model.Restock;
import model.RestockRow;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Cursor;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class PanelRestock extends JPanel {

	private JTextField txtBarcode;
	private JTextField txtPrice;
	private JTable table;
	private JLabel lblNewLabel;
	private JLabel lblAmount;
	private JLabel lblPrice;
	private JLabel lblProvider;
	private List<RestockRow> restockRows;
	private DefaultTableModel defaultTableModel;
	private JDateChooser sellByDate;
	private JLabel lblSellByDate;
	private JSpinner txtAmount;
	private JButton btnAddRestockRow;
	private Double totalRow = 0.0;
	private JPanel btnAddProvider;
	private JPanel panel_1;
	private JLabel lblTotal;
	private JLabel lblAddProvider;
	private ProviderPanel providerPanel;
	private JComboBox txtProvider;
	private String providerName = "";
	private JScrollPane scrollPane_1;
	private JList jlistBarcodes;
	private JButton btnListProducts;
	private DialogProductList dialogProductList;
	private Collection<String> names;
	private DefaultComboBoxModel<String> modelo;
	private DefaultListModel<String> defaultListModel;
	private List<String> barcodes;
	

	/**
	 * Create the panel.
	 */

	public PanelRestock() {
		restockRows = new ArrayList<>();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{40, 214, 106, 212, 106, 212, 106, 212, 0, 125, 0};
		gridBagLayout.rowHeights = new int[]{33, 33, 33, 337, 33, 49, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		barcodes = new ArrayList<>();

		for (Product product : Main.getProductsDataService().getAllProducts()) {
			barcodes.add(product.getBarcode());
		}
		defaultListModel = new DefaultListModel<>();
		modelo = new DefaultComboBoxModel<>();
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Roboto", Font.PLAIN, 13));
		txtPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validacion = e.getKeyChar();
				if (!Character.isDigit(validacion)) {
					e.consume();
				}
				if (e.getKeyChar()== e.VK_ENTER) {
					sellByDate.requestFocus();
					e.consume();
					return;

				}
			}
		});

		btnAddProvider = new JPanel();
		btnAddProvider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddProvider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					ProviderPanel dialog = new ProviderPanel();
					dialog.getOkButton().addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							String provider = dialog.getTxtName().getText();
							names.add(provider);
							modelo.removeAllElements();
							modelo.addAll(names);
						}
					});
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAddProvider.setBackground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAddProvider.setBackground(Color.LIGHT_GRAY);
			}
		});
		btnAddProvider.setBackground(Color.LIGHT_GRAY);
		btnAddProvider.setLayout(null);
		GridBagConstraints gbc_btnAddProvider = new GridBagConstraints();
		gbc_btnAddProvider.gridheight = 2;
		gbc_btnAddProvider.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddProvider.fill = GridBagConstraints.BOTH;
		gbc_btnAddProvider.gridx = 1;
		gbc_btnAddProvider.gridy = 0;
		add(btnAddProvider, gbc_btnAddProvider);

		lblAddProvider = new JLabel("Add \r\nProvider\r\n");
		lblAddProvider.setBounds(62, 11, 91, 39);
		lblAddProvider.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddProvider.setFont(new Font("Roboto", Font.PLAIN, 15));
		btnAddProvider.add(lblAddProvider);

		lblNewLabel = new JLabel("Barcode: ");
		lblNewLabel.setFont(new Font("Roboto", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);

		txtBarcode = new JTextField();
		txtBarcode.setFont(new Font("Roboto", Font.PLAIN, 13));
		txtBarcode.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validacion = e.getKeyChar();
				if (!Character.isDigit(validacion)) {
					e.consume();
				}
				if (txtBarcode.getText().trim().length() == 15) {
					e.consume();

				}

				if (validacion == e.VK_ENTER) {

					if(Main.getProductsDataService().exist(txtBarcode.getText())) {
						SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
							@Override
							protected Void doInBackground() throws Exception {
								txtBarcode.setBackground(Color.GREEN);
								Thread.sleep(500);
								return null;
							}

							@Override
							protected void done() {
								txtBarcode.setBackground(Color.WHITE);
								sellByDate.requestFocus();
							}
						};
						worker.execute();

					}else{

						txtBarcode.setBackground(Color.RED);
						JOptionPane.showMessageDialog(null, "This product doesn't exists in the catalog");
						txtBarcode.setBackground(Color.WHITE);

					}	



				}}			@Override
			public void keyReleased(KeyEvent e) {
					String filter = txtBarcode.getText();

					defaultListModel.removeAllElements();
					if (filter.isEmpty()) {
						defaultListModel.addAll(barcodes);
					}else {
						var filterElemets = barcodes.stream().filter(s -> s.toString().contains(filter)).toList();
						defaultListModel.addAll(filterElemets);
					}
			}
});
		GridBagConstraints gbc_txtBarcode = new GridBagConstraints();
		gbc_txtBarcode.fill = GridBagConstraints.BOTH;
		gbc_txtBarcode.insets = new Insets(0, 0, 5, 5);
		gbc_txtBarcode.gridx = 3;
		gbc_txtBarcode.gridy = 0;
		add(txtBarcode, gbc_txtBarcode);
		txtBarcode.setColumns(10);

		txtAmount = new JSpinner();
		txtAmount.setFont(new Font("Roboto", Font.PLAIN, 13));
		txtAmount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
				char validacion = e.getKeyChar();
				if (!Character.isDigit(validacion)) {
					e.consume();
				}
				if (e.getKeyChar()== e.VK_ENTER) {
					txtPrice.requestFocus();
					e.consume();
					return;

				}
			}
		});

		lblAmount = new JLabel("Amount: ");
		lblAmount.setFont(new Font("Roboto", Font.PLAIN, 15));
		lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblAmount = new GridBagConstraints();
		gbc_lblAmount.anchor = GridBagConstraints.EAST;
		gbc_lblAmount.fill = GridBagConstraints.VERTICAL;
		gbc_lblAmount.insets = new Insets(0, 0, 5, 5);
		gbc_lblAmount.gridx = 4;
		gbc_lblAmount.gridy = 0;
		add(lblAmount, gbc_lblAmount);
		txtAmount.setModel(new SpinnerNumberModel(Integer.valueOf(0), null, null, Integer.valueOf(1)));
		GridBagConstraints gbc_txtAmount = new GridBagConstraints();
		gbc_txtAmount.fill = GridBagConstraints.BOTH;
		gbc_txtAmount.insets = new Insets(0, 0, 5, 5);
		gbc_txtAmount.gridx = 5;
		gbc_txtAmount.gridy = 0;
		add(txtAmount, gbc_txtAmount);

		lblPrice = new JLabel("Price: ");
		lblPrice.setFont(new Font("Roboto", Font.PLAIN, 15));
		lblPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblPrice = new GridBagConstraints();
		gbc_lblPrice.anchor = GridBagConstraints.EAST;
		gbc_lblPrice.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrice.gridx = 6;
		gbc_lblPrice.gridy = 0;
		add(lblPrice, gbc_lblPrice);
		GridBagConstraints gbc_txtPrice = new GridBagConstraints();
		gbc_txtPrice.fill = GridBagConstraints.BOTH;
		gbc_txtPrice.insets = new Insets(0, 0, 5, 5);
		gbc_txtPrice.gridx = 7;
		gbc_txtPrice.gridy = 0;
		add(txtPrice, gbc_txtPrice);
		txtPrice.setColumns(10);
		
		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridheight = 4;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 9;
		gbc_scrollPane_1.gridy = 0;
		add(scrollPane_1, gbc_scrollPane_1);
		
		jlistBarcodes = new JList(defaultListModel);
		jlistBarcodes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String barcode = (String) jlistBarcodes.getSelectedValue();
				txtBarcode.setText(barcode);
			}
		});
		jlistBarcodes.setSelectionBackground(Color.LIGHT_GRAY);
		jlistBarcodes.setBackground(getBackground());
		scrollPane_1.setViewportView(jlistBarcodes);
		scrollPane_1.getViewport().setBackground(getBackground());

		lblSellByDate = new JLabel("Sell by Date: ");
		lblSellByDate.setFont(new Font("Roboto", Font.PLAIN, 15));
		lblSellByDate.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblSellByDate = new GridBagConstraints();
		gbc_lblSellByDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSellByDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblSellByDate.gridx = 2;
		gbc_lblSellByDate.gridy = 1;
		add(lblSellByDate, gbc_lblSellByDate);

		sellByDate = new JDateChooser();
		sellByDate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar()== e.VK_ENTER) {
					txtProvider.requestFocus();
					e.consume();
					return;

				}
			}
		});
		sellByDate.setDateFormatString("YYYY-MM-dd");
		GridBagConstraints gbc_sellByDate = new GridBagConstraints();
		gbc_sellByDate.fill = GridBagConstraints.BOTH;
		gbc_sellByDate.insets = new Insets(0, 0, 5, 5);
		gbc_sellByDate.gridx = 3;
		gbc_sellByDate.gridy = 1;
		add(sellByDate, gbc_sellByDate);

		lblProvider = new JLabel("Provider: ");
		lblProvider.setFont(new Font("Roboto", Font.PLAIN, 15));
		lblProvider.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblProvider = new GridBagConstraints();
		gbc_lblProvider.anchor = GridBagConstraints.EAST;
		gbc_lblProvider.insets = new Insets(0, 0, 5, 5);
		gbc_lblProvider.gridx = 4;
		gbc_lblProvider.gridy = 1;
		add(lblProvider, gbc_lblProvider);


		names = new ArrayList<String>();
		for (Provider provider : Main.getProvidersDataService().getAllProviders()) {
			names.add(provider.getName());
		}
		
		modelo.addAll(names);
	
		txtProvider = new JComboBox(modelo);
		txtProvider.setFont(new Font("Roboto", Font.PLAIN, 13));
		GridBagConstraints gbc_txtProvider = new GridBagConstraints();
		gbc_txtProvider.insets = new Insets(0, 0, 5, 5);
		gbc_txtProvider.fill = GridBagConstraints.BOTH;
		gbc_txtProvider.gridx = 5;
		gbc_txtProvider.gridy = 1;
		add(txtProvider, gbc_txtProvider);

		btnAddRestockRow = new JButton("Add");
		btnAddRestockRow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RestockRow restockRow = getRow();

				if (restockRow != null) {
					fillTable(restockRow);
					restockRows.add(restockRow);
					lblTotal.setText("$ "+String.valueOf(totalRow));
					System.out.println("$ "+String.valueOf(totalRow));
					providerName = (String) txtProvider.getSelectedItem();
					removeAll();
				}else {
					JOptionPane.showMessageDialog(null, "ERROR");
				}

			}
		});
		btnAddRestockRow.setBackground(Color.LIGHT_GRAY);
		btnAddRestockRow.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		GridBagConstraints gbc_btnAddRestockRow = new GridBagConstraints();
		gbc_btnAddRestockRow.fill = GridBagConstraints.BOTH;
		gbc_btnAddRestockRow.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddRestockRow.gridx = 7;
		gbc_btnAddRestockRow.gridy = 1;
		add(btnAddRestockRow, gbc_btnAddRestockRow);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 7;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);
		table = new JTable();
		table.setRowHeight(32);
		table.setGridColor(Color.LIGHT_GRAY);
		defaultTableModel = new DefaultTableModel();

		table.setModel(defaultTableModel);
		table.setFont(new Font("Roboto Thin", Font.PLAIN, 20));
		scrollPane.setViewportView(table);
		
		btnListProducts = new JButton("Show All");
		btnListProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DialogProductList dialog = new DialogProductList();
					dialog.getListPane().getTable().addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							int row = dialog.getListPane().getTable().getSelectedRow();
							String value = (String) dialog.getListPane().getTable().getModel().getValueAt(row, 0);
							txtBarcode.setText(value);
							dialog.dispose();
						};
					});
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception evt) {
					evt.printStackTrace();
				}
			}
		});
		btnListProducts.setFont(new Font("Roboto Medium", Font.PLAIN, 13));
		btnListProducts.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btnListProducts = new GridBagConstraints();
		gbc_btnListProducts.fill = GridBagConstraints.BOTH;
		gbc_btnListProducts.insets = new Insets(0, 0, 5, 0);
		gbc_btnListProducts.gridx = 9;
		gbc_btnListProducts.gridy = 4;
		add(btnListProducts, gbc_btnListProducts);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.gridwidth = 2;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 6;
		gbc_panel_1.gridy = 5;
		add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		lblTotal = new JLabel();
		
		lblTotal.setFont(new Font("Roboto Thin", Font.PLAIN, 20));
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblTotal, BorderLayout.CENTER);


		


		defaultTableModel.addColumn("BARCODE");
		defaultTableModel.addColumn("NAME");
		defaultTableModel.addColumn("AMOUNT");
		defaultTableModel.addColumn("TOTAL");
		defaultTableModel.addColumn("SELL BY DATE");

	}



	public List<RestockRow> getRestockRows() {
		return restockRows;
	}



	public void setRestockRows(List<RestockRow> restockRows) {
		this.restockRows = restockRows;
	}



	public JTextField getTxtBarcode() {
		return txtBarcode;
	}



	public JButton getBtnAddRestockRow() {
		return btnAddRestockRow;
	}


	public JLabel getLblTotal() {
		return lblTotal;
	}



	public void setLblTotal(JLabel lblTotal) {
		this.lblTotal = lblTotal;
	}



	public JSpinner getTxtAmount() {
		return txtAmount;
	}



	public void setTxtAmount(JSpinner txtAmount) {
		this.txtAmount = txtAmount;
	}


	public JTextField getTxtPrice() {
		return txtPrice;
	}



	public void setTxtPrice(JTextField txtPrice) {
		this.txtPrice = txtPrice;
	}




	public String getProviderName() {
		return providerName;
	}



	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}



	public JDateChooser getSellByDate() {
		return sellByDate;
	}



	public void setSellByDate(JDateChooser sellByDate) {
		this.sellByDate = sellByDate;
	}



	public JComboBox getTxtProvider() {
		return txtProvider;
	}



	public void setTxtProvider(JComboBox txtProvider) {
		this.txtProvider = txtProvider;
	}



	public void setTxtBarcode(JTextField txtBarcode) {
		this.txtBarcode = txtBarcode;
	}



	public double getTotalRow() {
		return totalRow;
	}

	


	public void fillTable(RestockRow restockRow) {
		Object[] row = new Object[5];
		row[0] = restockRow.getBarcode();
		row[1] = restockRow.getName();
		row[2] = restockRow.getAmount();
		row[3] = restockRow.getPrice();
		row[4] = restockRow.getSellByDate();
		defaultTableModel.addRow(row);
	}

	public RestockRow getRow() {
		if (txtBarcode.getText().isEmpty() || txtAmount.getValue()==null || txtPrice.getText().isEmpty()  || sellByDate.getDateFormatString().isEmpty()) {
			return null;
		}
		if (Main.getProductsDataService().exist(txtBarcode.getText())) {
			RestockRow restockRow = new RestockRow();
			Product product = (Product)Main.getProductsDataService().get(txtBarcode.getText());
			if ((product.getOnInventory()+(int)txtAmount.getValue())>product.getStockMax()) {

				JOptionPane.showMessageDialog(null, "The amount of the products exceeds the maximum stock \n"
						+ "Maximum stock: "+product.getStockMax()+"\n"
						+ "Space on inventory: "+String.valueOf(product.getStockMax()-product.getOnInventory()));
				return null;
			}
			//Main.getProductsDataService().get(txtBarcode.getText())
			product.setOnInventory((Integer) txtAmount.getValue());
			System.out.println(product.toString());
			restockRow.setBarcode(txtBarcode.getText());
			restockRow.setName(product.getName());
			restockRow.setAmount((int)txtAmount.getValue());

			java.util.Date date = sellByDate.getDate();
			SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
			restockRow.setSellByDate(dateFormat.format(date));
			restockRow.setPrice(Double.parseDouble(txtPrice.getText()));
			restockRow.setTotal((int)txtAmount.getValue()*Double.parseDouble(txtPrice.getText()));
			totalRow += (int)txtAmount.getValue()*Double.parseDouble(txtPrice.getText());
			product.setPrice((Double.parseDouble(txtPrice.getText()))+Double.parseDouble(txtPrice.getText())*0.16);
			Main.getProductsDataService().update(product);
			return restockRow;
		}else {
			return null;
		}

	}

	public void clear () {

		int i = 0;
		while (defaultTableModel.getRowCount()!=0) {
			defaultTableModel.removeRow(0);
			i++;
		}
		restockRows.clear();

	}

	public void removeAll() {
		txtBarcode.setText("");
		txtPrice.setText("");
		txtAmount.setValue((int)0);
		txtProvider.setSelectedIndex(-1);
		sellByDate.setDate(null);
		

	}



}
