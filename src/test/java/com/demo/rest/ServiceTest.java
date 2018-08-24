package com.demo.rest;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.rest.dao.CustomerDAO;
import com.demo.rest.pojo.Customer;
import com.demo.rest.service.CustomerServiceImpl;


public class ServiceTest {

//	private MockMvc mockMvc;
	private CustomerServiceImpl customerService= new CustomerServiceImpl();
//	@InjectMocks
//	private Demo demo;

//	@Before
//	public void setUp() throws Exception {
//		mockMvc = MockMvcBuilders.standaloneSetup(demo).build();
//		
//	}
/*
	@Mock
	CustomerServiceImpl customerService;*/
	@Autowired
	private CustomerDAO dao;

	@SuppressWarnings("unchecked")
	@Test
	public void viewAll() {

		List<Customer> list = customerService.viewAllCustomers();
		System.out.println(list + "is list" + customerService.viewAllCustomers() + "is method" + customerService + "customerService" );
		List<Customer> newlist = (List<Customer>) new Customer(101, "Drishti Rao", 98696666);
		Assert.assertEquals(newlist, list);
		
	}

}