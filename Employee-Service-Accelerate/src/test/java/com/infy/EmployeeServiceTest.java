package com.infy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.infy.exception.EmployeeNotFoundException;
import com.infy.model.Employee;
import com.infy.repository.EmployeeRepository;
import com.infy.service.EmployeeService;

@SpringBootTest
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;

	@MockBean
	private EmployeeRepository employeeRepository;

	// Test Case for searchEmployeeById
	@Test
	public void testSearchEmployeeById_Success() {
		Long employeeId = 1L;
		Employee employee = new Employee(employeeId, "John Doe", 50000.00);
		when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

		Optional<Employee> foundEmployee = employeeService.searchEmployeeById(employeeId);

		assertTrue(foundEmployee.isPresent());
		assertEquals("John Doe", foundEmployee.get().getName());
		verify(employeeRepository, times(1)).findById(employeeId);
	}

	@Test
	public void testSearchEmployeeById_NotFound() {
		Long employeeId = 999L;
		when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

		Optional<Employee> foundEmployee = employeeService.searchEmployeeById(employeeId);

		assertFalse(foundEmployee.isPresent());
		verify(employeeRepository, times(1)).findById(employeeId);
	}

	// Test Case for searchEmployeeByName
	@Test
	public void testSearchEmployeeByName_Success() {
		String name = "John";
		Employee employee1 = new Employee(1L, "John Doe", 50000.00);
		Employee employee2 = new Employee(2L, "John Smith", 60000.00);
		when(employeeRepository.findByNameContainingIgnoreCase(name)).thenReturn(Arrays.asList(employee1, employee2));

		List<Employee> employees = employeeService.searchEmployeeByName(name);

		assertEquals(2, employees.size());
		assertTrue(employees.stream().anyMatch(emp -> emp.getName().equals("John Doe")));
		assertTrue(employees.stream().anyMatch(emp -> emp.getName().equals("John Smith")));
		verify(employeeRepository, times(1)).findByNameContainingIgnoreCase(name);
	}

	@Test
	public void testSearchEmployeeByName_NotFound() {
		String name = "NonExistent";
		when(employeeRepository.findByNameContainingIgnoreCase(name)).thenReturn(Arrays.asList());

		List<Employee> employees = employeeService.searchEmployeeByName(name);

		assertEquals(0, employees.size());
		verify(employeeRepository, times(1)).findByNameContainingIgnoreCase(name);
	}

	// Test Case for updateEmployee
	@Test
	public void testUpdateEmployee_Success() {
		Long employeeId = 1L;
		Employee existingEmployee = new Employee(employeeId, "John Doe", 50000.00);
		Employee updatedEmployee = new Employee(employeeId, "John Updated", 60000.00);
		when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(existingEmployee));
		when(employeeRepository.save(existingEmployee)).thenReturn(updatedEmployee);

		Employee result = employeeService.updateEmployee(employeeId, updatedEmployee);

		assertEquals("John Updated", result.getName());
		assertEquals(60000.00, result.getSalary());
		verify(employeeRepository, times(1)).findById(employeeId);
		verify(employeeRepository, times(1)).save(existingEmployee);
	}

	@Test
	public void testUpdateEmployee_NotFound() {
		Long employeeId = 999L;
		Employee updatedEmployee = new Employee(employeeId, "NonExistent", 60000.00);
		when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

		assertThrows(EmployeeNotFoundException.class,
				() -> employeeService.updateEmployee(employeeId, updatedEmployee));

		verify(employeeRepository, times(1)).findById(employeeId);
		verify(employeeRepository, never()).save(any());
	}

	// Test Case for deleteEmployee
	@Test
	public void testDeleteEmployee_Success() {
		Long employeeId = 1L;
		when(employeeRepository.existsById(employeeId)).thenReturn(true);
		doNothing().when(employeeRepository).deleteById(employeeId);

		employeeService.deleteEmployee(employeeId);

		verify(employeeRepository, times(1)).existsById(employeeId);
		verify(employeeRepository, times(1)).deleteById(employeeId);
	}

	@Test
	public void testDeleteEmployee_NotFound() {
		Long employeeId = 999L;
		when(employeeRepository.existsById(employeeId)).thenReturn(false);

		assertThrows(EmployeeNotFoundException.class, () -> employeeService.deleteEmployee(employeeId));

		verify(employeeRepository, times(1)).existsById(employeeId);
		verify(employeeRepository, never()).deleteById(employeeId);
	}

	// Test Case for getEmployeesBySalaryGreaterThan
	@Test
	public void testGetEmployeesBySalaryGreaterThan_Success() {
		Double salary = 50000.00;
		Employee employee1 = new Employee(1L, "John Doe", 60000.00);
		Employee employee2 = new Employee(2L, "Jane Smith", 70000.00);
		when(employeeRepository.findBySalaryGreaterThanOrderByName(salary))
				.thenReturn(Arrays.asList(employee1, employee2));

		List<Employee> employees = employeeService.getEmployeesBySalaryGreaterThan(salary);

		assertEquals(2, employees.size());
		assertTrue(employees.stream().anyMatch(emp -> emp.getName().equals("John Doe")));
		assertTrue(employees.stream().anyMatch(emp -> emp.getName().equals("Jane Smith")));
		verify(employeeRepository, times(1)).findBySalaryGreaterThanOrderByName(salary);
	}

	@Test
	public void testGetEmployeesBySalaryGreaterThan_NotFound() {
		Double salary = 100000.00;
		when(employeeRepository.findBySalaryGreaterThanOrderByName(salary)).thenReturn(Arrays.asList());

		List<Employee> employees = employeeService.getEmployeesBySalaryGreaterThan(salary);

		assertEquals(0, employees.size());
		verify(employeeRepository, times(1)).findBySalaryGreaterThanOrderByName(salary);
	}
}
