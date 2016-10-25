package com.artsoft.dao;

import com.artsoft.model.Request;

public class RequestDAOImpl extends AbstractDao implements RequestDAO{

	@Override
	public int insert(Request request) {
		int insertedRequestId = (int) getSession().save(request);
		return insertedRequestId;
	}

	@Override
	public void update(Request request) {
		getSession().update(request);
	}

}
