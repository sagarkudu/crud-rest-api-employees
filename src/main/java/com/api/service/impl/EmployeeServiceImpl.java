package com.api.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;


import com.api.exception.ResourceNotFoundException;
import com.api.model.Employee;
import com.api.repository.EmployeeRepository;
import com.api.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	//creating constructor based dependency injection which is when there parameters are mandatory.
	//Here EmployeeRepository is mandatory parameter.
	
	private EmployeeRepository employeeRepository;

	
	//creating constructor using source
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	//Adding unimplemented methods

	//create the employee
	@Override
	public Employee saveEmployee(Employee employee) {
		//we are simply going to use employeeRepository and its method 'save' and we are going save its instance to the database.
		return employeeRepository.save(employee);
	}

	//get all employees
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	//get employee by id
	@Override
	public Employee getEmployeeById(long id) {
		// Optional<Employee> employee = employeeRepository.findById(id);
		// if (employee.isPresent()) {
		// 	return employee.get();
		// } else {
		// 	throw new ResourceNotFoundException("employee", "Id", id);
		// }

		//using lamda expression instead of above
		return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("employee", "Id", id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// we need to check whether that employee exists in database or not.
		Employee existingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));

		//set the employee
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());

		//save existing employee
		employeeRepository.save(existingEmployee);

		return existingEmployee;
	}

	//delete the employee
	@Override
	public void deleteEmployee(long id) {

		//check whether given id exists in db.
		employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee Not Found", "Id", id));
		employeeRepository.deleteById(id);
		
	}

	


}
