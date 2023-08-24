package services;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import dao.Database;
import dao.Products;
import intefaces.Accessible;
import model.Product;
import records.DatabaseRecord;

import static utilities.UtilityDatabaseRecord.getDataBaseParameters;

public class ProductsDataServiceImplementation implements DataService{
	
	private Database database;
	private Products products;
	
	public ProductsDataServiceImplementation() {
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
			products = new Products(connection);
		} else {
			System.out.println("Connection Error");
		}
	}

	@Override
	public String insert(Accessible element) {
		if(!(element instanceof Product product)) {
			return "casting error";
		}
		return this.products.insertProduct((Product)element);
	}

	@Override
	public boolean exist(String code) {
		return this.products.exist(code);
	}

	@Override
	public Accessible get(String code) {
		return this.products.getProduct(code);
	}
	
	

	@Override
	public Integer delete(String code) {
		return this.products.deleteProduct(code);
		
	}

	@Override
	public List<Accessible> getAll() {
		List<Accessible> list = new ArrayList<Accessible>();
		for (Product product : this.products.getProducts()) {
			list.add(product);
		}
		return list;
	}
	
	public List<Product> getAllProducts(){
		return this.products.getProducts();
	}

	@Override
	public String update(Accessible element) {
		return this.products.updateProduct((Product)element);
	}

	

}
