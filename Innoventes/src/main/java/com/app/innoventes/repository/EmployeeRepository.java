package com.app.innoventes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.innoventes.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	@Query(value="select * from Employee where name= :name",nativeQuery = true)
	Optional<Employee> findByname(String name);


}
