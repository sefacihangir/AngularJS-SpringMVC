package com.artsoft.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.artsoft.model.AppUser;
import com.artsoft.model.ProviderService;

@Repository("providerServiceDao")
public class ProviderServiceDAOImpl extends AbstractDao implements ProviderServiceDAO{

	@Override
	public int insert(ProviderService providerService) {
		int insertedProviderServiceId = (int) getSession().save(providerService);
		return insertedProviderServiceId;
	}

	@Override
	public void update(ProviderService providerService) {
		getSession().update(providerService);
	}

	@Override
	public boolean findProviderServiceAvailability(ProviderService providerService) {
		Criteria criteria = getSession().createCriteria(ProviderService.class);
		Criterion providerCateogyListId = Restrictions.eq("providerCategoryList", providerService.getProviderCategoryList());
		Criterion serviceId = Restrictions.eq("service", providerService.getService());
		criteria.add(providerCateogyListId);
		criteria.add(serviceId);
		criteria.setProjection(Projections.rowCount());
		long count = (Long) criteria.uniqueResult();
		return count != 0 ?  true : false;		// return true when the service already exists
	}

	@Override
	public void delete(ProviderService providerService) {
		getSession().delete(providerService);
	}

	@Override
	public ProviderService findById(int id) {
		String sql = "SELECT ps FROM ProviderService ps WHERE ps.providerServiceId = :id";
		Query query = getSession().createQuery(sql).setParameter("id", id);
		return (ProviderService) query.uniqueResult();
	}

	@Override
	public List<ProviderService> findByServiceId(int id) {
		Criteria criteria = getSession().createCriteria(ProviderService.class);
		return null;
	}

}
