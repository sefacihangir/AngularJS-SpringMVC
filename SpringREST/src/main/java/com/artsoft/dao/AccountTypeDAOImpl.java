package com.artsoft.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.artsoft.model.AccountType;


@Repository("accountTypeDao")
public class AccountTypeDAOImpl extends AbstractDao implements AccountTypeDAO{

	@Override
	public AccountType findById(int id) {
		String sql = "SELECT at FROM AccountType at WHERE at.accountTypeId = :id";
		Query query = getSession().createQuery(sql).setParameter("id", id);
		return (AccountType) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AccountType> findAll() {
		return getSession().createQuery("FROM AccountType").list();
	}

	@Override
	public int insert(AccountType accountType) {
		int insertedAccountTypeId = (int) getSession().save(accountType);
		return insertedAccountTypeId;
	}

	@Override
	public void update(AccountType accountType) {
		getSession().update(accountType);
	}

	@Override
	public AccountType findByName(String name) {
		String sql = "SELECT at FROM AccountType at WHERE at.description = :name";
		Query query = getSession().createQuery(sql).setParameter("name", name);
		return (AccountType) query.uniqueResult();
	}

}
