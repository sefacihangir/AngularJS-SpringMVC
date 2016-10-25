package com.artsoft.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="provider_service")
public class ProviderService implements Serializable{

	private static final long serialVersionUID = 8950337127945994494L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="provider_service_id")
	private int providerServiceId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="provider_category_list_id")
	private ProviderCategoryList providerCategoryList;
	
	@Column(name="price")
	private double price;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="service_id")
	private Service service;
	
	@Column(name="available")
	private int available;

	public int getProviderServiceId() {
		return providerServiceId;
	}

	public void setProviderServiceId(int providerServiceId) {
		this.providerServiceId = providerServiceId;
	}

	public ProviderCategoryList getProviderCategoryList() {
		return providerCategoryList;
	}

	public void setProviderCategoryList(ProviderCategoryList providerCategoryList) {
		this.providerCategoryList = providerCategoryList;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}
	
	
	
}
