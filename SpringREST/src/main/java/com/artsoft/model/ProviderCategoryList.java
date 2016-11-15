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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="provider_category_list")
public class ProviderCategoryList implements Serializable{

	private static final long serialVersionUID = 5801128644018924023L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="provider_category_list_id")
	private int providerCategoryListId;
	
	@ManyToOne(targetEntity=AppUser.class)
	@JoinColumn(name="provider_id")
	private AppUser provider;
	
	@ManyToOne(targetEntity=Category.class)
	@JoinColumn(name="category_id")
	private Category category;
	
	@Column(name="list_name")
	private String listName;

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "providerCategoryList" ,targetEntity=ProviderService.class, cascade = CascadeType.ALL)
	@JsonManagedReference
	Set<ProviderService> providerServices = new HashSet<ProviderService>(0);
	
	
	public int getProviderCategoryListId() {
		return providerCategoryListId;
	}

	public void setProviderCategoryListId(int providerCategoryListId) {
		this.providerCategoryListId = providerCategoryListId;
	}

	public AppUser getProvider() {
		return provider;
	}

	public void setProvider(AppUser provider) {
		this.provider = provider;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public Set<ProviderService> getProviderServices() {
		return providerServices;
	}

	public void setProviderServices(Set<ProviderService> providerServices) {
		this.providerServices = providerServices;
	}
	
	
	

}
