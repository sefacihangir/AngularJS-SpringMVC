package com.artsoft.dao;

import java.util.List;

import com.artsoft.model.AccountType;

public interface AccountTypeDAO {
	
	AccountType findById(int id);
	
	List<AccountType> findAll();
	
	int insert(AccountType accountType);
	
	void update(AccountType accountType);
	
}
