package com.springboot.h2.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// @Entity annotation specifies that the class is mapped to a database table.
@Entity
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// @Id annotation specifies the primary key of an entity.
	// @GeneratedValue provides the generation strategy specification for the primary key values.
	@Id
	@GeneratedValue
	private int productId;
	private String productName;
	private double price;
	private String productDesc;
	private int isHidden;

	// Default constructor.
	public Product() {	}

	// Parameterized constructor.
	public Product(int productId, String productName, double price, String productDesc, int isHidden) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.productDesc = productDesc;
		this.isHidden = isHidden;
	}

	
	
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public int getIsHiden() {
		return isHidden;
	}

	public void setIsHiden(int isHiden) {
		this.isHidden = isHiden;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", price=" + price
				+ ", productDesc=" + productDesc + ", isHidden=" + isHidden + "]";
	}


		
	}
