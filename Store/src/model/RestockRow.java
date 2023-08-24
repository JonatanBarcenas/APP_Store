package model;

import java.io.Serializable;

import java.time.LocalDate;
import java.util.Date;

import intefaces.Accessible;



public class RestockRow implements Accessible {
	
	
	private String barcode;
	private int amount;
	private String name;
	private String sellByDate;
	private Double price;
	private double total;
	
	public RestockRow() {
		
	}

	
	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	

	public String getSellByDate() {
		return sellByDate;
	}

	public void setSellByDate(String date) {
		this.sellByDate = date;
	}

	public double getTotal() {
		return total;
	}
	
	

	public void setTotal(double total) {
		this.total = total;
	}
	
	

	public String getName() {
		return name;
	}


	@Override
	public String toString() {
		return "RestockRow barcode=" + barcode + ", amount=" + amount 
				+ ", sellByDate=" + sellByDate + ", total=" + total + "]";
	}
	
	

	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public void setName(String name) {
		this.name = name;
	}


	
	
	@Override
	public boolean equals(Object obj) {
		RestockRow restockRow = (RestockRow) obj;
		return restockRow.getId().equals(barcode);
	}


	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return barcode;
	}
	
	

}
