package com.artsoft.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


import com.artsoft.model.ServiceModel;

@Repository("serviceDao")
public class ServiceDAOImpl extends AbstractDao implements ServiceDAO{

	@Override
	public ServiceModel findById(int id) {
		String sql = "SELECT s FROM ServiceModel s WHERE s.serviceId = :id";
		Query query = getSession().createQuery(sql).setParameter("id", id);
		return (ServiceModel) query.uniqueResult();
	}

	@Override
	public ServiceModel findByName(String name) {
		String sql = "SELECT s FROM ServiceModel s WHERE s.serviceName = :name";
		Query query = getSession().createQuery(sql).setParameter("name", name.toUpperCase());
		return (ServiceModel) query.uniqueResult();
	}

	@Override
	public int insert(ServiceModel service) {
		String serviceNameUpperCase = service.getServiceName().toUpperCase();
		service.setServiceName(serviceNameUpperCase);
		int insertedServiceId = (int) getSession().save(service);
		return insertedServiceId;
	}

	@Override
	public void update(ServiceModel service) {
		String serviceNameUpperCase = service.getServiceName().toUpperCase();
		service.setServiceName(serviceNameUpperCase);
		getSession().update(service);
	}

	@Override
	public void delete(ServiceModel service) {
		getSession().delete(service);
	}

	@Override
	public boolean findServiceNameAvailability(ServiceModel service) {
		Criteria criteria = getSession().createCriteria(ServiceModel.class);
		Criterion serviceId = Restrictions.ne("serviceId", service.getServiceId());
		Criterion serviceName = Restrictions.eq("serviceName", service.getServiceName().toUpperCase());
		Criterion categoryId = Restrictions.eq("category", service.getCategory());
		criteria.add(serviceId);
		criteria.add(serviceName);
		criteria.add(categoryId);
		criteria.setProjection(Projections.rowCount());
		long count = (Long) criteria.uniqueResult();
		return count != 0 ?  false : true;
	}

}
