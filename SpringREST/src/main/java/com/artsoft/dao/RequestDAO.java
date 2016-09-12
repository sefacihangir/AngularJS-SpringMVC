package com.artsoft.dao;

import com.artsoft.model.Request;

public interface RequestDAO {
	
	int insert(Request request);
	
	void update(Request request);
	
}
