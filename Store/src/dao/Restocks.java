package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Provider;
import model.Restock;
import model.RestockRow;
import services.ProvidersDataServiceImplementation;

public class Restocks {

	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private Statement statement;
	private ProvidersDataServiceImplementation providersDataService = new ProvidersDataServiceImplementation();

	public Restocks(Connection connection) {
		this.connection = connection;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}
	
	public boolean insertRestockRow(RestockRow restockRow, int id) {
		String SQL = "INSERT INTO restock_row (barcode, amount, name, price, sellByDate, total, id_restock) VALUES (?,?,?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setLong(1, Long.parseLong(restockRow.getBarcode()));
			preparedStatement.setInt(2, restockRow.getAmount());
			preparedStatement.setString(3, restockRow.getName());
			preparedStatement.setDouble(4, restockRow.getPrice());
			preparedStatement.setString(5, restockRow.getSellByDate());
			preparedStatement.setDouble(6, restockRow.getTotal());
			preparedStatement.setInt(7, id);
			return preparedStatement.execute();
		} catch (SQLException e) {
			System.out.println(e.toString());
			return false;
		}
	}
	
	public boolean insertRestock(Restock restock) {
		String SQL = "INSERT INTO restock (id_restock, date, id_provider, total) VALUES (?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setString(1,null);
			preparedStatement.setString(2, restock.getDate());
			preparedStatement.setInt(3, Integer.parseInt(restock.getProvider().getId()));
			preparedStatement.setDouble(4, restock.getTotal());
			return preparedStatement.execute();
		} catch (SQLException e) {
			System.out.println(e.toString());
            return false;
		}
	}
	
	public List<Restock> getAllRestocks(){
		var restocks = new ArrayList<Restock>();
		String SQL = "SELECT * FROM restock";
		try {
			preparedStatement = connection.prepareStatement(SQL);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Restock restock = new Restock();
				
				restock.setFolio(String.valueOf(resultSet.getInt("id_restock")));
				restock.setDate(resultSet.getString("date"));
				restock.setProvider((Provider) providersDataService.get(String.valueOf(resultSet.getInt("id_provider"))));
				restock.setTotal(resultSet.getDouble("total"));
				
				restocks.add(restock);

			}
			return restocks;
		} catch (SQLException e) {
		System.out.println(e.toString());
		return null;
		}
		
	}
	
	public List<RestockRow> getRows(int folio){
		var restockRows = new ArrayList<RestockRow>();
		String SQL = "SELECT * FROM restock_row where id_restock = ?";
		try {
			
			preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setInt(1, folio);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				RestockRow restockRow = new RestockRow();
				restockRow.setBarcode(String.valueOf(resultSet.getLong("barcode")));
				restockRow.setAmount(resultSet.getInt("amount"));
				restockRow.setName(resultSet.getString("name"));
				restockRow.setPrice(resultSet.getDouble("price"));
				restockRow.setSellByDate(resultSet.getString("sellByDate"));
				restockRow.setTotal(resultSet.getDouble("total"));
				restockRows.add(restockRow);
				
			}
			
			return restockRows;
		} catch (SQLException e) {
			System.out.println(e.toString());
			return null;
		
		}
	}
	
	public Restock getLastRestock() {
		String SQL = "SELECT * FROM restock WHERE id_restock = (SELECT MAX(id_restock) FROM restock)";
		try {
			
			ResultSet resultSet = statement.executeQuery(SQL);
			var restock = new Restock();
			if (resultSet.next()) {
				restock.setFolio(String.valueOf(resultSet.getInt("id_restock")));
				restock.setDate(resultSet.getString("date"));
				restock.setProvider((Provider) providersDataService.get(String.valueOf(resultSet.getInt("id_provider"))));
				restock.setTotal(resultSet.getDouble("total"));
				System.out.println(restock.toString());
				return restock;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean exist(int folio) {
		String sql = "SELECT * FROM restock WHERE id_restock = " + folio + "";
		try {
			return statement.executeQuery(sql).next();
		} catch (SQLException e) {
			System.out.println(e.toString());
			return false;
		}
	}
	
	public Restock getRestock(int folio) {
		String SQL = "SELECT * FROM restock WHERE id_restock = ?";
		try {
			preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setInt(1, folio);
			resultSet = preparedStatement.executeQuery();
			var restock = new Restock();
			if (resultSet.next()) {
				restock.setFolio(String.valueOf(resultSet.getInt("id_restock")));
				restock.setDate(resultSet.getString("date"));
				restock.setProvider((Provider) providersDataService.get(String.valueOf(resultSet.getInt("id_provider"))));
				restock.setTotal(resultSet.getDouble("total"));
				
				return restock;
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
			
		}
		return null;
	}
	

}
