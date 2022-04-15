package com.FinalProject.ChrisCosmetic.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;
	
	@Column
	private String productName;
	
	@Column
	private String productImage;	
	
	@Column
	private String productShortDesc;
	
	@Column
	private String productDetailDesc;
	
	@ManyToOne
	@JoinColumn(name = "sub_category_id")
	private SubCategory subCategory;
	
	@Column(name = "price")
	private int price;
	
	@Column(name = "quantity")
	private int quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getProductShortDesc() {
		return productShortDesc;
	}

	public void setProductShortDesc(String productShortDesc) {
		this.productShortDesc = productShortDesc;
	}

	public String getProductDetailDesc() {
		return productDetailDesc;
	}

	public void setProductDetailDesc(String productDetailDesc) {
		this.productDetailDesc = productDetailDesc;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
