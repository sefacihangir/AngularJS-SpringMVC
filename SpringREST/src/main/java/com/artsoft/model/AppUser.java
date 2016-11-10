package com.artsoft.model;

import java.io.Serializable;
import java.sql.Timestamp;
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
@Table(name="appuser")
public class AppUser implements Serializable{

	private static final long serialVersionUID = -5186343863817997390L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="app_user_id")
	private int appUserId;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@ManyToOne(targetEntity=Role.class)
	@JoinColumn(name="role_id")
	private Role role;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="phone_nr")
	private String phoneNr;
	
	@Column(name="created_at")
	private Timestamp createdAt;
	
	@ManyToOne(targetEntity=Account.class, cascade = CascadeType.ALL)
	@JoinColumn(name="account_id")
	private Account account;
	
	@Column(name="last_action")
	private Timestamp lastAction;
	
	@Column(name="image_path")
	private String imagePath;

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "appuser", targetEntity = Address.class, cascade = CascadeType.ALL)
	@JsonManagedReference
	Set<Address> addresses = new HashSet<Address>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "appuser", targetEntity = Request.class, cascade = CascadeType.ALL)
	@JsonManagedReference
	Set<Request> requests = new HashSet<Request>(0);
	
	
	public int getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(int appUserId) {
		this.appUserId = appUserId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNr() {
		return phoneNr;
	}

	public void setPhoneNr(String phoneNr) {
		this.phoneNr = phoneNr;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}


	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Timestamp getLastAction() {
		return lastAction;
	}

	public void setLastAction(Timestamp lastAction) {
		this.lastAction = lastAction;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public Set<Request> getRequests() {
		return requests;
	}

	public void setRequests(Set<Request> requests) {
		this.requests = requests;
	}


	public String toString(){
		String res = "";
		res += "Email-- " + this.email + "\n";
		res += "Password-- " + this.password + "\n";
		for(Address address : this.addresses){
			res += "Address--Description: " + address.getDescription() + " | County: " + address.getCounty() + "\n";
		}
		return res;
	}

	
	
}
