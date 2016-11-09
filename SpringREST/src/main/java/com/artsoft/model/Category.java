package com.artsoft.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="category")
public class Category implements Serializable{

	private static final long serialVersionUID = -3540078231135721843L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="category_id")
	private int categoryId;
	
	@Column(name="category_name")
	private String categoryName;
	
	@ManyToOne(targetEntity=Icon.class)
	@JoinColumn(name="icon_id")
	private Icon icon;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category", targetEntity = ServiceModel.class, cascade = CascadeType.ALL)
	@JsonManagedReference
	Set<ServiceModel> services = new HashSet<ServiceModel>(0);
	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public Set<ServiceModel> getServices() {
		return services;
	}

	public void setServices(Set<ServiceModel> services) {
		this.services = services;
	}
	
	

}
