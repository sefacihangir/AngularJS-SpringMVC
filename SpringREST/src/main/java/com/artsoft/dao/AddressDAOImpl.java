package com.artsoft.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.artsoft.model.Address;

@Repository("addressDao")
public class AddressDAOImpl extends AbstractDao implements AddressDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> findAllByUserId(int id) {
		String sql = "SELECT a FROM Address a WHERE a.appUserId = :id";
		Query query = getSession().createQuery(sql).setParameter("id",id);
		return query.list();
	}

	@Override
	public int insert(Address address) {
		int insertedAddressId = (int) getSession().save(address);
		return insertedAddressId;
	}

	@Override
	public void update(Address address) {
		getSession().update(address);
	}

}
