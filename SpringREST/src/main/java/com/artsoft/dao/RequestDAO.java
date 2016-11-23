package com.artsoft.dao;

import java.util.List;

import com.artsoft.model.AppUser;
import com.artsoft.model.Request;

public interface RequestDAO {
	
	int insert(Request request);
	
	void update(Request request);
	
	List<Request> findAllForProvider(AppUser provider);
	
	List<Request> findAllForCustomer(AppUser customer);
	
	Request findById(int id);
	
	public void delete(Request request);
	
}
