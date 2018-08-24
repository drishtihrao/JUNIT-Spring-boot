package com.demo.rest.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.rest.pojo.Customer;
import com.demo.rest.service.CustomerServiceImpl;

@RestController
public class Demo {

	@Autowired
	private CustomerServiceImpl service; // = new ServiceImpl();

	@RequestMapping(value = "/customer/add", method = RequestMethod.POST/* , consumes = "application/json" */)
	public void addCustomer(@RequestBody Customer customer) {
		System.out.println(customer);
		service.addCustomer(customer);
	}
	
	@RequestMapping(value = "/customers", method = RequestMethod.GET/* , produces = MediaType.ALL_VALUE */)
	public List<Customer> viewAllCustomers() {
/*		for (Customer customer : service.viewAllCustomers()) {

			Link link = ControllerLinkBuilder
					.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).deleteCustomer(customer.getCustomerId()))
					.withSelfRel();
			 Resource resource = new Resource(customer, link); 
			customer.add(link);

		}*/
		return service.viewAllCustomers();
	}

	@RequestMapping(value = "/customers/{start}/{count}", method = RequestMethod.GET/*
																					 * , produces = MediaType.ALL_VALUE
																					 */)
	public Resources viewAllCustomersBetween(@PathVariable("start") int start, @PathVariable("count") int count) {
		System.out.println(viewAllCustomers().size() + "is the size of list in next method");
		List<Customer> tempCustomers = viewAllCustomers();
		int listLength = tempCustomers.size();
		List<Customer> customers = new ArrayList<>();

		System.out.println(start + "start " + count + "count " + listLength + "listlength  *******");

		for (int i = start - 1; i < start + count - 1; i++) {
			customers.add(tempCustomers.get(i));
		}
		/*start + count <= listLength ? start + count : listLength - count + 1, count*/

		Link previousLink = linkTo(
				methodOn(this.getClass()).viewAllCustomersBetween(start - count > 0 ? start - count : 1, count))
						.withRel("Previous");
		Link nextLink = linkTo(methodOn(this.getClass())
				.viewAllCustomersBetween(start + count > listLength ?listLength - count + 1: start+count,  count))
						.withRel("Next");

		return new Resources<>(customers, previousLink, nextLink);
	}

	@RequestMapping(value = "/customer/update", method = RequestMethod.PUT/* , consumes = "application/json" */)
	public void updateCustomer(int id, @RequestBody Customer customer) {
		System.out.println(customer);
		service.updateCustomer(customer);
	}

	@RequestMapping(value = "/customer/delete", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteCustomer(int customerId) {
		service.deleteCustomer(customerId);
		return new ResponseEntity<String>("Entity Deleted", HttpStatus.OK);
	}

	@RequestMapping(value = "/customer/searchById", method = RequestMethod.GET)
	public ResponseEntity<Customer> getCustomerById(int customerId) {
		Customer cust = null;
		Resource<Customer> resource = null;

		for (Customer customer : service.viewAllCustomers()) {
			if (customer.getCustomerId() == customerId) {
				resource = new Resource<Customer>(customer);
				cust = (Customer) customer;
				// Link link = new Link("http://localhost:8080/customers");
				// ControllerLinkBuilder link =
				// ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).viewAllCustomers());
				// resource.add(link.withRel("All Customers"));

				Link link = ControllerLinkBuilder
						.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).deleteCustomer(customerId))
						.withSelfRel();
				cust.add(link);
				
				break;

			}
		}

		if (cust == null) {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<Customer>(cust, HttpStatus.OK);

	}

}
