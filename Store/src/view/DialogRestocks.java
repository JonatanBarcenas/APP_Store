package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;

public class DialogRestocks extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ListRestockPane listRestockPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogRestocks dialog = new DialogRestocks();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogRestocks() {
		setTitle("LIST OF RESTOCKS");
		setBounds(100, 100, 1104, 618);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		listRestockPane = new ListRestockPane();
		contentPanel.add(listRestockPane, BorderLayout.CENTER);
		
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setViewportView(listRestockPane);
//		
//		contentPanel.add(scrollPane, BorderLayout.CENTER);
	}

	public ListRestockPane getListRestockPane() {
		return listRestockPane;
	}
	
	

}
