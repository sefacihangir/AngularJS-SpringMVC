package com.artsoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artsoft.dao.AccountTypeDAO;
import com.artsoft.model.AccountType;

@Service("accountTypeService")
@Transactional
public class AccountTypeServiceImpl implements AccountTypeService{

	@Autowired
	AccountTypeDAO accountTypeDao;
	
	@Override
	public AccountType findById(int id) {
		return accountTypeDao.findById(id);
	}

	@Override
	public AccountType findByName(String name) {
		return accountTypeDao.findByName(name);
	}

	@Override
	public List<AccountType> findAll() {
		return accountTypeDao.findAll();
	}

	@Override
	public int insert(AccountType accountType) {
		return accountTypeDao.insert(accountType);
	}

	@Override
	public void update(AccountType accountType) {
		accountTypeDao.update(accountType);
	}

}
