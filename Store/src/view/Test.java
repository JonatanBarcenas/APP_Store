package view;



import java.sql.Connection;
import static utilities.UtilityDatabaseRecord.getDataBaseParameters;
import javax.swing.JOptionPane;

import dao.Database;
import dao.Products;
import model.Product;
import records.DatabaseRecord;


public class Test {

//	public static void main(String[] args) {
//		DatabaseRecord rec = getDataBaseParameters();
//		//System.out.println(rec.toString());
//
//		Database baseDatos=Database.newInstance(rec.name(), rec.user(), rec.password(), rec.driver(), rec.protocol());
//		
//	//	System.out.println(baseDatos.doConnection());
//		
//		if(baseDatos.doConnection().equals("successful connection")) {
//			
//			Connection connection = baseDatos.getConnection();
//
//			Products tablaProducto = new Products(connection);
//			
//			Product producto = new Product();
//			
//			producto.setBarcode("111111111111111");
//			producto.setName("Sabritas habanero");
//			producto.setBrand("Sabritas");
//			producto.setContent("250");
//			producto.setStockMax((int)15);
//			producto.setStockMin((int)1);
//			producto.setType("Botana");
//			producto.setMeasureUnit("Gramos");
//			producto.setPresentation("Bolsa");
//			producto.setDescription("Botana para ver pelis");
//			producto.setPrice(5.55);
//			producto.setOnInventory(0);
//			
//			
//			
//			System.out.println(tablaProducto.insertProduct(producto));
//		}else {
//			JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos");
//		}
//	}

}
