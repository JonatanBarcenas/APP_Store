package view;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.SystemColor;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;

public class MenuRestockPanel extends JPanel {

	private JPanel btnRestock;
	private JPanel btnConsult;
	private JPanel btnList;
	private JLabel lblRestock;
	private JLabel lblConsult;
	private JLabel lblList;
	private JSeparator separator;
	private JSeparator separator_1;
	private JPanel btnClearContentPane;
	private JLabel lblExit;

	/**
	 * Create the panel.
	 */
	public MenuRestockPanel() {
		setLayout(new GridLayout(1, 4, 0, 0));

		btnRestock = new JPanel();
		btnRestock.setBackground(Color.LIGHT_GRAY);
		btnRestock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRestock.setBackground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnRestock.setBackground(Color.LIGHT_GRAY);
			}
		});
		btnRestock.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(btnRestock);
		btnRestock.setLayout(new BorderLayout(0, 0));
		
		lblRestock = new JLabel("Restock");
		lblRestock.setFont(new Font("Roboto Medium", Font.PLAIN, 25));
		lblRestock.setHorizontalAlignment(SwingConstants.CENTER);
		btnRestock.add(lblRestock, BorderLayout.CENTER);
		
		separator = new JSeparator();
		btnRestock.add(separator, BorderLayout.EAST);



		btnConsult = new JPanel();
		btnConsult.setBackground(Color.LIGHT_GRAY);
		btnConsult.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnConsult.setBackground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnConsult.setBackground(Color.LIGHT_GRAY);
			}
		});
		btnConsult.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(btnConsult);
		btnConsult.setLayout(new BorderLayout(0, 0));
		
		lblConsult = new JLabel("Consult");
		lblConsult.setFont(new Font("Roboto Medium", Font.PLAIN, 25));
		lblConsult.setHorizontalAlignment(SwingConstants.CENTER);
		btnConsult.add(lblConsult, BorderLayout.CENTER);
		
		separator_1 = new JSeparator();
		btnConsult.add(separator_1, BorderLayout.EAST);

		btnList = new JPanel();
		btnList.setBackground(Color.LIGHT_GRAY);
		btnList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnList.setBackground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnList.setBackground(Color.LIGHT_GRAY);
			}
		});
		btnList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(btnList);
		btnList.setLayout(new BorderLayout(0, 0));
		
		lblList = new JLabel("List");
		lblList.setFont(new Font("Roboto Medium", Font.PLAIN, 25));
		lblList.setHorizontalAlignment(SwingConstants.CENTER);
		btnList.add(lblList, BorderLayout.CENTER);
		
		lblExit = new JLabel("Exit");
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		lblExit.setFont(new Font("Roboto Medium", Font.PLAIN, 25));
		

		
		btnClearContentPane = new JPanel();
		btnClearContentPane.setBackground(Color.LIGHT_GRAY);
		btnClearContentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClearContentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnClearContentPane.setBackground(Color.RED);
				lblExit.setForeground(Color.WHITE);
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnClearContentPane.setBackground(Color.LIGHT_GRAY);
				lblExit.setForeground(Color.BLACK);
				
			}
		});
		add(btnClearContentPane);
		btnClearContentPane.setLayout(new BorderLayout(0, 0));
		btnClearContentPane.add(lblExit, BorderLayout.CENTER);
		
	}

	public JPanel getBtnRegister() {
		return btnRestock;
	}

	public void setBtnRegister(JPanel btnRegister) {
		this.btnRestock = btnRegister;
	}

	public JPanel getBtnConsult() {
		return btnConsult;
	}

	public void setBtnConsult(JPanel btnConsult) {
		this.btnConsult = btnConsult;
	}

	public JPanel getBtnList() {
		return btnList;
	}

	public void setBtnList(JPanel btnList) {
		this.btnList = btnList;
	}

	public JPanel getBtnClearContentPane() {
		return btnClearContentPane;
	}
	
	

	
	



}
