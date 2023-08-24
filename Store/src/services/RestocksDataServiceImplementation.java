package services;

import static utilities.UtilityDatabaseRecord.getDataBaseParameters;

import java.sql.Connection;
import java.util.List;

import dao.Database;

import dao.Restocks;
import intefaces.Accessible;
import model.Restock;
import model.RestockRow;
import records.DatabaseRecord;

public class RestocksDataServiceImplementation implements DataService {
	

	private Database database;
	private Restocks restocks;
	
	public RestocksDataServiceImplementation() {
		initDao();
	}
	
	private void initDao() {
		DatabaseRecord record = getDataBaseParameters();
		String dbName = record.name();
		String user = record.user();
		String password = record.password();
		String driver = record.driver();
		String protocol = record.protocol();
		database = Database.newInstance(dbName, user, password, driver, protocol);
		if (database.doConnection().equalsIgnoreCase("successful connection")) {
			Connection connection = database.getConnection();
			restocks = new Restocks(connection);
		} else {
			System.out.println("Connection Error");
		}
	}

	@Override
	public String insert(Accessible element) {

		return (this.restocks.insertRestock((Restock) element)) ? "Done" : "Error";
		
	}
	
	public String insertRestockRow(RestockRow restockRow, int id) {
		return (this.restocks.insertRestockRow(restockRow, id) ? "Done" : "Error");
	}

	@Override
	public boolean exist(String code) {
		// TODO Auto-generated method stub
		return this.restocks.exist(Integer.parseInt(code));
	}

	@Override
	public Accessible get(String code) {
		return this.restocks.getRestock(Integer.parseInt(code));
	}

	@Override
	public Integer delete(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	public Restock getLastRestock() {
		return this.restocks.getLastRestock();
	}
	@Override
	public List<Accessible> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Restock> getAllRestocks(){
		return this.restocks.getAllRestocks();
	}
	
	public List<RestockRow> getRows(int folio){
		return this.restocks.getRows(folio);
	}

	@Override
	public String update(Accessible element) {
		// TODO Auto-generated method stub
		return null;
	}

}
