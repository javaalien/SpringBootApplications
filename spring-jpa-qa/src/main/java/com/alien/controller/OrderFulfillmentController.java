package com.alien.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alien.common.CustomerOrderDTO;
import com.alien.common.OrderRequest;
import com.alien.entity.Customer;
import com.alien.service.OrderFulfillmentService;

@RestController
@RequestMapping("/ecom")
public class OrderFulfillmentController {

	@Autowired
	private OrderFulfillmentService orderFulfillmentService;

	@PostMapping("/addOrder")
	public Customer addOrder(@RequestBody OrderRequest<Customer> orderRequest) {
		return orderFulfillmentService.createOrder(orderRequest);
	}

	@GetMapping("/orderCount")
	public List<Object[]> getCustomerOrderCount() {
		return orderFulfillmentService.findCustomerOrderCount();
	}

	@GetMapping("/orderCount/response")
	public List<CustomerOrderDTO> getCustomerOrderCountResponse() {
		return orderFulfillmentService.findCustomerOrderCountResponse();
	}
}
