package com.alien.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alien.common.CustomerOrderDTO;
import com.alien.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query(value = "SELECT c.name , COUNT(o) FROM Customer c JOIN c.orders o GROUP BY c.id")
	List<Object[]> findCustomerOrderCount();

	@Query(value = "SELECT  NEW com.alien.common.CustomerOrderDTO(c.name , COUNT(o), SUM(o.price)) FROM Customer c JOIN c.orders o GROUP BY c.id")
	List<CustomerOrderDTO> findCustomerOrderCountResponse();

}
