package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;

public class DialogProductList extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ListPane listPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogProductList dialog = new DialogProductList();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogProductList() {
		setType(Type.POPUP);
		setTitle("LIST OF PRODUCTS");
		setBounds(100, 100, 1281, 609);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		listPane = new ListPane();
		
		
		contentPanel.add(listPane, BorderLayout.CENTER);
	}

	public ListPane getListPane() {
		return listPane;
	}
	

	
}
