package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import records.DatabaseRecord;


public class UtilityDatabaseRecord {

	public static DatabaseRecord getDataBaseParameters() {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(new File("resources/Config.properties")));
			DatabaseRecord record = new DatabaseRecord((String) properties.get("Database_Name"), (String) properties.get("User"),
					(String) properties.get("Password"), (String) properties.get("Driver"),
					(String) properties.get("Protocol"));
			return record;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

}
