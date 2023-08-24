package services;

import static utilities.UtilityDatabaseRecord.getDataBaseParameters;

import java.sql.Connection;
import java.util.List;

import dao.Database;

import dao.Sales;
import intefaces.Accessible;

import model.Sale;
import model.TicketRow;
import records.DatabaseRecord;

public class SalesDataServiceImplementation  implements DataService{
	private Database database;
	private Sales sales;
	
	public SalesDataServiceImplementation() {
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
			sales = new Sales(connection);
		} else {
			System.out.println("Connection Error");
		}
	}

	@Override
	public String insert(Accessible element) {
		// TODO Auto-generated method stub
		return (this.sales.insertSale((Sale) element)) ? "Done" : "Error";
	}

	public String insertTicketRow(TicketRow ticketRow, int id) {
		return (this.sales.insertTicketRow(ticketRow, id)) ? "Done" : "Error";
	}
	@Override
	public boolean exist(String code) {
		// TODO Auto-generated method stub
		return this.sales.exist(Integer.parseInt(code));
	}

	@Override
	public Accessible get(String code) {
		// TODO Auto-generated method stub
		return this.sales.getSale(Integer.parseInt(code));
	}

	@Override
	public Integer delete(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Accessible> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Sale> getAllSales(){
		return this.sales.getAllSales();
	}
	
	public Sale getLastSale() {
		return this.sales.getLastSale();
	}
	
	public List<TicketRow> getRows(int folio){
		return this.sales.getRows(folio);
	}


	@Override
	public String update(Accessible element) {
		// TODO Auto-generated method stub
		return null;
	}
}
