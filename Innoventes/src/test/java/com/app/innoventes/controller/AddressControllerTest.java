package com.app.innoventes.controller;

import static org.junit.Assert.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.app.innoventes.model.Address;
import com.app.innoventes.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;

	@WebMvcTest(AddressController.class)
	public class AddressControllerTest {
		@Autowired
		MockMvc mockMvc;
		
		@MockBean
		AddressService addressService;
		
	         @Test
			public void getAllAddressTest() throws Exception {

				Mockito.when(addressService.getAllAddress()).thenReturn(Stream.of(new Address(1,"Balaji layout","marathalli","Banglore"),
				(new Address(1,"Balaji layout","marathalli","Banglore"))).collect(Collectors.toList()));

				MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/getAllAddress");
				MvcResult result = mockMvc.perform(mockHttpServletRequestBuilder).andReturn();
				MockHttpServletResponse response = result.getResponse();
				System.err.println(response.getContentAsString());
				int status = response.getStatus();
				assertEquals(200, status);
			}
			
			@Test
			public void getAddressByIdTest() throws Exception {

				Mockito.when(addressService.getAddressById(1)).thenReturn(new Address(1,"Balaji layout","marathalli","Banglore"));

				MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/getAddressById/1");
				MvcResult result = mockMvc.perform(mockHttpServletRequestBuilder).andReturn();
				MockHttpServletResponse response = result.getResponse();
				System.err.println(response.getContentAsString());
				int status = response.getStatus();
				assertEquals(200, status);
			}
			
			@Test
			public void deleteAddressByIdTest() throws Exception {
				
				Mockito.when(addressService.deleteAddressById(15)).thenReturn(1);

				MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.delete("/deleteAddress/15");
				MvcResult result = mockMvc.perform(mockHttpServletRequestBuilder).andReturn();
				MockHttpServletResponse response = result.getResponse();
				System.err.println(response.getContentAsString());
				int status = response.getStatus();
				assertEquals(200, status);
			}
			
			@Test
			public void editAddressTest() throws Exception {

				Mockito.when(addressService.updateAddress(new Address(1,"Balaji layout","marathalli","Banglore"))).
				thenReturn((new Address(1,"Balaji layout","marathalli","Banglore")));
				

				ObjectMapper mapper = new ObjectMapper();
				MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.put("/editAddress")
						.contentType(MediaType.APPLICATION_JSON)
		                .accept(MediaType.APPLICATION_JSON)
		                .characterEncoding("UTF-8")
		                .content(mapper.writeValueAsString(new Address(1,"Balaji layout","marathalli","Banglore")));
				MvcResult result = mockMvc.perform(mockHttpServletRequestBuilder).andReturn();
				MockHttpServletResponse response = result.getResponse();
				System.err.println("---"+response.getContentAsString());
				int status = response.getStatus();
				assertEquals(200, status);
			}
			
			@Test
			public void createAddressTest() throws Exception {
				
				Mockito.when(addressService.createAddress(Mockito.any(Address.class)))
				.thenReturn(new Address(1,"Balaji layout","marathalli","Banglore"));

				ObjectMapper mapper = new ObjectMapper();
				MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/createAddress")
						.contentType(MediaType.APPLICATION_JSON
								)
		                .accept(MediaType.APPLICATION_JSON)
		                .characterEncoding("UTF-8")
		                .content(mapper.writeValueAsString(new Address(4,"Balaji layout","marathalli","Banglore")));
				MvcResult result = mockMvc.perform(mockHttpServletRequestBuilder).andReturn();
				MockHttpServletResponse response = result.getResponse();
				System.err.println("---"+response.getContentAsString());
				int status = response.getStatus();
				assertEquals(200, status);
			}
		}


