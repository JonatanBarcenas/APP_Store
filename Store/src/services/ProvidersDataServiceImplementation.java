package services;

import static utilities.UtilityDatabaseRecord.getDataBaseParameters;

import java.sql.Connection;
import java.util.List;

import dao.Database;
import dao.Providers;
import intefaces.Accessible;
import model.Provider;
import records.DatabaseRecord;

public class ProvidersDataServiceImplementation implements DataService {

	private Database database;
	private Providers providers;

	public ProvidersDataServiceImplementation() {
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
			providers = new Providers(connection);
		} else {
			System.out.println("Connection Error");
		}
	}

	@Override
	public String insert(Accessible element) {
		if(!(element instanceof Provider provider)) {
			return "casting error";
		}
		return this.providers.insertProvider((Provider)element);

	}

	@Override
	public boolean exist(String code) {
		// TODO Auto-generated method stub
		return this.providers.exist(code);
	}

	@Override
	public Accessible get(String code) {
		// TODO Auto-generated method stub
		return this.providers.getProvider(code);
	}
	
	public Provider getByName(String name) {
		return this.providers.getProviderByName(name);
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

	public List<Provider> getAllProviders(){
		return this.providers.getProviders();
	}

	@Override
	public String update(Accessible element) {
		// TODO Auto-generated method stub
		return null;
	}



}
