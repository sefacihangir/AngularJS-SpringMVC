package com.artsoft.dao;

import java.util.List;

import com.artsoft.model.Address;

public interface AddressDAO {
	
	List<Address> findAllByUserId(int id);
	
	int insert(Address address);
	
	void update(Address address);
	
}
