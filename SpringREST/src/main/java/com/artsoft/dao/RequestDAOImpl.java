package com.artsoft.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.artsoft.model.AppUser;
import com.artsoft.model.Request;

@Repository("requestDao")
public class RequestDAOImpl extends AbstractDao implements RequestDAO {

	@Override
	public int insert(Request request) {
		int insertedRequestId = (int) getSession().save(request);
		return insertedRequestId;
	}

	@Override
	public void update(Request request) {
		getSession().update(request);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Request> findAllForProvider(AppUser provider) {
		Criteria criteria = getSession().createCriteria(Request.class);
		Criterion providerId = Restrictions.eq("provider", provider);
		criteria.add(providerId);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Request> findAllForCustomer(AppUser customer) {
		Criteria criteria = getSession().createCriteria(Request.class);
		Criterion customerId = Restrictions.eq("appuser", customer);
		criteria.add(customerId);
		return criteria.list();
	}


}
