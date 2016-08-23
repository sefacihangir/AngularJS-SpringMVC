package com.artsoft.model;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = -5186343863817997390L;
	
	private long 	userId;
	private String 	firstName;
	private String 	lastName;
	private int 	age;
	
	
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
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
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	
	@Override
	public String toString(){
		return "User[ id: " + userId + ", firstName: " + firstName + ", lastName: " + lastName + ", age: " + age;
	}
	
}
