package com.artsoft.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
<<<<<<< HEAD
import javax.persistence.FetchType;
=======
>>>>>>> 1b1be3ee9c8334b32e7db56685322da94ddd72f3
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

<<<<<<< HEAD

=======
>>>>>>> 1b1be3ee9c8334b32e7db56685322da94ddd72f3
@Entity
@Table(name="service")
public class Service implements Serializable{

	private static final long serialVersionUID = -3970304441604166862L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="service_id")
	private int serviceId;
	
<<<<<<< HEAD
	@ManyToOne(fetch = FetchType.LAZY)
=======
	@ManyToOne(targetEntity=Category.class)
>>>>>>> 1b1be3ee9c8334b32e7db56685322da94ddd72f3
	@JoinColumn(name="category_id")
	private Category category;
	
	@Column(name="service_name")
	private String serviceName;

<<<<<<< HEAD
=======
	
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
	
	
	
>>>>>>> 1b1be3ee9c8334b32e7db56685322da94ddd72f3
}
