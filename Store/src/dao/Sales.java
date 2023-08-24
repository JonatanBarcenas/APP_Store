package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Provider;
import model.Restock;
import model.RestockRow;
import model.Sale;
import model.TicketRow;

public class Sales {
	
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private Statement statement;
	
	public Sales(Connection connection) {
		this.connection = connection;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}
	
	public boolean insertTicketRow(TicketRow ticketRow, int id) {
		String SQL = "INSERT INTO ticket_row(id_ticket, barcode, amount, price,total, id_sale, name) VALUES (?,?,?,?,?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setString(1, null);
			preparedStatement.setLong(2, Long.parseLong(ticketRow.getBarcode()));
			preparedStatement.setInt(3, ticketRow.getAmount());
			preparedStatement.setDouble(4, ticketRow.getPrice());
			preparedStatement.setDouble(5, ticketRow.getTotal());
			preparedStatement.setInt(6, id);
			preparedStatement.setString(7, ticketRow.getProduct());
			return preparedStatement.execute();
		} catch (SQLException e) {
			System.out.println(e.toString());
			return false;
		}
	}
	
	public boolean insertSale(Sale sale) {
		String SQL = "INSERT INTO sale (id_sale, date, total) VALUES (?,?,?)";
		try {
			preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setString(1,null);
			preparedStatement.setString(2, sale.getDate());
			preparedStatement.setDouble(3, sale.getTotal());
			return preparedStatement.execute();
		} catch (SQLException e) {
			System.out.println(e.toString());
            return false;
		}
	}
	
	public List<Sale> getAllSales(){
		var sales = new ArrayList<Sale>();
		String SQL = "SELECT * FROM sale";
		try {
			preparedStatement = connection.prepareStatement(SQL);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Sale sale = new Sale();
				sale.setFolio(String.valueOf(resultSet.getInt("id_sale")));
				sale.setDate(resultSet.getString("date"));
				sale.setTotal(resultSet.getDouble("total"));
				sales.add(sale);
			}
			return sales;
		}catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.toString());
			return null;
		}
		
	}
	
	public List<TicketRow> getRows(int folio){
		
		var ticketRows = new ArrayList<TicketRow>();
		String SQL = "SELECT * FROM ticket_row WHERE id_ticket = ?";
		try {
			preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setInt(1, folio);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				TicketRow ticketRow = new TicketRow();
				ticketRow.setBarcode(String.valueOf(resultSet.getLong("barcode")));
				ticketRow.setProduct(resultSet.getString("name"));
				ticketRow.setAmount(resultSet.getInt("amount"));
				ticketRow.setPrice(resultSet.getDouble("price"));
				ticketRow.setTotal(resultSet.getDouble("total"));
				ticketRows.add(ticketRow);

			}
			return ticketRows;
		} catch (SQLException e) {
			System.out.println(e.toString());
			return null;
		}
		
	}
	
	public boolean exist(int folio) {
		String SQL = "SELECT * FROM sale WHERE id_sale = "+folio+"";
		try {
			return statement.executeQuery(SQL).next();
		} catch (SQLException e) {
			System.out.println(e.toString());
			return false;
		}
	}
	
	public Sale getSale(int folio) {
		String SQL = "SELECT * FROM sale WHERE id_sale = ?";
		try {
			preparedStatement = connection.prepareStatement(SQL);
			preparedStatement.setInt(1, folio);
			resultSet = preparedStatement.executeQuery();
			
			var sale = new Sale();
			if (resultSet.next()) {
				sale.setFolio(String.valueOf(resultSet.getInt("id_sale")));
				sale.setDate(resultSet.getString("date"));
				sale.setTotal(resultSet.getDouble("total"));
				return sale;
				
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	public Sale getLastSale() {
		String SQL = "SELECT * FROM sale WHERE id_sale = (SELECT MAX(id_sale) FROM sale)";
		try {
			ResultSet resultSet = statement.executeQuery(SQL);
			var sale = new Sale();
			if (resultSet.next()) {
				sale.setFolio(String.valueOf(resultSet.getInt("id_sale")));
				sale.setDate(resultSet.getString("date"));
				sale.setTotal(resultSet.getDouble("total"));
				return sale;
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return null;
	}
	
	

}
