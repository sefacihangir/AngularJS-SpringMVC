package com.artsoft.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;



@Entity
@Table(name="account")
@DynamicUpdate(value=true)
public class Account implements Serializable{

	private static final long serialVersionUID = 6943006466100266187L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="account_id")
	private int accountId;
	
	@ManyToOne(targetEntity=AccountType.class)
	@JoinColumn(name="account_type_id")
	private AccountType accountType;
	
	@ManyToOne(targetEntity=State.class)
	@JoinColumn(name="state_id")
	private State state;
	
	@Column(name="start_date")
	private Timestamp startDate;
	
	@Column(name="end_date")
	private Timestamp endDate;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

}
