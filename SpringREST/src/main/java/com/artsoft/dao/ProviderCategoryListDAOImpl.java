package com.artsoft.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.artsoft.model.ProviderCategoryList;


@Repository("providerCategoryListDao")
public class ProviderCategoryListDAOImpl extends AbstractDao implements ProviderCategoryListDAO{

	@Override
	public ProviderCategoryList findByProviderId(int id) {
//		String sql = "SELECT pcl FROM ProviderCategoryList pcl WHERE pcl.provider = :id";
//		Query query = getSession().createQuery(sql).setParameter("id", id);
//		return (ProviderCategoryList) query.uniqueResult();
		return null;
	}

	@Override
	public int insert(ProviderCategoryList providerCategoryList) {
		String listNameUpperCase = providerCategoryList.getListName().toUpperCase();
		providerCategoryList.setListName(listNameUpperCase);
		int insertedProviderCategoryListId = (int) getSession().save(providerCategoryList);
		return insertedProviderCategoryListId;
	}

	@Override
	public void update(ProviderCategoryList providerCategoryList) {
		getSession().update(providerCategoryList);
	}

	@Override
	public boolean findListNameAvailability(ProviderCategoryList providerCategoryList) {
		Criteria criteria = getSession().createCriteria(ProviderCategoryList.class);
		Criterion listNameCriterion = Restrictions.eq("listName", providerCategoryList.getListName());
		Criterion providerId = Restrictions.eq("provider", providerCategoryList.getProvider());
		criteria.add(listNameCriterion);
		criteria.add(providerId);
		criteria.setProjection(Projections.rowCount());
		long count = (Long) criteria.uniqueResult();
		return count != 0 ?  false: true;
	}

	@Override
	public ProviderCategoryList findByProviderCategoryListId(int id) {
		String sql = "SELECT pcl FROM ProviderCategoryList pcl WHERE pcl.providerCategoryListId = :id";
		Query query = getSession().createQuery(sql).setParameter("id", id);
		return (ProviderCategoryList) query.uniqueResult();
	}

}
