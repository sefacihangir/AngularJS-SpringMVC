package com.artsoft.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

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
		String sql = "SELECT c FROM Category c WHERE c.categoryName LIKE :name";
		Query query = getSession().createQuery(sql).setParameter("name", "%" + name + "%");
		return (Category) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> findAll() {
		return getSession().createQuery("FROM Category").list();
	}

	@Override
	public int insert(Category category) {
		int insertedCategoryId = (int) getSession().save(category);
		return insertedCategoryId;
	}

	@Override
	public void update(Category category) {
		getSession().update(category);
	}

}
