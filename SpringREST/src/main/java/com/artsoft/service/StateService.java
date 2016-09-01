package com.artsoft.service;

import java.util.List;

import com.artsoft.model.State;

public interface StateService {

	State findById(int id);
	
	List<State> findAll();
	
	int insert(State state);
	
	void update(State state);
	
}
