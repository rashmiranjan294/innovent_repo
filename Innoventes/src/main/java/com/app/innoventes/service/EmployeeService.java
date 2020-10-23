package com.app.innoventes.service;

import java.util.List;

import com.app.innoventes.model.Employee;

public interface EmployeeService {
	
    public List<Employee> getAllEmployees();
	
	public Employee createEmployee(Employee emp);

	public Employee getEmployeeById(long id);

	public int deleteEmployeeById(long id);

	public Employee updateEmployee(Employee emp);
	
	public Employee getEmployeeByName(String name);

}
