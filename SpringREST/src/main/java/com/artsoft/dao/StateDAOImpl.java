package com.artsoft.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.artsoft.model.State;

@Repository("stateDao")
public class StateDAOImpl extends AbstractDao implements StateDAO {

	@Override
	public State findById(int id) {
		String sql = "SELECT s FROM State s WHERE s.stateId = :id";
		Query query = getSession().createQuery(sql).setParameter("stateId", id);
		return (State) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<State> findAll() {
		return getSession().createQuery("FROM State").list();
	}

	@Override
	public int insert(State state) {
		int insertedStateId = (int) getSession().save(state);
		return insertedStateId;
	}

	@Override
	public void update(State state) {
		getSession().update(state);
	}

}
