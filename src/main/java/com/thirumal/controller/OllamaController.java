/**
 * 
 */
package com.thirumal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thirumal.model.OllamaChat;
import com.thirumal.service.OllamaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * 
 */
@RestController
@RequestMapping("ollama")
public class OllamaController {

	private final OllamaService ollamaService;

	public OllamaController(OllamaService ollamaService) {
		super();
		this.ollamaService = ollamaService;
	}
	
	@PostMapping("/ask-question")
	public String postMethodName(@RequestBody OllamaChat ollamaChat) {
		//TODO: process POST request
		
		return ollamaService.askQuestion(ollamaChat);
	}
	
}
