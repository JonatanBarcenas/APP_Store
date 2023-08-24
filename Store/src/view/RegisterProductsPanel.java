package view;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import model.Product;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class RegisterProductsPanel extends JPanel {
	private JTextField txtBarcode;
	private JTextField txtName;
	private JTextField txtBrand;
	private JTextField txtContent;
	private JTextField txtStockMax;
	private JTextField txtStockMin;
	private JTextField txtDescription;
	private JComboBox comboBoxType;
	private JComboBox comboBoxUnit;
	private JComboBox comboBoxPresentation;
	private JLabel lblNewLabel;
	private List<Long> source;
	private List<String> names;
	private JLabel lblName;
	private JLabel lblBrand;
	private JLabel lblType;
	private JLabel lblContent;
	private JLabel lblUnit;
	private JLabel lblMaximumStock;
	private JLabel lblMinimumStock;
	private JLabel lblPresentation;
	private JLabel lblDescription;
	private JComboBox comboBoxBy;
	private JScrollPane scrollPane;
	private JList list;
	private DefaultListModel<String> model;
	private List<Product> products;
	private Object by = "";

	/**
	 * Create the panel.
	 */
	public RegisterProductsPanel(List<Product> products) {
		this.products = products;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 310, 556, -18, 300, 0};
		gridBagLayout.rowHeights = new int[]{54, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		lblNewLabel = new JLabel("Barcode: ");
		lblNewLabel.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);
		
		model = new DefaultListModel<>();

		source = new ArrayList<Long>();
		names = new ArrayList<>();

		for (Product product : products) {
			source.add(Long.parseLong(product.getBarcode()));
			names.add(product.getName());
		}
		
		Collections.sort(source);
		Collections.sort(names);
		
		var barcodes = new ArrayList<String>();
		
		for (Long barcode : source) {
			barcodes.add(String.valueOf(barcode));
		}
		
	

		txtBarcode = new JTextField();
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
					if(exist(txtBarcode.getText())) {
						txtBarcode.setBackground(Color.RED);
						JOptionPane.showMessageDialog(null, "It already exists in the catalog");
						txtBarcode.setBackground(Color.WHITE);
					}else if (txtBarcode.getText().trim().length() < 15 || txtBarcode.getText().startsWith("0")) {
						txtBarcode.setBackground(Color.RED);
						JOptionPane.showMessageDialog(null, "The barcode does not have 15 digits");
						txtBarcode.setBackground(Color.WHITE);
					}else {
						
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
					                txtName.requestFocus();
					            }
					        };
					        worker.execute();
					    }
						
					}
					
				
			}
			@Override
			public void keyReleased(KeyEvent e) {
				by = comboBoxBy.getSelectedItem();
				
				if (by == "By Barcode") {
					String filter = txtBarcode.getText();

					model.removeAllElements();
					if (filter.isEmpty()) {
						model.addAll(barcodes);
					}else {
						var filterElemets = barcodes.stream().filter(s -> s.toString().contains(filter)).toList();
						model.addAll(filterElemets);
					}
				}
				
			}
		});
		txtBarcode.setFont(new Font("Roboto", Font.BOLD, 12));
		txtBarcode.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_txtBarcode = new GridBagConstraints();
		gbc_txtBarcode.fill = GridBagConstraints.BOTH;
		gbc_txtBarcode.insets = new Insets(0, 0, 5, 5);
		gbc_txtBarcode.gridx = 2;
		gbc_txtBarcode.gridy = 1;
		add(txtBarcode, gbc_txtBarcode);
		txtBarcode.setColumns(10);

		comboBoxBy = new JComboBox();
		comboBoxBy.setFont(new Font("Roboto", Font.PLAIN, 12));
		comboBoxBy.setModel(new DefaultComboBoxModel(new String[] {"By Barcode", "By Name"}));
		GridBagConstraints gbc_comboBoxBy = new GridBagConstraints();
		gbc_comboBoxBy.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxBy.fill = GridBagConstraints.BOTH;
		gbc_comboBoxBy.gridx = 4;
		gbc_comboBoxBy.gridy = 1;
		add(comboBoxBy, gbc_comboBoxBy);

		lblName = new JLabel("Name: ");
		lblName.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 2;
		add(lblName, gbc_lblName);

		txtName = new JTextField();
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				by = comboBoxBy.getSelectedItem();
				if(by == "By Name" ) {
					String filter = txtName.getText();
					model.removeAllElements();
					if (filter.isEmpty()) {
						model.addAll(names);
					}else {
						var filterElemets = names.stream().filter(s -> s.toString().contains(filter)).toList();
						model.addAll(filterElemets);
					}
				}
				

			}
			@Override
			public void keyTyped(KeyEvent e) {
				char validacion = e.getKeyChar();
				if (validacion == e.VK_ENTER) {
					txtBrand.requestFocus();
				}
			}
		});

		txtName.setFont(new Font("Roboto", Font.BOLD, 12));
		txtName.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.fill = GridBagConstraints.BOTH;
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.gridx = 2;
		gbc_txtName.gridy = 2;
		add(txtName, gbc_txtName);
		txtName.setColumns(10);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 10;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 4;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);

		list = new JList(model);
		list.setFont(new Font("Roboto", Font.PLAIN, 12));
		
		scrollPane.setViewportView(list);

		lblBrand = new JLabel("Brand: ");
		lblBrand.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		GridBagConstraints gbc_lblBrand = new GridBagConstraints();
		gbc_lblBrand.insets = new Insets(0, 0, 5, 5);
		gbc_lblBrand.anchor = GridBagConstraints.EAST;
		gbc_lblBrand.gridx = 1;
		gbc_lblBrand.gridy = 3;
		add(lblBrand, gbc_lblBrand);

		txtBrand = new JTextField();
		txtBrand.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			@Override
			public void keyTyped(KeyEvent e) {
				char validacion = e.getKeyChar();
				if (validacion == e.VK_ENTER) {
					comboBoxType.requestFocus();
				}
			}
		});
		txtBrand.setFont(new Font("Roboto", Font.BOLD, 12));
		txtBrand.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_txtBrand = new GridBagConstraints();
		gbc_txtBrand.fill = GridBagConstraints.BOTH;
		gbc_txtBrand.insets = new Insets(0, 0, 5, 5);
		gbc_txtBrand.gridx = 2;
		gbc_txtBrand.gridy = 3;
		add(txtBrand, gbc_txtBrand);
		txtBrand.setColumns(10);

		lblType = new JLabel("Type: ");
		lblType.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.insets = new Insets(0, 0, 5, 5);
		gbc_lblType.anchor = GridBagConstraints.EAST;
		gbc_lblType.gridx = 1;
		gbc_lblType.gridy = 4;
		add(lblType, gbc_lblType);

		comboBoxType = new JComboBox();
		comboBoxType.setModel(new DefaultComboBoxModel(new String[] {"", "Fresh produce", "Meat and poultry", "Dairy products", "Bakery items", "Canned goods", "Frozen foods", "Beverages", "Snacks", "Pasta, rice, and grains", "Condiments", "Baking supplies", "Cleaning and household products", "Personal care products", "Pet food and supplies", "Baby care products", "Health and wellness products", "International foods", "Deli items", "Fresh seafood", "Specialty or gourmet products"}));
		comboBoxType.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				char validacion = e.getKeyChar();
				if (validacion == e.VK_ENTER) {
					txtContent.requestFocus();
				}
			}
		});
		comboBoxType.setFont(new Font("Roboto", Font.BOLD, 12));
		comboBoxType.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_comboBoxType = new GridBagConstraints();
		gbc_comboBoxType.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxType.fill = GridBagConstraints.BOTH;
		gbc_comboBoxType.gridx = 2;
		gbc_comboBoxType.gridy = 4;
		add(comboBoxType, gbc_comboBoxType);

		lblContent = new JLabel("Content: ");
		lblContent.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		GridBagConstraints gbc_lblContent = new GridBagConstraints();
		gbc_lblContent.insets = new Insets(0, 0, 5, 5);
		gbc_lblContent.anchor = GridBagConstraints.EAST;
		gbc_lblContent.gridx = 1;
		gbc_lblContent.gridy = 5;
		add(lblContent, gbc_lblContent);

		txtContent = new JTextField();
		txtContent.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				char validacion = e.getKeyChar();
				if (validacion == e.VK_ENTER) {
					comboBoxUnit.requestFocus();
				}
			}
		});
		txtContent.setFont(new Font("Roboto", Font.BOLD, 12));
		txtContent.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_txtContent = new GridBagConstraints();
		gbc_txtContent.insets = new Insets(0, 0, 5, 5);
		gbc_txtContent.fill = GridBagConstraints.BOTH;
		gbc_txtContent.gridx = 2;
		gbc_txtContent.gridy = 5;
		add(txtContent, gbc_txtContent);
		txtContent.setColumns(10);

		lblUnit = new JLabel("Measure unit: ");
		lblUnit.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
		GridBagConstraints gbc_lblUnit = new GridBagConstraints();
		gbc_lblUnit.insets = new Insets(0, 0, 5, 5);
		gbc_lblUnit.anchor = GridBagConstraints.EAST;
		gbc_lblUnit.gridx = 1;
		gbc_lblUnit.gridy = 6;
		add(lblUnit, gbc_lblUnit);

		comboBoxUnit = new JComboBox();
		comboBoxUnit.setModel(new DefaultComboBoxModel(new String[] {"", "grams", "mililiters", "liters", "milligrams", "pieces", "ounces", "centimeters", "millimeters", "kilograms"}));
		comboBoxUnit.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				char validacion = e.getKeyChar();
				if (validacion == e.VK_ENTER) {
					txtStockMax.requestFocus();
				}
			}
		});
		comboBoxUnit.setFont(new Font("Roboto", Font.BOLD, 12));
		comboBoxUnit.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_comboBoxUnit = new GridBagConstraints();
		gbc_comboBoxUnit.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxUnit.fill = GridBagConstraints.BOTH;
		gbc_comboBoxUnit.gridx = 2;
		gbc_comboBoxUnit.gridy = 6;
		add(comboBoxUnit, gbc_comboBoxUnit);
		
				txtStockMax = new JTextField();
				txtStockMax.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						char validacion = e.getKeyChar();
						if (validacion == e.VK_ENTER) {
							txtStockMin.requestFocus();
						}
					}
				});
				
						lblMaximumStock = new JLabel("Maximum stock: ");
						lblMaximumStock.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
						GridBagConstraints gbc_lblMaximumStock = new GridBagConstraints();
						gbc_lblMaximumStock.insets = new Insets(0, 0, 5, 5);
						gbc_lblMaximumStock.anchor = GridBagConstraints.EAST;
						gbc_lblMaximumStock.gridx = 1;
						gbc_lblMaximumStock.gridy = 7;
						add(lblMaximumStock, gbc_lblMaximumStock);
				txtStockMax.setFont(new Font("Roboto", Font.BOLD, 12));
				txtStockMax.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				GridBagConstraints gbc_txtStockMax = new GridBagConstraints();
				gbc_txtStockMax.insets = new Insets(0, 0, 5, 5);
				gbc_txtStockMax.fill = GridBagConstraints.BOTH;
				gbc_txtStockMax.gridx = 2;
				gbc_txtStockMax.gridy = 7;
				add(txtStockMax, gbc_txtStockMax);
				txtStockMax.setColumns(10);
		
				txtStockMin = new JTextField();
				txtStockMin.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						char validacion = e.getKeyChar();
						if (validacion == e.VK_ENTER) {
							comboBoxPresentation.requestFocus();
						}
					}
				});
				
						lblMinimumStock = new JLabel("Minimum stock: ");
						lblMinimumStock.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
						GridBagConstraints gbc_lblMinimumStock = new GridBagConstraints();
						gbc_lblMinimumStock.insets = new Insets(0, 0, 5, 5);
						gbc_lblMinimumStock.anchor = GridBagConstraints.EAST;
						gbc_lblMinimumStock.gridx = 1;
						gbc_lblMinimumStock.gridy = 8;
						add(lblMinimumStock, gbc_lblMinimumStock);
				txtStockMin.setFont(new Font("Roboto", Font.BOLD, 12));
				txtStockMin.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				GridBagConstraints gbc_txtStockMin = new GridBagConstraints();
				gbc_txtStockMin.insets = new Insets(0, 0, 5, 5);
				gbc_txtStockMin.fill = GridBagConstraints.BOTH;
				gbc_txtStockMin.gridx = 2;
				gbc_txtStockMin.gridy = 8;
				add(txtStockMin, gbc_txtStockMin);
				txtStockMin.setColumns(10);
		
				comboBoxPresentation = new JComboBox();
				comboBoxPresentation.setModel(new DefaultComboBoxModel(new String[] {"", "box", "can", "bottle", "plastic bottle", "package", "bag", "bar", "Wrapper", "cylinder", "roll"}));
				comboBoxPresentation.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						char validacion = e.getKeyChar();
						if (validacion == e.VK_ENTER) {
							txtDescription.requestFocus();
						}
					}
				});
				
						lblPresentation = new JLabel("Presentation: ");
						lblPresentation.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
						GridBagConstraints gbc_lblPresentation = new GridBagConstraints();
						gbc_lblPresentation.insets = new Insets(0, 0, 5, 5);
						gbc_lblPresentation.anchor = GridBagConstraints.EAST;
						gbc_lblPresentation.gridx = 1;
						gbc_lblPresentation.gridy = 9;
						add(lblPresentation, gbc_lblPresentation);
				comboBoxPresentation.setFont(new Font("Roboto", Font.BOLD, 12));
				comboBoxPresentation.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				GridBagConstraints gbc_comboBoxPresentation = new GridBagConstraints();
				gbc_comboBoxPresentation.insets = new Insets(0, 0, 5, 5);
				gbc_comboBoxPresentation.fill = GridBagConstraints.BOTH;
				gbc_comboBoxPresentation.gridx = 2;
				gbc_comboBoxPresentation.gridy = 9;
				add(comboBoxPresentation, gbc_comboBoxPresentation);
				
						lblDescription = new JLabel("Description: ");
						lblDescription.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
						GridBagConstraints gbc_lblDescription = new GridBagConstraints();
						gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
						gbc_lblDescription.anchor = GridBagConstraints.EAST;
						gbc_lblDescription.gridx = 1;
						gbc_lblDescription.gridy = 10;
						add(lblDescription, gbc_lblDescription);
		
				txtDescription = new JTextField();
				txtDescription.setFont(new Font("Roboto", Font.BOLD, 12));
				txtDescription.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				GridBagConstraints gbc_txtDescription = new GridBagConstraints();
				gbc_txtDescription.gridheight = 2;
				gbc_txtDescription.insets = new Insets(0, 0, 5, 5);
				gbc_txtDescription.fill = GridBagConstraints.BOTH;
				gbc_txtDescription.gridx = 2;
				gbc_txtDescription.gridy = 10;
				add(txtDescription, gbc_txtDescription);
				txtDescription.setColumns(10);

	}

	public Product getProduct() {
		if (txtBarcode.getText().isEmpty() || txtName.getText().isEmpty() || txtBrand.getText().isEmpty() || txtContent.getText().isEmpty() ||txtStockMax.getText().isEmpty() || txtStockMin.getText().isEmpty() || txtDescription.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "All fields must be filled");
			return null;
		}
		if(exist(txtBarcode.getText())) {
			txtBarcode.setBackground(Color.RED);
			JOptionPane.showMessageDialog(null, "It already exists in the catalog");
			txtBarcode.setBackground(Color.WHITE);
			txtBarcode.requestFocus();
			return null;
		}else if (txtBarcode.getText().trim().length() < 15 ||  txtBarcode.getText().startsWith("0")) {
			txtBarcode.setBackground(Color.RED);
			JOptionPane.showMessageDialog(null, "The barcode does not have 15 digits");
			txtBarcode.setBackground(Color.WHITE);
			txtBarcode.requestFocus();
			return null;
		}else {
			
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
		           
		            }
		        };
		        worker.execute();
		    }
		Product product = new Product();
		product.setBarcode(txtBarcode.getText());
		product.setName(txtName.getText());
		product.setBrand(txtBrand.getText());
		product.setType((String) comboBoxType.getSelectedItem());
		product.setContent(txtContent.getText());
		product.setMeasureUnit((String)comboBoxUnit.getSelectedItem());
		product.setStockMax(Integer.parseInt(txtStockMax.getText()));
		product.setStockMin(Integer.parseInt(txtStockMin.getText()));
		product.setPresentation((String) comboBoxPresentation.getSelectedItem());
		product.setDescription(txtDescription.getText());
		return product;
	}

	public boolean exist(String barcode) {
		for (Product product : products) {
			if (product.getBarcode().equals(barcode)) {
				return true;
			}
		}
		return false;
	}

	public void clear() {
		txtBarcode.setText(""); 
		txtName.setText("");
		txtBrand.setText("");
		txtContent.setText("");
		txtStockMax.setText("");
		txtStockMin.setText("");
		txtDescription.setText("");
	}
}
