package com.artsoft.service;

import java.util.List;

import com.artsoft.model.AccountType;

public interface AccountTypeService {

	AccountType findById(int id);

	AccountType findByName(String name);

	List<AccountType> findAll();

	int insert(AccountType accountType);

	void update(AccountType accountType);

}
