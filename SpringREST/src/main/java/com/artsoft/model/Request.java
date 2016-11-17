package com.artsoft.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="request")
public class Request implements Serializable{

	private static final long serialVersionUID = -7168529237607679101L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="request_id")
	private int requestId;
	
	@ManyToOne(targetEntity=AppUser.class)
	@JoinColumn(name="app_user_id")
	@JsonBackReference
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private AppUser appuser;
	
	@Column(name="request_date")
	private Date requestDate;
	
	@Column(name="desired_date")
	private Date desiredDate;
	
	@Column(name="desired_hour")
	private String desiredHour;
	
	@ManyToOne(targetEntity=AppUser.class)
	@JoinColumn(name="provider_id")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private AppUser provider;
	
	@ManyToOne(targetEntity=ProviderService.class)
	@JoinColumn(name="provider_service_id")
	private ProviderService providerService;
	
	@Column(name="total_cost")
	private double totalCost;

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}


	public AppUser getAppuser() {
		return appuser;
	}

	public void setAppuser(AppUser appuser) {
		this.appuser = appuser;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getDesiredDate() {
		return desiredDate;
	}

	public void setDesiredDate(Date desiredDate) {
		this.desiredDate = desiredDate;
	}

	public String getDesiredHour() {
		return desiredHour;
	}

	public void setDesiredHour(String desiredHour) {
		this.desiredHour = desiredHour;
	}

	public AppUser getProvider() {
		return provider;
	}

	public void setProvider(AppUser provider) {
		this.provider = provider;
	}

	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	
	
	
	
}
