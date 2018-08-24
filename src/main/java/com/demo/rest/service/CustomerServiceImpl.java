package com.demo.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.rest.dao.CustomerDAO;
import com.demo.rest.pojo.Customer;

@Component
public class CustomerServiceImpl implements Service {

	@Autowired
	private CustomerDAO dao; //= new DaoImpl();

	public CustomerServiceImpl() {
		System.out.println(dao);

//		service.addCustomer(new Customer(105, "Mehek T", 72015456));
//		service.addCustomer(new Customer(106, "Lokesh U", 87642025));
	}
	@Override
	public void addCustomer(Customer customer) {
		dao.save(customer);

	}

	@Override
	public List<Customer> viewAllCustomers() {
		System.out.println(dao.findAll());
		return dao.findAll();
	}

	@Override
	public void updateCustomer(Customer customer) {
		dao.save(customer);
		
	}

	@Override
	public void deleteCustomer(int customerId) {
		dao.deleteById(customerId);
		
	}

	@Override
	public Customer getCustomerById(int customerId)  {
		return dao.findById(customerId).get();
	}

}
