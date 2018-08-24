package com.demo.rest;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.demo.rest.controller.Demo;
import com.demo.rest.service.CustomerServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomerTest {

	private MockMvc mockMvc;

	@InjectMocks
	private Demo demo;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(demo).build();
	}

	@Mock
	CustomerServiceImpl customerService;

	@Test
	public void testViewCustomer() throws Exception {
		mockMvc.perform(get("/customers")).andExpect(status().isOk());
				/* .andExpect(content().contentType(MediaType.APPLICATION_JSON)) 
				.andExpect(jsonPath("$.customerId").value(101))
				.andExpect(jsonPath("$.customerName").value("Drishti Rao"));*/

	}
	
	@Test
	public void testViewCustomerBetween() throws Exception {
		mockMvc.perform(get("/customers/1/2")).andExpect(status().isOk());
				/* .andExpect(content().contentType(MediaType.APPLICATION_JSON)) */
				/*.andExpect(jsonPath("$.customerId").value(101))
				.andExpect(jsonPath("$.customerName").value("Drishti Rao"));*/

	}

}
