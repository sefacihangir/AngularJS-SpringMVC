package com.artsoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artsoft.dao.AddressDAO;
import com.artsoft.model.Address;


@Service("addressService")
@Transactional
public class AddressServiceImpl implements AddressService{

	@Autowired
	AddressDAO addressDao;
	
	
	@Override
	public int insert(Address address) {
		return addressDao.insert(address);
	}

}
