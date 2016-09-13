package com.artsoft.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category implements Serializable{

	private static final long serialVersionUID = -3540078231135721843L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="category_id")
<<<<<<< HEAD
	private int categoryId;
=======
	private int categoryId;	
>>>>>>> 1b1be3ee9c8334b32e7db56685322da94ddd72f3
	
	@Column(name="category_name")
	private String categoryName;
	
	@ManyToOne(targetEntity=Icon.class)
	@JoinColumn(name="icon_id")
	private Icon icon;

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
	
	
	
<<<<<<< HEAD
	
=======
>>>>>>> 1b1be3ee9c8334b32e7db56685322da94ddd72f3
}
