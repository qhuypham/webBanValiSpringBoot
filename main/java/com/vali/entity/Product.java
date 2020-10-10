package com.vali.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity @Table(name = "Products")
public class Product {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Double unitPrice;
	private String image;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date productDate;
	private boolean Available;
	private Integer quantity;
	private String description;
	private Double discount;
	private Integer viewCount;
	private boolean special;

	@ManyToOne
	@JoinColumn(name = "categoryId")
	Category category;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<OrderDetail> orderDetails;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public boolean isAvailable() {
		return Available;
	}

	public void setAvailable(boolean available) {
		Available = available;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public boolean isSpecial() {
		return special;
	}

	public void setSpecial(boolean special) {
		this.special = special;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	
}
