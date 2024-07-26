package com.example.Online_FIR_System.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
	
	@GetMapping("/")
	public String loadHome() {
		return "home";
	}
	
	@GetMapping("/login")
    public String showLoginPage() {
        return "login"; // This should match the name of your JSP file without the .jsp extension
    }
}
