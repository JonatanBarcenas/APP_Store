package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Date;
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
import model.Restock;
import model.RestockRow;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDateChooser;

public class ConsultRestockPanel extends JPanel {
	
	private JTextField txtFolio;
	private DefaultTableModel restockRowsModel;
	private DefaultTableModel restocksModel;
	private JPanel panel;
	private JPanel panel_1;
	private String by = "";
	private JTable tableRestockRows;
	private JScrollPane scrollPane_1;
	private JPanel panel_2;
	private JDateChooser dateChooserFrom;
	private JDateChooser dateChooserTo;
	private List<Restock> restocks;
	private JTable tableRestocks;
	private JButton btnShowRestocks;



	/**
	 * Create the panel.
	 */
	public ConsultRestockPanel() {

		restocks = new ArrayList<>();
		restocksModel = new DefaultTableModel();
		restockRowsModel = new DefaultTableModel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{580, 134, 243, 400, 0};
		gridBagLayout.rowHeights = new int[]{58, 44, 64, 308, 58, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 4;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);

		txtFolio = new JTextField();
		txtFolio.setHorizontalAlignment(SwingConstants.CENTER);
		txtFolio.setFont(new Font("Roboto", Font.PLAIN, 15));
		txtFolio.setBorder(new LineBorder(Color.LIGHT_GRAY));
		txtFolio.setBorder(null);
		txtFolio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {



			}
			@Override
			public void keyTyped(KeyEvent e) {
				char validacion = e.getKeyChar();
				if (!Character.isDigit(validacion)) {
					e.consume();
				}
				if (txtFolio.getText().trim().length() == 15) {
					e.consume();

				}

				if (validacion == e.VK_ENTER) {


					if(Main.getRestocksDataService().exist(txtFolio.getText())) {
						SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
							@Override
							protected Void doInBackground() throws Exception {
								txtFolio.setBackground(Color.GREEN);
								Thread.sleep(500);
								return null;
							}

							@Override
							protected void done() {
								txtFolio.setBackground(Color.WHITE);
								var rows = Main.getRestocksDataService().getRows(Integer.parseInt(txtFolio.getText()));
								fillTable(rows);
							}
						};
						worker.execute();

					}else{

						txtFolio.setBackground(Color.RED);
						JOptionPane.showMessageDialog(null, "There is no refill with this folio");
						txtFolio.setBackground(Color.WHITE);

					}	



				}

			}
		});


		txtFolio.setColumns(10);
		GridBagConstraints gbc_txtFolio = new GridBagConstraints();
		gbc_txtFolio.insets = new Insets(0, 0, 5, 5);
		gbc_txtFolio.fill = GridBagConstraints.BOTH;
		gbc_txtFolio.gridx = 0;
		gbc_txtFolio.gridy = 1;
		add(txtFolio, gbc_txtFolio);



		restockRowsModel.addColumn("BARCODE");
		restockRowsModel.addColumn("NAME");
		restockRowsModel.addColumn("AMOUNT");
		restockRowsModel.addColumn("PRICE");
		restockRowsModel.addColumn("SELL BY DATE");
		restockRowsModel.addColumn(" $ ");

		restocksModel.addColumn("FOLIO");
		restocksModel.addColumn("DATE");
		restocksModel.addColumn("PROVIDER");
		restocksModel.addColumn("TOTAL");
		
		btnShowRestocks = new JButton("SHOW LIST");
		btnShowRestocks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DialogRestocks dialog = new DialogRestocks();
					dialog.getListRestockPane().getTable().addMouseListener(new MouseAdapter() {
						public void mouseClicked(MouseEvent e) {
							int row = dialog.getListRestockPane().getTable().getSelectedRow();
							
							 String value = (String) dialog.getListRestockPane().getTable().getModel().getValueAt(row, 0);
							 
							 txtFolio.setText(value);
							 
							 dialog.dispose();
						};
					});
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				} catch (Exception evt) {
					evt.printStackTrace();
				}
			}
		});
		btnShowRestocks.setFont(new Font("Roboto Thin", Font.PLAIN, 17));
		btnShowRestocks.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_btnShowRestocks = new GridBagConstraints();
		gbc_btnShowRestocks.fill = GridBagConstraints.BOTH;
		gbc_btnShowRestocks.insets = new Insets(0, 0, 5, 5);
		gbc_btnShowRestocks.gridx = 1;
		gbc_btnShowRestocks.gridy = 1;
		add(btnShowRestocks, gbc_btnShowRestocks);


		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);

		tableRestockRows = new JTable();
		tableRestockRows.setRowHeight(32);
		tableRestockRows.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		tableRestockRows.setModel(restockRowsModel);
		scrollPane.setViewportView(tableRestockRows);

		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridwidth = 2;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		add(panel_2, gbc_panel_2);

		dateChooserFrom = new JDateChooser();
		dateChooserFrom.setFont(new Font("Roboto Thin", Font.PLAIN, 15));
		dateChooserFrom.setDateFormatString("yyyy-MM-dd");
		dateChooserFrom.setBounds(85, 11, 173, 37);
		panel_2.add(dateChooserFrom);

		dateChooserTo = new JDateChooser();
		dateChooserTo.setFont(new Font("Roboto Thin", Font.PLAIN, 15));
		dateChooserTo.setDateFormatString("yyyy-MM-dd");
		dateChooserTo.setBounds(328, 11, 173, 37);
		panel_2.add(dateChooserTo);

		JLabel lblNewLabel = new JLabel("FROM");
		lblNewLabel.setFont(new Font("Roboto Thin", Font.PLAIN, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 84, 37);
		panel_2.add(lblNewLabel);

		JLabel lblTo = new JLabel("TO");
		lblTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTo.setFont(new Font("Roboto Thin", Font.PLAIN, 17));
		lblTo.setBounds(260, 11, 70, 37);
		panel_2.add(lblTo);

		JButton btnSearch = new JButton("SEARCH");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
				clearRestockTable();
				restocks.clear();
				
				Date initDate = getDateChooserFrom().getDate();
				Date finalDate = getDateChooserTo().getDate();
				if (initDate.after(finalDate) || finalDate.before(initDate)) {
					dateChooserFrom.setDate(null);
					dateChooserTo.setDate(null);
					
					JOptionPane.showMessageDialog(null, "The order of dates is wrong");
					return;
				}
				

				for (Restock restock : Main.getRestocksDataService().getAllRestocks()) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					try {
						Date date = format.parse(restock.getDate());
						if (date.after(initDate) && date.before(finalDate)) {
							restocks.add(restock);
						}
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}



				}

				fillRestockTable();



			}
		});
		btnSearch.setBackground(Color.LIGHT_GRAY);
		btnSearch.setFont(new Font("Roboto Thin", Font.PLAIN, 17));
		btnSearch.setBounds(522, 11, 111, 37);
		panel_2.add(btnSearch);

		scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 3;
		add(scrollPane_1, gbc_scrollPane_1);

		tableRestocks = new JTable();
		tableRestocks.setRowHeight(32);
		tableRestocks.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tableRestocks.getSelectedRow();
				
				 String value = (String) tableRestocks.getModel().getValueAt(row, 0);
				 
				 fillTable(Main.getRestocksDataService().getRows(Integer.parseInt(value)));
			
	
			}
		});
		tableRestocks.setFont(new Font("Roboto Medium", Font.PLAIN, 17));
		tableRestocks.setModel(restocksModel);
		scrollPane_1.setViewportView(tableRestocks);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setLayout(null);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.gridwidth = 4;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 4;
		add(panel_1, gbc_panel_1);




	}

	public void fillRestockTable(){
		clearRestockTable();
		Object[] row = new Object[4];
		for (Restock restock : restocks) {
			row[0] = restock.getFolio();
			row[1] = restock.getDate();
			row[2] = restock.getProvider().getName();
			row[3] = restock.getTotal();
			restocksModel.addRow(row);
		}
	}

	public void fillTable(List<RestockRow> rows) {
		clear();
		Object[] row = new Object[6];
		for (RestockRow restockRow : rows) {
			row[0] = restockRow.getBarcode();
			row[1] = restockRow.getName();
			row[2] = restockRow.getAmount();
			row[3] = restockRow.getPrice();
			row[4] = restockRow.getSellByDate();
			row[5] = restockRow.getTotal();
			restockRowsModel.addRow(row);
		}

	}

	


	public JTextField getTxtFolio() {
		return txtFolio;
	}

	public JDateChooser getDateChooserFrom() {
		return dateChooserFrom;
	}

	public JDateChooser getDateChooserTo() {
		return dateChooserTo;
	}

	public void clear () {
		int i = 0;
		while (restockRowsModel.getRowCount()!=0) {
			restockRowsModel.removeRow(0);
			i++;
		}
	}

	public void clearRestockTable () {
		int i = 0;
		while (restocksModel.getRowCount()!=0) {
			restocksModel.removeRow(0);
			i++;
		}
	}
}
