package com.alien.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alien.dto.ChatGPTRequest;
import com.alien.dto.ChatGptResponse;

@RestController
@RequestMapping("/bot")
public class CustomBotController {

	private String model;

	private String apiURL;

	private RestTemplate template;

	@GetMapping("/chat")
	public String chat(@RequestParam("prompt") String prompt) {
		ChatGPTRequest request = new ChatGPTRequest(model, prompt);
		ChatGptResponse response = template.postForObject(apiURL, request, ChatGptResponse.class);

		return response.getChoices().get(0).getMessage().getContent();
	}

}
