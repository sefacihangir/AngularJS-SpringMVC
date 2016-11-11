package com.artsoft.service;

import java.util.List;

import com.artsoft.model.Account;

public interface AccountService {
	
	Account findById(int id);
	
	List<Account> findAll();
	
	int insert(Account account);
	
	void update(Account account);
	
}
