package com.app.innoventes.controller;

import static org.junit.Assert.assertEquals;

import java.util.Date;
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

import com.app.innoventes.model.Employee;
import com.app.innoventes.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	EmployeeService employeeService;
	
         @Test
		public void getAllEmployeesTest() throws Exception {

			Mockito.when(employeeService.getAllEmployees()).thenReturn(Stream.of(new Employee(1,"Rashmiranjan",new Date()),
					new Employee(1,"Rashmiranjan",new Date())).collect(Collectors.toList()));

			MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/getAllEmployees");
			MvcResult result = mockMvc.perform(mockHttpServletRequestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			System.err.println(response.getContentAsString());
			int status = response.getStatus();
			assertEquals(200, status);
		}
		
		@Test
		public void getEmployeeByIdTest() throws Exception {

			Mockito.when(employeeService.getEmployeeById(1)).thenReturn(new Employee(1,"Rashmiranjan",new Date()));

			MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/getEmployeeById/1");
			MvcResult result = mockMvc.perform(mockHttpServletRequestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			System.err.println(response.getContentAsString());
			int status = response.getStatus();
			assertEquals(200, status);
		}
		
		@Test
		public void deleteEmployeeByIdTest() throws Exception {
			
			Mockito.when(employeeService.deleteEmployeeById(15)).thenReturn(1);

			//System.err.println("*****" + Mockito.when(historyService.getById(1)).thenReturn(history1));
			MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.delete("/deleteEmployee/15");
			MvcResult result = mockMvc.perform(mockHttpServletRequestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			System.err.println(response.getContentAsString());
			int status = response.getStatus();
			assertEquals(200, status);
		}
		
		@Test
		public void editEmployeeTest() throws Exception {

			Mockito.when(employeeService.updateEmployee(new Employee(1,"Rashmiranjan",new Date()))).
			thenReturn((new Employee(1,"Rashmiranjan",new Date())));
			

			ObjectMapper mapper = new ObjectMapper();
			MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.put("/editEmployee")
					.contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON)
	                .characterEncoding("UTF-8")
	                .content(mapper.writeValueAsString(new Employee(2,"Rashmiranjan",new Date())));
			MvcResult result = mockMvc.perform(mockHttpServletRequestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			System.err.println("---"+response.getContentAsString());
			int status = response.getStatus();
			assertEquals(200, status);
		}
		
		@Test
		public void createEmployeeTest() throws Exception {
			
			Mockito.when(employeeService.createEmployee(Mockito.any(Employee.class)))
			.thenReturn(new Employee(2,"Rashmiranjan",new Date()));

			ObjectMapper mapper = new ObjectMapper();
			MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/createEmployee")
					.contentType(MediaType.APPLICATION_JSON
							)
	                .accept(MediaType.APPLICATION_JSON)
	                .characterEncoding("UTF-8")
	                .content(mapper.writeValueAsString(new Employee(4,"Rashmiranjan",new Date())));
			MvcResult result = mockMvc.perform(mockHttpServletRequestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			System.err.println("---"+response.getContentAsString());
			int status = response.getStatus();
			assertEquals(200, status);
		}
	}



