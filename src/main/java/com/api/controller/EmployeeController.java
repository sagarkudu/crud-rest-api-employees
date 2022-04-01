package com.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.api.model.Employee;
import com.api.service.EmployeeService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController 
@RequestMapping("/api/employees")
public class EmployeeController {
	
	//communicate service layer with Controller.
	private EmployeeService employeeService;

	//generate constructor using fields
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	//build rest api to create an employee+
	//Here we need to pass Employee object, because this post request contains Employee JSON object that needs to be binds to java object and for that we are going to use @RequestBody. 
	@PostMapping()
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}
	
	// Get All Employees 
	@GetMapping
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	// Get Employee by ID.
	@GetMapping("{id}") //{id} is URL template variable.
	//{id} and variable id of @PathVariable("id") should match and long variable can be different.
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId) {
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
	}
	
	//update the employee
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
	}

	//delete employee
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long employeeId) {

		//delete employee from the db.
		employeeService.deleteEmployee(employeeId);

		//after successful deletion
		return new ResponseEntity<String>("Employee deletion successful!!!", HttpStatus.OK);
	}
	
	
}
