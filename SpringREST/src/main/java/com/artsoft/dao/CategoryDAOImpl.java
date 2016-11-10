package com.artsoft.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.artsoft.model.AppUser;
import com.artsoft.model.Category;

@Repository("categoryDao")
public class CategoryDAOImpl extends AbstractDao implements CategoryDAO{

	@Override
	public Category findById(int id) {
		String sql = "SELECT c FROM Category c WHERE c.categoryId = :id";
		Query query = getSession().createQuery(sql).setParameter("id", id);
		return (Category) query.uniqueResult();
	}

	@Override
	public Category findByName(String name) {
		String sql = "SELECT c FROM Category c WHERE c.categoryName = :name";
		Query query = getSession().createQuery(sql).setParameter("name", name.toUpperCase());
		return (Category) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> findAll() {
		return getSession().createQuery("FROM Category").list();
	}

	@Override
	public int insert(Category category) {
		String categoryNameUpperCase = category.getCategoryName().toUpperCase();
		category.setCategoryName(categoryNameUpperCase);
		int insertedCategoryId = (int) getSession().save(category);
		return insertedCategoryId;
	}

	@Override
	public void update(Category category) {
		String categoryNameUpperCase = category.getCategoryName().toUpperCase();
		category.setCategoryName(categoryNameUpperCase);
		getSession().update(category);
	}

	@Override
	public boolean findCategoryNameAvailability(Category category) {
		Criteria criteria = getSession().createCriteria(Category.class);
		Criterion categoryId = Restrictions.ne("categoryId", category.getCategoryId());
		Criterion categoryName = Restrictions.eq("categoryName", category.getCategoryName().toUpperCase());
		LogicalExpression andExp = Restrictions.and(categoryId, categoryName);
		criteria.add(andExp);
		criteria.setProjection(Projections.rowCount());
		long count = (Long) criteria.uniqueResult();
		return count != 0 ?  false : true;
	}

	@Override
	public void delete(Category category) {
		getSession().delete(category);
	}

	@Override
	public boolean findCategoryAvailability(Category category) {
		Criteria criteria = getSession().createCriteria(Category.class);
		Criterion categoryId = Restrictions.eq("categoryId", category.getCategoryId());
		criteria.add(categoryId);
		long count = (Long) criteria.uniqueResult();
		return count != 0 ? true : false;
	}

}
