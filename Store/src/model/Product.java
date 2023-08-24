package model;

import intefaces.Accessible;

public class Product implements Comparable<Product>, Accessible {
	
	private String barcode;
	private String name;
	private String brand;
	private String type;
	private String content;
	private String measureUnit;
	private String presentation;
	private String description;
	private Double price;
	private Integer stockMax;
	private Integer stockMin;
	private Integer onInventory;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(String barcode, String name, String brand, String type, String content, String measureUnit,
			String presentation, String description, Double price, Integer stockMax, Integer stockMin) {
		super();
		this.barcode = barcode;
		this.name = name;
		this.brand = brand;
		this.type = type;
		this.content = content;
		this.measureUnit = measureUnit;
		this.presentation = presentation;
		this.description = description;
		this.price = price;
		this.stockMax = stockMax;
		this.stockMin = stockMin;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMeasureUnit() {
		return measureUnit;
	}

	public void setMeasureUnit(String measureUnit) {
		this.measureUnit = measureUnit;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}
	

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStockMax() {
		return stockMax;
	}

	public void setStockMax(Integer stockMax) {
		this.stockMax = stockMax;
	}

	public Integer getStockMin() {
		return stockMin;
	}

	public void setStockMin(Integer stockMin) {
		this.stockMin = stockMin;
	}

	public Integer getOnInventory() {
		return onInventory;
	}

	public void setOnInventory(Integer onInventory) {
		this.onInventory = onInventory;
	}

	@Override
	public String toString() {
		return "Product [barcode=" + barcode + ", name=" + name + ", brand=" + brand + ", type=" + type + ", content="
				+ content + ", measureUnit=" + measureUnit + ", presentation=" + presentation + ", description="
				+ description + ", price=" + price + ", stockMax=" + stockMax + ", stockMin=" + stockMin
				+ ", onInventory=" + onInventory + "]";
	}

	@Override
	public boolean equals(Object obj) {
		Product product = (Product) obj;
		return product.getBarcode().equals(this.barcode);
	}

	@Override
	public int compareTo(Product o) {
		
		return barcode.compareTo(o.getBarcode());
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return barcode;
	}
	
	
	

}
