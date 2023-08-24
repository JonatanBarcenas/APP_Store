package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.Provider;

import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class ProviderPanel extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JButton cancelButton;
	private JButton okButton;
	private JTextField textPhone;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			ModifyPanel dialog = new ModifyPanel();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public ProviderPanel() {
		setFont(new Font("Roboto", Font.PLAIN, 12));
		setTitle("PROVIDER INFORMATION");
		setBounds(100, 100, 606, 221);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 590, 528);
		contentPanel.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{122, 82, 335, 0};
		gbl_panel.rowHeights = new int[]{35, 40, 40, 37, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(ProviderPanel.class.getResource("/resources/img/iconodeusaurio (1).png")));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.gridheight = 3;
			gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
			gbc_lblNewLabel.gridx = 0;
			gbc_lblNewLabel.gridy = 1;
			panel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			JLabel lblName = new JLabel("Name: ");
			lblName.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
			GridBagConstraints gbc_lblName = new GridBagConstraints();
			gbc_lblName.anchor = GridBagConstraints.EAST;
			gbc_lblName.insets = new Insets(0, 0, 5, 5);
			gbc_lblName.gridx = 1;
			gbc_lblName.gridy = 1;
			panel.add(lblName, gbc_lblName);
		}
		{
			txtName = new JTextField();
			txtName.setFont(new Font("Roboto", Font.BOLD, 12));
			txtName.setColumns(10);
			txtName.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_txtName = new GridBagConstraints();
			gbc_txtName.fill = GridBagConstraints.BOTH;
			gbc_txtName.insets = new Insets(0, 0, 5, 0);
			gbc_txtName.gridx = 2;
			gbc_txtName.gridy = 1;
			panel.add(txtName, gbc_txtName);
		}
		{
			JLabel lblPresentation = new JLabel("Phone: ");
			lblPresentation.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
			GridBagConstraints gbc_lblPresentation = new GridBagConstraints();
			gbc_lblPresentation.anchor = GridBagConstraints.EAST;
			gbc_lblPresentation.insets = new Insets(0, 0, 5, 5);
			gbc_lblPresentation.gridx = 1;
			gbc_lblPresentation.gridy = 2;
			panel.add(lblPresentation, gbc_lblPresentation);
		}
		{
			textPhone = new JTextField();
			GridBagConstraints gbc_textPhone = new GridBagConstraints();
			gbc_textPhone.insets = new Insets(0, 0, 5, 0);
			gbc_textPhone.fill = GridBagConstraints.BOTH;
			gbc_textPhone.gridx = 2;
			gbc_textPhone.gridy = 2;
			panel.add(textPhone, gbc_textPhone);
			textPhone.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (!txtName.getText().trim().isEmpty() && !textPhone.getText().trim().isEmpty()) {
							Provider provider = new Provider();
							provider.setName(txtName.getText());
							provider.setPhone(textPhone.getText());
							JOptionPane.showMessageDialog(null, Main.getProvidersDataService().insert(provider));
							dispose();
							
						}else {
							JOptionPane.showMessageDialog(null, "First fill the fields");
						}
						
					}
				});
				okButton.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(JTextField txtBarcode) {
		this.txtName = txtBarcode;
	}


	public JTextField getTextPhone() {
		return textPhone;
	}

	public void setTextPhone(JTextField textPhone) {
		this.textPhone = textPhone;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public JButton getOkButton() {
		return okButton;
	}

	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}
	
	
}
