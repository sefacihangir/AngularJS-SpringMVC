package com.artsoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artsoft.dao.StateDAO;
import com.artsoft.model.State;

@Service("stateService")
@Transactional
public class StateServiceImpl implements StateService{
	
	@Autowired
	StateDAO stateDao;
	
	@Override
	public State findById(int id) {
		return stateDao.findById(id);
	}

	@Override
	public List<State> findAll() {
		return stateDao.findAll();
	}

	@Override
	public int insert(State state) {
		return stateDao.insert(state);
	}

	@Override
	public void update(State state) {
		stateDao.update(state);
	}

}
