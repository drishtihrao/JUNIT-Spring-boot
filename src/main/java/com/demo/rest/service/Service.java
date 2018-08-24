package com.demo.rest.service;

import java.util.List;

import com.demo.rest.pojo.Customer;

public interface Service {
	void addCustomer(Customer customer);

	List<Customer> viewAllCustomers();

	void updateCustomer(Customer customer);

	Customer getCustomerById(int customerId);

	void deleteCustomer(int customerId);
}
