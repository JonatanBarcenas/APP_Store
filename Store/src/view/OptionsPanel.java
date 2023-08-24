package view;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class OptionsPanel extends JPanel {
	private JPanel btnAccept;
	private JPanel btnContinue;
	private JPanel btnExit;
	private JLabel lblAcept;
	private JLabel lblContinue;
	private JLabel lblExit;
	private JSeparator separator;
	private JSeparator separator_1;

	/**
	 * Create the panel.
	 */
	public OptionsPanel() {
		setLayout(new GridLayout(1, 3, 0, 0));

		btnAccept = new JPanel();
		btnAccept.setBackground(Color.LIGHT_GRAY);
		btnAccept.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAccept.setBackground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnAccept.setBackground(Color.LIGHT_GRAY);
			}
		});
		btnAccept.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(btnAccept);
		btnAccept.setLayout(new BorderLayout(0, 0));
		
		lblAcept = new JLabel("Accept\r\n");
		lblAcept.setFont(new Font("Roboto Medium", Font.PLAIN, 25));
		lblAcept.setHorizontalAlignment(SwingConstants.CENTER);
		btnAccept.add(lblAcept, BorderLayout.CENTER);
		
		separator = new JSeparator();
		btnAccept.add(separator, BorderLayout.EAST);



		btnContinue = new JPanel();
		btnContinue.setBackground(Color.LIGHT_GRAY);
		btnContinue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnContinue.setBackground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnContinue.setBackground(Color.LIGHT_GRAY);
			}
		});
		btnContinue.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(btnContinue);
		btnContinue.setLayout(new BorderLayout(0, 0));
		
		lblContinue = new JLabel("Continue\r\n");
		lblContinue.setFont(new Font("Roboto Medium", Font.PLAIN, 25));
		lblContinue.setHorizontalAlignment(SwingConstants.CENTER);
		btnContinue.add(lblContinue, BorderLayout.CENTER);
		
		separator_1 = new JSeparator();
		btnContinue.add(separator_1, BorderLayout.EAST);

		btnExit = new JPanel();
		btnExit.setBackground(Color.LIGHT_GRAY);
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnExit.setBackground(Color.RED);
				lblExit.setForeground(Color.WHITE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnExit.setBackground(Color.LIGHT_GRAY);
				lblExit.setForeground(Color.BLACK);
			}
		});
		btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		add(btnExit);
		btnExit.setLayout(new BorderLayout(0, 0));
		
		lblExit = new JLabel("Exit\r\n");
		lblExit.setFont(new Font("Roboto Medium", Font.PLAIN, 25));
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		btnExit.add(lblExit, BorderLayout.CENTER);

	}

	public JPanel getBtnAccept() {
		return btnAccept;
	}

	

	public JPanel getBtnContinue() {
		return btnContinue;
	}

	
	public JPanel getBtnExit() {
		return btnExit;
	}


	
	


	
	
	

}
