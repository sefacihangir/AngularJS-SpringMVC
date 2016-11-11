package com.artsoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artsoft.dao.AccountDAO;
import com.artsoft.model.Account;

@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountDAO accountDao;
	
	
	@Override
	public Account findById(int id) {
		return accountDao.findById(id);
	}

	@Override
	public List<Account> findAll() {
		return accountDao.findAll();
	}

	@Override
	public int insert(Account account) {
		return accountDao.insert(account);
	}

	@Override
	public void update(Account account) {
		accountDao.update(account);
	}

}
