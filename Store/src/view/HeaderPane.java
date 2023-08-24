package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.GridLayout;

public class HeaderPane extends JPanel {

	private JLabel title;
	private JLabel subtitle;

	/**
	 * Create the panel.
	 */
	public HeaderPane(String title, String subtitle) {
		setLayout(new GridLayout(3, 1, 0, 0));
		this.title = new JLabel(title);
		this.title.setFont(new Font("Roboto Black", Font.BOLD, 20));
		this.title.setHorizontalAlignment(SwingConstants.CENTER);
		add(this.title);
		this.subtitle = new JLabel(subtitle);
		this.subtitle.setFont(new Font("Roboto Black", Font.BOLD, 18));
		this.subtitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(this.subtitle);
	}

}
