package view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.AudioReproductor;
import model.Product;
import model.RestockRow;
import model.TicketRow;

import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class PanelSell extends JPanel {
	private JTextField txtBarcode;
	private JTable table;
	private JPanel panel;
	private JTextField txtAmount;
	private DefaultTableModel defaultTableModel;
	private DefaultListModel<String> defaultListModel;
	private List<TicketRow> ticketRows;
	private List<String> barcodes;
	private double total;
	private JLabel lblTotal;
	private JLabel lblAvailableAmount;

	/**
	 * Create the panel.
	 */
	public PanelSell() {
		
		
		total = 0.0;
		ticketRows = new ArrayList<>();
		barcodes = new ArrayList<>();
		defaultListModel = new DefaultListModel<>();
		defaultTableModel = new DefaultTableModel();
		defaultTableModel.addColumn("BARCODE");
		defaultTableModel.addColumn("DESCRIPTION");
		defaultTableModel.addColumn("AMOUNT");
		defaultTableModel.addColumn("PRICE");
		defaultTableModel.addColumn("TOTAL");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{103, 121, 0, 154, 154, 590, 0};
		gridBagLayout.rowHeights = new int[]{27, 52, 122, 212, 40, 41, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		for (Product product : Main.getProductsDataService().getAllProducts()) {
			barcodes.add(product.getBarcode());
		}
		
		txtBarcode = new JTextField();
		txtBarcode.setHorizontalAlignment(SwingConstants.CENTER);
		txtBarcode.setBackground(Color.LIGHT_GRAY);
		txtBarcode.setFont(new Font("Roboto", Font.PLAIN, 17));
		txtBarcode.addKeyListener(new KeyAdapter() {
			@Override
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
			@Override
			public void keyTyped(KeyEvent e) {
				char validacion = e.getKeyChar();
				if (validacion == e.VK_ENTER) {
					if (Main.getProductsDataService().exist(txtBarcode.getText())) {
						Product product = (Product) Main.getProductsDataService().get(txtBarcode.getText());
						lblAvailableAmount.setText(String.valueOf(product.getOnInventory()-product.getStockMin()));
						
						setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "This product doesn't exist");
					}
				}
				if (!Character.isDigit(validacion)) {
					e.consume();
				}
				if (txtBarcode.getText().trim().length() == 15) {
					e.consume();
					
				}
			}
		});
		GridBagConstraints gbc_txtBarcode = new GridBagConstraints();
		gbc_txtBarcode.insets = new Insets(0, 0, 5, 5);
		gbc_txtBarcode.fill = GridBagConstraints.BOTH;
		gbc_txtBarcode.gridx = 1;
		gbc_txtBarcode.gridy = 1;
		add(txtBarcode, gbc_txtBarcode);
		txtBarcode.setColumns(10);
		
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 3;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		
		txtAmount = new JTextField();
		txtAmount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char validacion = e.getKeyChar();
				
				if (!Character.isDigit(validacion)) {
					e.consume();
				}
			}
		});
		txtAmount.setFont(new Font("Roboto", Font.PLAIN, 17));
		txtAmount.setBounds(89, 0, 150, 46);
		panel.add(txtAmount);
		txtAmount.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Amount");
		lblNewLabel.setFont(new Font("Roboto", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 90, 46);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("+");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				TicketRow ticketRow = getTicketRow();
				if (ticketRow != null) {
					String rutaAudio = "src/resources/img/mario-coin.mp3";
					AudioReproductor audioReproductor = new AudioReproductor(rutaAudio);
					Thread hilo = new Thread(audioReproductor);
					hilo.start();
					fillTable(ticketRow);
					ticketRows.add(ticketRow);
					lblTotal.setText(String.valueOf(total));
					txtAmount.setText("");
					txtBarcode.setText("");
				}
				
			
				
			}
			
		});
		btnNewButton.setFont(new Font("Roboto", Font.BOLD, 24));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(835, 1, 53, 45);
		panel.add(btnNewButton);
		
		JLabel lblAvailable = new JLabel("Available");
		lblAvailable.setHorizontalAlignment(SwingConstants.CENTER);
		lblAvailable.setFont(new Font("Roboto", Font.PLAIN, 15));
		lblAvailable.setBounds(262, 0, 90, 46);
		panel.add(lblAvailable);
		
		lblAvailableAmount = new JLabel("");
		lblAvailableAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAvailableAmount.setFont(new Font("Roboto", Font.PLAIN, 15));
		lblAvailableAmount.setBounds(353, 0, 90, 46);
		panel.add(lblAvailableAmount);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		JList list = new JList();
		list.setBackground(Color.LIGHT_GRAY);
		list.setModel(defaultListModel);
		list.setFont(new Font("Roboto", Font.PLAIN, 15));
		scrollPane.setViewportView(list);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.gridheight = 3;
		gbc_scrollPane_1.gridwidth = 3;
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 3;
		gbc_scrollPane_1.gridy = 2;
		add(scrollPane_1, gbc_scrollPane_1);
		
		table = new JTable();
		table.setFont(new Font("Roboto", Font.PLAIN, 17));
		table.setRowHeight(32);
		table.setModel(defaultTableModel);
		table.setFocusable(false);
		scrollPane_1.setViewportView(table);
		
		JButton btnListProducts = new JButton("Show All");
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
		gbc_btnListProducts.insets = new Insets(0, 0, 5, 5);
		gbc_btnListProducts.gridx = 1;
		gbc_btnListProducts.gridy = 4;
		add(btnListProducts, gbc_btnListProducts);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 5;
		gbc_panel_1.gridy = 5;
		add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		lblTotal = new JLabel("$ 0.00");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Roboto Thin", Font.PLAIN, 20));
		panel_1.add(lblTotal, BorderLayout.CENTER);

	}
	
	
	
	public TicketRow getTicketRow() {
		if (txtBarcode.getText().trim().isEmpty() || txtAmount.getText().trim().isEmpty() || !Main.getProductsDataService().exist(txtBarcode.getText())) {
			JOptionPane.showMessageDialog(null, "Error");
			return null;
		}
		
		TicketRow ticketRow = new TicketRow();
		ticketRow.setBarcode(txtBarcode.getText());
		ticketRow.setAmount(Integer.parseInt(txtAmount.getText()));
		Product product = (Product)Main.getProductsDataService().get(txtBarcode.getText());
		if (Integer.parseInt(txtAmount.getText())>product.getOnInventory()-product.getStockMin()) {
			JOptionPane.showMessageDialog(null, "Insufficient units");
			return null;
		}
		
		ticketRow.setProduct(product.getDescription());
		ticketRow.setPrice(product.getPrice());
		ticketRow.setTotal(Integer.parseInt(txtAmount.getText())*product.getPrice());
		total+=Integer.parseInt(txtAmount.getText())*product.getPrice();
		product.setOnInventory(product.getOnInventory()-Integer.parseInt(txtAmount.getText()));
		Main.getProductsDataService().update(product);
		
		
		return ticketRow;
	}
	
	
	
	public List<TicketRow> getTicketRows() {
		return ticketRows;
	}



	public void setTicketRows(List<TicketRow> ticketRows) {
		this.ticketRows = ticketRows;
	}

	
	


	public double getTotal() {
		return total;
	}



	public void setTotal(double total) {
		this.total = total;
	}



	public void fillTable(TicketRow ticketRow) {
		Object[] row = new Object[5];
		row[0] = ticketRow.getBarcode();
		row[1] = ticketRow.getProduct();
		row[2] = ticketRow.getAmount();
		row[3] = ticketRow.getPrice();
		row[4] = ticketRow.getTotal();
		defaultTableModel.addRow(row);
	}
	
	public void clear () {
		int i = 0;
		while (defaultTableModel.getRowCount()!=0) {
			defaultTableModel.removeRow(0);
			i++;
		}
	}
}
