package com.artsoft.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.artsoft.model.Service;

@Repository("serviceDao")
public class ServiceDAOImpl extends AbstractDao implements ServiceDAO{

	@Override
	public Service findById(int id) {
		String sql = "SELECT s FROM Service s WHERE s.serviceId = :id";
		Query query = getSession().createQuery(sql).setParameter("id", id);
		return (Service) query.uniqueResult();
	}

	@Override
	public Service findByName(String name) {
		String sql = "SELECT s FROM Service s WHERE s.serviceName LIKE :name";
		Query query = getSession().createQuery(sql).setParameter("name", "%" + name + "%");
		return (Service) query.uniqueResult();
	}

	@Override
	public int insert(Service service) {
		int insertedServiceId = (int) getSession().save(service);
		return insertedServiceId;
	}

	@Override
	public void update(Service service) {
		getSession().update(service);
	}

}
