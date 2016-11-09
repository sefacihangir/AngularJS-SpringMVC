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
@Table(name="service")
public class Service implements Serializable{

	private static final long serialVersionUID = -3970304441604166862L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="service_id")
	private int serviceId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="category_id")
	@JsonBackReference
	private Category category;
	
	@Column(name="service_name")
	private String serviceName;

	
	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	

}
