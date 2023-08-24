package view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import model.Clock;
import model.CurrentDate;

import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home extends JPanel {
	
	private Clock clock = new Clock();
	private CurrentDate currentDate = new CurrentDate();
	private JPanel btnProducts;
	private JPanel btnRestocks;
	private JPanel btnSales;

	/**
	 * Create the panel.
	 */
	public Home() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{153, 350, 350, 399, 0};
		gridBagLayout.rowHeights = new int[]{0, 275, 350, 112, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridwidth = 3;
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 1;
		add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{692, 395, 0};
		gbl_panel_3.rowHeights = new int[]{105, 59, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblNewLabel = new JLabel("STORE");
		lblNewLabel.setFont(new Font("Roboto Black", Font.PLAIN, 99));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridheight = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_3.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblTime = new JLabel("");
		lblTime.setFont(new Font("Roboto Medium", Font.PLAIN, 95));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblTime = new GridBagConstraints();
		gbc_lblTime.fill = GridBagConstraints.BOTH;
		gbc_lblTime.insets = new Insets(0, 0, 5, 0);
		gbc_lblTime.gridx = 1;
		gbc_lblTime.gridy = 0;
		panel_3.add(lblTime, gbc_lblTime);
		clock.currentHour(lblTime);
		
		
		JLabel lblDate = new JLabel("");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setFont(new Font("Roboto Medium", Font.PLAIN, 60));
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.fill = GridBagConstraints.BOTH;
		gbc_lblDate.gridx = 1;
		gbc_lblDate.gridy = 1;
		panel_3.add(lblDate, gbc_lblDate);
		currentDate.date(lblDate);
		
		btnProducts = new JPanel();
		btnProducts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnProducts.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnProducts.setBackground(getBackground());
			}
		});
		btnProducts.setBackground(getBackground());
		GridBagConstraints gbc_btnProducts = new GridBagConstraints();
		gbc_btnProducts.fill = GridBagConstraints.BOTH;
		gbc_btnProducts.insets = new Insets(0, 0, 5, 5);
		gbc_btnProducts.gridx = 1;
		gbc_btnProducts.gridy = 2;
		add(btnProducts, gbc_btnProducts);
		btnProducts.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("PRODUCTS");
		lblNewLabel_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_3.setFont(new Font("Roboto Black", Font.PLAIN, 55));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		btnProducts.add(lblNewLabel_3, BorderLayout.CENTER);
		
		btnRestocks = new JPanel();
		btnRestocks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRestocks.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnRestocks.setBackground(getBackground());
			}
		});
		btnRestocks.setBackground(getBackground());
		GridBagConstraints gbc_btnRestocks = new GridBagConstraints();
		gbc_btnRestocks.fill = GridBagConstraints.BOTH;
		gbc_btnRestocks.insets = new Insets(0, 0, 5, 5);
		gbc_btnRestocks.gridx = 2;
		gbc_btnRestocks.gridy = 2;
		add(btnRestocks, gbc_btnRestocks);
		btnRestocks.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("RESTOCKS");
		lblNewLabel_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_2.setFont(new Font("Roboto Black", Font.PLAIN, 55));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		btnRestocks.add(lblNewLabel_2, BorderLayout.CENTER);
		
		btnSales = new JPanel();
		btnSales.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSales.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSales.setBackground(getBackground());
			}
		});
		btnSales.setBackground(getBackground());
		GridBagConstraints gbc_btnSales = new GridBagConstraints();
		gbc_btnSales.insets = new Insets(0, 0, 5, 0);
		gbc_btnSales.fill = GridBagConstraints.BOTH;
		gbc_btnSales.gridx = 3;
		gbc_btnSales.gridy = 2;
		add(btnSales, gbc_btnSales);
		btnSales.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("SALES");
		lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_1.setFont(new Font("Roboto Black", Font.PLAIN, 55));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		btnSales.add(lblNewLabel_1, BorderLayout.CENTER);

	}

	public JPanel getBtnProducts() {
		return btnProducts;
	}

	public JPanel getBtnRestocks() {
		return btnRestocks;
	}

	public JPanel getBtnSales() {
		return btnSales;
	}
	
	
}
