package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDate {

	public void date(javax.swing.JLabel Label) {

		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		Label.setText(dateFormat.format(currentDate));
	}

}
