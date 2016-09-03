package com.artsoft.dao;

import java.util.List;

import com.artsoft.model.Account;

public interface AccountDAO {
	
	Account findById(int id);
	
	List<Account> findAll();
	
	int insert(Account account);
	
	void update(Account account);
	
}
