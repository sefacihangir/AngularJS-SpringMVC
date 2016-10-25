package com.artsoft.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="state")
public class State implements Serializable{

	private static final long serialVersionUID = 4746451131337463744L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="state_id")
	private int stateId;
	
	@Column(name="description")
	private String description;

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}
