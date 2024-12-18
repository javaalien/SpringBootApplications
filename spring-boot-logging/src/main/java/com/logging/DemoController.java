package com.logging;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

	@GetMapping("/hello")				// http://localhost:8080/hello
	public Map<String, String> hello(@RequestParam(defaultValue = "World") String name) {

		logger.info("Name: {}", name);

		if (logger.isTraceEnabled()) {
			logger.trace("Greeting: {}", this.getGreetingMessage(name));
		}
		
		return Map.of("greeting", "Hello " + name);
	}

	private String getGreetingMessage(String name) {
		return "Hello " + name;
	}

}
