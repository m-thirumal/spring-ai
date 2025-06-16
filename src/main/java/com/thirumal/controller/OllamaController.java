/**
 * 
 */
package com.thirumal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thirumal.model.OllamaChat;
import com.thirumal.service.OllamaService;


/**
 * 
 */
//@RestController
@Controller
@RequestMapping("ollama")
public class OllamaController {

	private final OllamaService ollamaService;

	public OllamaController(OllamaService ollamaService) {
		super();
		this.ollamaService = ollamaService;
	}
	
	@GetMapping()
	public String home(Model model) {
		model.addAttribute("response", "");
        model.addAttribute("question", "");
		return "index";
	}
	
	@PostMapping("/ask-question")
	public String postMethodName(@RequestParam OllamaChat ollamaChat, Model model) {
		
		String response = ollamaService.askQuestion(ollamaChat);
		// Add data to be rendered in the template
	    model.addAttribute("response", response);
	    model.addAttribute("question", ollamaChat.getQuestion());

	    // Return the JTE template name (without .jte extension)
	    return "index"; // This will render answer-page.jte
	}
	
}
