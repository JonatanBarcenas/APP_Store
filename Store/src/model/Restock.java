package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import intefaces.Accessible;


public class Restock implements Accessible {
	
	private String folio;
	private String date;
	private Provider provider;
	private double total;
	
	
	public Restock() {
		
	
	}
	
	
	
	

	public String getFolio() {
		return folio;
	}





	public void setFolio(String folio) {
		this.folio = folio;
	}





	public String getDate() {
		return date;
	}





	public void setDate(String date) {
		this.date = date;
	}





	public Provider getProvider() {
		return provider;
	}





	public void setProvider(Provider provider) {
		this.provider = provider;
	}



	public double getTotal() {
		return total;
	}





	public void setTotal(double total) {
		this.total = total;
	}





	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return folio;
	}





	@Override
	public String toString() {
		return "Restock [folio=" + folio + ", date=" + date + ", provider=" + provider + ", total=" + total + ", index="
				+"]";
	}


	
	
	
	

	
	
}
