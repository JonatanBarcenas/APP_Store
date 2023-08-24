package model;

import java.io.Serializable;

import intefaces.Accessible;



public class Provider implements Accessible {

	private String id;
	private String name;
	private String phone;
	
	public Provider() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getPhone() {
		return phone;
	}




	public void setPhone(String phone) {
		this.phone = phone;
	}




	
	
	@Override
	public boolean equals(Object obj) {
		Provider provider = (Provider) obj;
		return provider.getName().toLowerCase().equals(name.toLowerCase());
	}



	@Override
	public String toString() {
		return "Provider [id=" + id + ", name=" + name + ", phone=" + phone + "]";
	}
	
	

}
