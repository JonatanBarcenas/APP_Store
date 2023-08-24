package model;

import intefaces.Accessible;

public class Sale implements Accessible{
	
	private String folio;
	private String date;
	private Double total;
	
	public Sale() {
		// TODO Auto-generated constructor stub
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

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return folio;
	}
	
	

}
