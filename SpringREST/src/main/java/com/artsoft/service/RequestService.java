package com.artsoft.service;

import java.util.List;

import com.artsoft.model.Request;

public interface RequestService {

	int insert(Request request);
	
	void update(Request request);
	
	List<Request> findAllFor(int id, String role);
	
}
