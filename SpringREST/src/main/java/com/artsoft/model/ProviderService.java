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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="provider_service")
public class ProviderService implements Serializable{

	private static final long serialVersionUID = 8950337127945994494L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="provider_service_id")
	private int providerServiceId;
	
	@ManyToOne(targetEntity = ProviderCategoryList.class)
	@JoinColumn(name="provider_category_list_id")
	@JsonBackReference
	private ProviderCategoryList providerCategoryList;
	
	@Column(name="price")
	private double price;
	
	@ManyToOne(targetEntity = ServiceModel.class)
	@JoinColumn(name="service_id")
	private ServiceModel service;
	
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

	public ServiceModel getService() {
		return service;
	}

	public void setService(ServiceModel service) {
		this.service = service;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}
	
	
	
}
