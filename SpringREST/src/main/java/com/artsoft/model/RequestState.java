package com.artsoft.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="request_state")
public class RequestState implements Serializable{

	
	private static final long serialVersionUID = -700117774189627643L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="request_state_id")
	private int requestStateId;
	
	
	@Column(name="description")
	private String description;


	public int getRequestStateId() {
		return requestStateId;
	}


	public void setRequestStateId(int requestStateId) {
		this.requestStateId = requestStateId;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
}
