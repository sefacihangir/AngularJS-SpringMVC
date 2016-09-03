package com.artsoft.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="account_type")
public class AccountType implements Serializable{

	private static final long serialVersionUID = 9109027184049098899L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="account_type_id")
	private int accountTypeId;
	
	@Column(name="description")
	private String description;

	public int getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(int accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
