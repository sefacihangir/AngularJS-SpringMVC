package com.artsoft.dao;

import java.util.List;

import com.artsoft.model.State;

public interface StateDAO {
	
	State findById(int id);
	
	List<State> findAll();
	
	int insert(State state);
	
	void update(State state);
	
}
