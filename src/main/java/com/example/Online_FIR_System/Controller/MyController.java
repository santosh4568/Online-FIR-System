package com.example.Online_FIR_System.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Online_FIR_System.Model.User;
import com.example.Online_FIR_System.Services.UserService;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


@Controller
public class MyController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String loadHome() {
		return "home";
	}
	
	@GetMapping("/login")
    public String showLoginPage() {
        return "login"; // This should match the name of your JSP file without the .jsp extension
    }
	
	@GetMapping("/signup")
	public String showSignUp() {
		return "sign-up";
	}
	
	 @PostMapping("/signup")
	    public ResponseEntity<Map<String, Object>> registerUser(
	            @RequestParam("name") String name,
	            @RequestParam("phoneNumber") String phoneNumber,
	            @RequestParam("address") String address,
	            @RequestParam("username") String username,
	            @RequestParam("password") String password,
	            Model model) {
		 		if (userService.existsByUsername(username)) {
	            return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Username already exists"));
		 		}
	        User user = new User();
	        user.setName(name);
	        user.setPhoneNumber(phoneNumber);
	        user.setAddress(address);
	        user.setUsername(username);
	        user.setPassword(password);

	        userService.SaveUser(user);

	        model.addAttribute("message", "Registration successful!");

	        return ResponseEntity.ok(Map.of("success", true));
	    }
	@GetMapping("/add-complaint")
	public String showAddComplaint() {
		return "add-complaint";
	}
}
