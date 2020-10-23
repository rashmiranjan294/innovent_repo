package com.app.innoventes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.innoventes.model.Employee;
import com.app.innoventes.response.ApiResponse;
import com.app.innoventes.service.EmployeeService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/getAllEmployees")
	public ApiResponse getAllEmployees(){
		List<Employee> empList  = employeeService.getAllEmployees();
		if(empList != null) {
			
			return new ApiResponse(HttpStatus.OK.value(), "Total "+empList.size()+ " Employees found", empList);
		}
		else {
			return new ApiResponse(HttpStatus.NOT_FOUND.value(), "Employees not found", null);
		}
	}
	
	@PostMapping("/createEmployee")
	public ApiResponse createEmployee(@RequestBody Employee emp){
		Employee addRecord= employeeService.createEmployee(emp); 
		if(addRecord != null) {
			
			return new ApiResponse(HttpStatus.OK.value(), "Employee added", addRecord);
		}
		else {
			return new ApiResponse(HttpStatus.EXPECTATION_FAILED.value(), "Employee registration failed", null);
		}
	}
	
	@GetMapping("/getEmployeeById/{id}")
	public ApiResponse getEmployeesById(@PathVariable("id") long id){
		
		Employee getEmp=employeeService.getEmployeeById(id);
		//getEmp
		if(getEmp != null) {
			
			return new ApiResponse(HttpStatus.OK.value(), "Employee found", getEmp);
		}
		else {
			
			return new ApiResponse(HttpStatus.NOT_FOUND.value(), "Employee not found with id: "+id, null);
		}
	}
	
	@PutMapping("/editEmployee")
	public ApiResponse editEmployeeById(@Valid @RequestBody Employee emp) {
		Employee editEmp= employeeService.updateEmployee(emp);
		
		if(editEmp != null) {
			
			return new ApiResponse(HttpStatus.OK.value(), "Employee updated with id: "+emp.getId(), editEmp);
		}
		else {
			
			return new ApiResponse(HttpStatus.NOT_FOUND.value(), "Employee not found with id: "+emp.getId(), null);	
		}
	}
	
	@DeleteMapping("/deleteEmployee/{id}")
	public ApiResponse deleteEmployeeById(@PathVariable("id") long id){
		int status= employeeService.deleteEmployeeById(id);
		
		if(status == 1) {
			return new ApiResponse(HttpStatus.OK.value(), "Employee deleted with id: "+id, null);
		}else {
			return new ApiResponse(HttpStatus.NOT_FOUND.value(), "Employee not found with id: "+id, null);
		}
	}
	
	// Search Employee by Technology and Designation
	@GetMapping("/getEmployeeByName/{name}")
	public ApiResponse getEmployeeByName(@PathVariable("name") String name) {

		Employee getEmpName = employeeService.getEmployeeByName(name);
		
        if(getEmpName != null) {
			
			return new ApiResponse(HttpStatus.OK.value(), "Employee name found", getEmpName);
		}
		else {
			
			return new ApiResponse(HttpStatus.NOT_FOUND.value(), "Employee name not found with: "+getEmpName, null);
		}
	}

}
