package com.artsoft.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.artsoft.model.ProviderCategoryList;


@Repository("providerCategoryListDao")
public class ProviderCategoryListDAOImpl extends AbstractDao implements ProviderCategoryListDAO{

	@Override
	public ProviderCategoryList findByProviderId(int id) {
		String sql = "SELECT pcl FROM ProviderCategoryList pcl WHERE pcl.providerCategoryListId = :id";
		Query query = getSession().createQuery(sql).setParameter("id", id);
		return (ProviderCategoryList) query.uniqueResult();
	}

	@Override
	public int insert(ProviderCategoryList providerCategoryList) {
		int insertedProviderCategoryListId = (int) getSession().save(providerCategoryList);
		return insertedProviderCategoryListId;
	}

	@Override
	public void update(ProviderCategoryList providerCategoryList) {
		getSession().update(providerCategoryList);
	}

}
