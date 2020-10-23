package com.app.innoventes.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.innoventes.model.Employee;
import com.app.innoventes.repository.EmployeeRepository;
import com.app.innoventes.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository empRepository;

	@Override
	public List<Employee> getAllEmployees() {
		return empRepository.findAll();
	}

	@Override
	public Employee createEmployee(Employee emp) {
		return empRepository.save(emp);
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> emp=empRepository.findById(id);
		if(emp.isPresent()) {
			return emp.get();
		}
		else {
			return null;
		}
	} 
		

	@Override
	public int deleteEmployeeById(long id) {
		Optional<Employee> optionalEmployee = empRepository.findById(id);
		if (optionalEmployee.isPresent()) {
			empRepository.deleteById(id);
			return 1;
		}

		return 0;
	}
	

	@Override
	public Employee updateEmployee(Employee emp) {
		Optional<Employee> optionalEmp=empRepository.findById(emp.getId());
		if(optionalEmp.isPresent()) {
			Employee newEmp = optionalEmp.get();
			newEmp.setId(emp.getId());
			newEmp.setName(emp.getName());
			newEmp.setDateOfBirth(emp.getDateOfBirth());
			
		return empRepository.save(newEmp);
		}
		else {
			return null;
		}
			
	}
		
	@Override
	public Employee getEmployeeByName(String name) {
		Optional<Employee> optionalEmployee = empRepository.findByname(name);
		if(optionalEmployee.isPresent()) {
			return optionalEmployee.get();
		}
		else {
			return null;
		}
	}

}
