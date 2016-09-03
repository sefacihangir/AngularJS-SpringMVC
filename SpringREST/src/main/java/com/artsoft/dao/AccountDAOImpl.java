package com.artsoft.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.artsoft.model.Account;

@Repository("accountDao")
public class AccountDAOImpl extends AbstractDao implements AccountDAO{

	@Override
	public Account findById(int id) {
		String sql = "SELECT a FROM Account a WHERE a.accountId = :id";
		Query query = getSession().createQuery(sql).setParameter("id", id);
		return (Account) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> findAll() {
		return getSession().createQuery("FROM Account").list();
	}

	@Override
	public int insert(Account account) {
		int insertedAccountId = (int) getSession().save(account);
		return insertedAccountId;
	}

	@Override
	public void update(Account account) {
		getSession().update(account);
	}

}
