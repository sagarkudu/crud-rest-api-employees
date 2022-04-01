package com.api.service;

import java.util.List;

import com.api.model.Employee;

public interface EmployeeService {
	
	//we are going to create methods so Controller can communicate with Service Layer.
	
	Employee saveEmployee(Employee employee);

	//List all employees
	List<Employee> getAllEmployees();

	//Get employee by ID
	Employee getEmployeeById(long id);

	//update the employee
	Employee updateEmployee(Employee employee, long id);

	//delete the employee
	void deleteEmployee(long id);
}
