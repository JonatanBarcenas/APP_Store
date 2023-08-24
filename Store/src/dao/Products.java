package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import intefaces.Accessible;
import model.Product;

public class Products {
	
	private Connection connection;
	private Statement statement;
	
	public Products(Connection connection) {

		this.connection = connection;

		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}
	
	public String insertProduct(Product product) {
		String SQL = "INSERT INTO products(barcode, name, brand, type, content, measureUnit, presentation, description, price, stock_Max, stock_Min, onInventory) VALUES "
				+ "(" + Long.parseLong(product.getBarcode()) + ",'" + product.getName() + "','" + product.getBrand() + "','"
				+ product.getType() + "','" + product.getContent() + "','" + product.getMeasureUnit() + "','"
				+ product.getPresentation() + "','" + product.getDescription() + "'," 
				+ product.getPrice() + " , " + product.getStockMax() + " , " + product.getStockMin() + " , " + product.getOnInventory()+" )";
		
		System.out.println(SQL);
		try {
			statement.executeUpdate(SQL);
			return "DONE";
		} catch (SQLException e) {
			System.out.println(e.toString());
			return "ERROR";
		}
	}
	
	public Product getProduct(String barcode) {
		String SQL = "select * from products where barcode =" + barcode + "";
		try {
			ResultSet resultSet = statement.executeQuery(SQL);
			if (resultSet.next()) {
				var product = new Product();
				product.setBarcode(resultSet.getString("barcode"));
				product.setName(resultSet.getString("name"));
				product.setBrand(resultSet.getString("type"));
				product.setContent(resultSet.getString("content"));
				product.setMeasureUnit(resultSet.getString("measureUnit"));
				product.setPresentation(resultSet.getString("presentation"));
				product.setDescription(resultSet.getString("description"));
				product.setPrice(resultSet.getDouble("price"));
				product.setStockMax(resultSet.getInt("stock_Max"));
				product.setStockMin(resultSet.getInt("stock_Min"));
				product.setOnInventory(resultSet.getInt("onInventory"));
				return product;
			} else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
			return null;
		}
	}
	
	public Integer deleteProduct(String barcode) {
		String SQL = "delete from products where barcode =" + barcode + "";
		try {
			return statement.executeUpdate(SQL);
			
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return 0;
	}
	
	public boolean exist(String barcode) {
		String sql = "SELECT * FROM products WHERE barcode = " + barcode + "";
		try {
			return statement.executeQuery(sql).next();
		} catch (SQLException e) {
			System.out.println(e.toString());
			return false;
		}
	}
	
	public String updateProduct(Product product) {
	    String SQL = "UPDATE products SET name='" + product.getName() + "', "
	            + "brand='" + product.getBrand() + "', "
	            + "type='" + product.getType() + "', "
	            + "content='" + product.getContent() + "', "
	            + "measureUnit='" + product.getMeasureUnit() + "', "
	            + "presentation='" + product.getPresentation() + "', "
	            + "description='" + product.getDescription() + "', "
	            + "price=" + product.getPrice() + ", "
	            + "stock_Max=" + product.getStockMax() + ", "
	            + "stock_Min=" + product.getStockMin() + ", "
	            + "onInventory=" + product.getOnInventory() + " "
	            + "WHERE barcode=" + product.getBarcode() + "";
	    System.out.println(SQL);
	    try {
	        statement.executeUpdate(SQL);
	        return "DONE";
	    } catch (SQLException e) {
	        System.out.println(e.toString());
	        return "ERROR";
	    }
	}
	
	public List<Product> getProducts(){
		String SQL = "SELECT * FROM products";
		try {
			ResultSet resultSet = statement.executeQuery(SQL);
			var products = new ArrayList<Product>();
			
			while(resultSet.next()) {
				var product = new Product();
				product.setBarcode(resultSet.getString("barcode"));
				product.setName(resultSet.getString("name"));
				product.setBrand(resultSet.getString("brand"));
				product.setType(resultSet.getString("type"));
				product.setContent(resultSet.getString("content"));
				product.setMeasureUnit(resultSet.getString("measureUnit"));
				product.setStockMax(resultSet.getInt("stock_Max"));
				product.setStockMin(resultSet.getInt("stock_Min"));
				product.setPresentation(resultSet.getString("presentation"));
				product.setDescription(resultSet.getString("description"));
				product.setPrice(resultSet.getDouble("price"));
				product.setOnInventory(resultSet.getInt("onInventory"));
				products.add(product);
			}
			Collections.sort(products);
			return products;
		} catch (SQLException e) {
			
			System.out.println(e.toString());
			
		}
		return null;
		
	}

	
	

}
