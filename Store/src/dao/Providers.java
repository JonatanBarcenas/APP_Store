package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Product;
import model.Provider;

public class Providers {
	
	private Connection connection;
	private Statement statement;
	
	public Providers(Connection connection) {
		this.connection = connection;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
	}
	
	public String insertProvider(Provider provider) {
		String SQL = "INSERT INTO provider(id_provider, name, phone) VALUES "
				+"( null, '"+provider.getName()+"' , '"+provider.getPhone()+"' )";
		
		System.out.println(SQL);
		try {
			statement.executeUpdate(SQL);
			return "DONE";
		} catch (SQLException e) {
			System.out.println(e.toString());
			return "ERROR";
		}
	}
	
	public  boolean exist(String id) {
		String sql = "select * from provider where id_provider ="+id+"";
		try {
			return statement.executeQuery(sql).next();
		} catch (SQLException e) {
			System.out.println(e.toString());
			return false;
		}
	}
	
	public Provider getProvider(String id) {
		String SQL = "SELECT * FROM provider where id_provider = "+id+"";
		try {
			ResultSet resultSet = statement.executeQuery(SQL);
			if (resultSet.next()) {
				var provider = new Provider();
				provider.setId(String.valueOf(resultSet.getInt("id_provider")));
				provider.setName(resultSet.getString("name"));
				provider.setPhone(resultSet.getString("phone"));
				return provider;
			}else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
			return null;
		}
	}
	
	public Provider getProviderByName(String name) {
		String SQL = "SELECT * FROM provider where name = '"+name+"'";
		try {
			ResultSet resultSet = statement.executeQuery(SQL);
			if (resultSet.next()) {
				var provider = new Provider();
				provider.setId(String.valueOf(resultSet.getInt("id_provider")));
				provider.setName(resultSet.getString("name"));
				provider.setPhone(resultSet.getString("phone"));
				return provider;
			}else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
			return null;
		}
	}
	
	public List<Provider> getProviders(){
		String SQL = "SELECT * FROM provider";
		try {
			ResultSet resultSet = statement.executeQuery(SQL);
			var providers = new ArrayList<Provider>();
			
			while(resultSet.next()) {
				var provider = new Provider();
				provider.setId(String.valueOf(resultSet.getInt("id_provider")));
				provider.setName(resultSet.getString("name"));
				provider.setPhone(resultSet.getString("phone"));
				providers.add(provider);
			}
			return providers;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		return null;
	}

}
