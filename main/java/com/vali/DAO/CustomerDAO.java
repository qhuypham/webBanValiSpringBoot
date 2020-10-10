package com.vali.DAO;

import java.util.List;

import com.vali.entity.Customer;

public interface CustomerDAO {

	Customer findById(String id);
	List<Customer> findAll();
	Customer create(Customer entity);
	void update(Customer entity);
	Customer delete(String id);
}
