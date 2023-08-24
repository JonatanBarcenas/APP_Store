package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class ModifyPanel extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtBrand;
	private JTextField txtContent;
	private JTextField txtStockMax;
	private JTextField txtStockMin;
	private JTextField txtDescription;
	private JComboBox comboBoxType;
	private JComboBox comboBoxUnit;
	private JComboBox comboBoxPresentation;
	private JButton cancelButton;
	private JButton okButton;

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
	public ModifyPanel() {
		setTitle("PRODUCT INFORMATION");
		setBounds(100, 100, 606, 479);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 590, 528);
		contentPanel.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{24, 103, 403, 0};
		gbl_panel.rowHeights = new int[]{35, 40, 40, 40, 40, 40, 40, 40, 40, 37, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
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
			JLabel lblBrand = new JLabel("Brand: ");
			lblBrand.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
			GridBagConstraints gbc_lblBrand = new GridBagConstraints();
			gbc_lblBrand.anchor = GridBagConstraints.EAST;
			gbc_lblBrand.insets = new Insets(0, 0, 5, 5);
			gbc_lblBrand.gridx = 1;
			gbc_lblBrand.gridy = 2;
			panel.add(lblBrand, gbc_lblBrand);
		}
		{
			txtBrand = new JTextField();
			txtBrand.setFont(new Font("Roboto", Font.BOLD, 12));
			txtBrand.setColumns(10);
			txtBrand.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_txtBrand = new GridBagConstraints();
			gbc_txtBrand.fill = GridBagConstraints.BOTH;
			gbc_txtBrand.insets = new Insets(0, 0, 5, 0);
			gbc_txtBrand.gridx = 2;
			gbc_txtBrand.gridy = 2;
			panel.add(txtBrand, gbc_txtBrand);
		}
		{
			JLabel lblType = new JLabel("Type: ");
			lblType.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
			GridBagConstraints gbc_lblType = new GridBagConstraints();
			gbc_lblType.anchor = GridBagConstraints.EAST;
			gbc_lblType.insets = new Insets(0, 0, 5, 5);
			gbc_lblType.gridx = 1;
			gbc_lblType.gridy = 3;
			panel.add(lblType, gbc_lblType);
		}
		{
			comboBoxType = new JComboBox();
			comboBoxType.setModel(new DefaultComboBoxModel(new String[] {"", "Fresh produce", "Meat and poultry", "Dairy products", "Bakery items", "Canned goods", "Frozen foods", "Beverages", "Snacks", "Pasta, rice, and grains", "Condiments", "Baking supplies", "Cleaning and household products", "Personal care products", "Pet food and supplies", "Baby care products", "Health and wellness products", "International foods", "Deli items", "Fresh seafood", "Specialty or gourmet products", "Food"}));
			comboBoxType.setFont(new Font("Roboto", Font.BOLD, 12));
			comboBoxType.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_comboBoxType = new GridBagConstraints();
			gbc_comboBoxType.fill = GridBagConstraints.BOTH;
			gbc_comboBoxType.insets = new Insets(0, 0, 5, 0);
			gbc_comboBoxType.gridx = 2;
			gbc_comboBoxType.gridy = 3;
			panel.add(comboBoxType, gbc_comboBoxType);
		}
		{
			JLabel lblContent = new JLabel("Content: ");
			lblContent.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
			GridBagConstraints gbc_lblContent = new GridBagConstraints();
			gbc_lblContent.anchor = GridBagConstraints.EAST;
			gbc_lblContent.insets = new Insets(0, 0, 5, 5);
			gbc_lblContent.gridx = 1;
			gbc_lblContent.gridy = 4;
			panel.add(lblContent, gbc_lblContent);
		}
		{
			txtContent = new JTextField();
			txtContent.setFont(new Font("Roboto", Font.BOLD, 12));
			txtContent.setColumns(10);
			txtContent.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_txtContent = new GridBagConstraints();
			gbc_txtContent.fill = GridBagConstraints.BOTH;
			gbc_txtContent.insets = new Insets(0, 0, 5, 0);
			gbc_txtContent.gridx = 2;
			gbc_txtContent.gridy = 4;
			panel.add(txtContent, gbc_txtContent);
		}
		{
			JLabel lblUnit = new JLabel("Measure unit: ");
			lblUnit.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
			GridBagConstraints gbc_lblUnit = new GridBagConstraints();
			gbc_lblUnit.anchor = GridBagConstraints.EAST;
			gbc_lblUnit.insets = new Insets(0, 0, 5, 5);
			gbc_lblUnit.gridx = 1;
			gbc_lblUnit.gridy = 5;
			panel.add(lblUnit, gbc_lblUnit);
		}
		{
			comboBoxUnit = new JComboBox();
			comboBoxUnit.setModel(new DefaultComboBoxModel(new String[] {"", "grams", "mililiters", "liters", "milligrams", "pieces", "ounces", "centimeters", "millimeters", "kilograms"}));
			comboBoxUnit.setFont(new Font("Roboto", Font.BOLD, 12));
			comboBoxUnit.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_comboBoxUnit = new GridBagConstraints();
			gbc_comboBoxUnit.fill = GridBagConstraints.BOTH;
			gbc_comboBoxUnit.insets = new Insets(0, 0, 5, 0);
			gbc_comboBoxUnit.gridx = 2;
			gbc_comboBoxUnit.gridy = 5;
			panel.add(comboBoxUnit, gbc_comboBoxUnit);
		}
		{
			JLabel lblMaximumStock = new JLabel("Maximum stock: ");
			lblMaximumStock.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
			GridBagConstraints gbc_lblMaximumStock = new GridBagConstraints();
			gbc_lblMaximumStock.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblMaximumStock.insets = new Insets(0, 0, 5, 5);
			gbc_lblMaximumStock.gridx = 1;
			gbc_lblMaximumStock.gridy = 6;
			panel.add(lblMaximumStock, gbc_lblMaximumStock);
		}
		{
			txtStockMax = new JTextField();
			txtStockMax.setFont(new Font("Roboto", Font.BOLD, 12));
			txtStockMax.setColumns(10);
			txtStockMax.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_txtStockMax = new GridBagConstraints();
			gbc_txtStockMax.fill = GridBagConstraints.BOTH;
			gbc_txtStockMax.insets = new Insets(0, 0, 5, 0);
			gbc_txtStockMax.gridx = 2;
			gbc_txtStockMax.gridy = 6;
			panel.add(txtStockMax, gbc_txtStockMax);
		}
		{
			JLabel lblMinimumStock = new JLabel("Minimum stock: ");
			lblMinimumStock.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
			GridBagConstraints gbc_lblMinimumStock = new GridBagConstraints();
			gbc_lblMinimumStock.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblMinimumStock.insets = new Insets(0, 0, 5, 5);
			gbc_lblMinimumStock.gridx = 1;
			gbc_lblMinimumStock.gridy = 7;
			panel.add(lblMinimumStock, gbc_lblMinimumStock);
		}
		{
			txtStockMin = new JTextField();
			txtStockMin.setFont(new Font("Roboto", Font.BOLD, 12));
			txtStockMin.setColumns(10);
			txtStockMin.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_txtStockMin = new GridBagConstraints();
			gbc_txtStockMin.fill = GridBagConstraints.BOTH;
			gbc_txtStockMin.insets = new Insets(0, 0, 5, 0);
			gbc_txtStockMin.gridx = 2;
			gbc_txtStockMin.gridy = 7;
			panel.add(txtStockMin, gbc_txtStockMin);
		}
		{
			JLabel lblPresentation = new JLabel("Presentation: ");
			lblPresentation.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
			GridBagConstraints gbc_lblPresentation = new GridBagConstraints();
			gbc_lblPresentation.anchor = GridBagConstraints.EAST;
			gbc_lblPresentation.insets = new Insets(0, 0, 5, 5);
			gbc_lblPresentation.gridx = 1;
			gbc_lblPresentation.gridy = 8;
			panel.add(lblPresentation, gbc_lblPresentation);
		}
		{
			comboBoxPresentation = new JComboBox();
			comboBoxPresentation.setModel(new DefaultComboBoxModel(new String[] {"", "box", "can", "bottle", "plastic bottle", "package", "bag", "bar", "Wrapper", "cylinder", "roll"}));
			comboBoxPresentation.setFont(new Font("Roboto", Font.BOLD, 12));
			comboBoxPresentation.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_comboBoxPresentation = new GridBagConstraints();
			gbc_comboBoxPresentation.fill = GridBagConstraints.BOTH;
			gbc_comboBoxPresentation.insets = new Insets(0, 0, 5, 0);
			gbc_comboBoxPresentation.gridx = 2;
			gbc_comboBoxPresentation.gridy = 8;
			panel.add(comboBoxPresentation, gbc_comboBoxPresentation);
		}
		{
			JLabel lblDescription = new JLabel("Description: ");
			lblDescription.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
			GridBagConstraints gbc_lblDescription = new GridBagConstraints();
			gbc_lblDescription.anchor = GridBagConstraints.NORTHEAST;
			gbc_lblDescription.insets = new Insets(0, 0, 0, 5);
			gbc_lblDescription.gridx = 1;
			gbc_lblDescription.gridy = 9;
			panel.add(lblDescription, gbc_lblDescription);
		}
		{
			txtDescription = new JTextField();
			txtDescription.setFont(new Font("Roboto", Font.BOLD, 12));
			txtDescription.setColumns(10);
			txtDescription.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			GridBagConstraints gbc_txtDescription = new GridBagConstraints();
			gbc_txtDescription.fill = GridBagConstraints.BOTH;
			gbc_txtDescription.gridx = 2;
			gbc_txtDescription.gridy = 9;
			panel.add(txtDescription, gbc_txtDescription);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setFont(new Font("Roboto Medium", Font.PLAIN, 15));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
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

	public JTextField getTxtBrand() {
		return txtBrand;
	}

	public void setTxtBrand(JTextField txtName) {
		this.txtBrand = txtName;
	}

	public JTextField getTxtContent() {
		return txtContent;
	}

	public void setTxtContent(JTextField txtContent) {
		this.txtContent = txtContent;
	}

	public JTextField getTxtStockMax() {
		return txtStockMax;
	}

	public void setTxtStockMax(JTextField txtStockMax) {
		this.txtStockMax = txtStockMax;
	}

	public JTextField getTxtStockMin() {
		return txtStockMin;
	}

	public void setTxtStockMin(JTextField txtStockMin) {
		this.txtStockMin = txtStockMin;
	}

	public JTextField getTxtDescription() {
		return txtDescription;
	}

	public void setTxtDescription(JTextField txtDescription) {
		this.txtDescription = txtDescription;
	}

	public JComboBox getComboBoxType() {
		return comboBoxType;
	}

	public void setComboBoxType(JComboBox comboBoxType) {
		this.comboBoxType = comboBoxType;
	}

	public JComboBox getComboBoxUnit() {
		return comboBoxUnit;
	}

	public void setComboBoxUnit(JComboBox comboBoxUnit) {
		this.comboBoxUnit = comboBoxUnit;
	}

	public JComboBox getComboBoxPresentation() {
		return comboBoxPresentation;
	}

	public void setComboBoxPresentation(JComboBox comboBoxPresentation) {
		this.comboBoxPresentation = comboBoxPresentation;
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
