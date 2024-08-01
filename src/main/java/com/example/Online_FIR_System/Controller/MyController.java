package com.example.Online_FIR_System.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.Online_FIR_System.Model.FIR;
import com.example.Online_FIR_System.Model.Officer;
import com.example.Online_FIR_System.Model.User;
import com.example.Online_FIR_System.Services.FirService;
import com.example.Online_FIR_System.Services.OfficerService;
import com.example.Online_FIR_System.Services.UserService;



@Controller
public class MyController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FirService firService;
	
	@Autowired
	private OfficerService officerService; 
	
	boolean isLogin  = false;
	
	
	
	@GetMapping("/")
	public ModelAndView loadHome() {
		return new ModelAndView("home");
	}
	
	@GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("login"); // This should match the name of your JSP file without the .jsp extension
    }
	
	@GetMapping("/signup")
	public ModelAndView showSignUp() {
		return new ModelAndView("sign-up");
	}
	

	@GetMapping("/add-complaint")
	public ModelAndView showAddComplaint() {
		return new ModelAndView("add-complaint");
	}
	
	
//	@GetMapping("/signupOfficer")
//	public ModelAndView showOfficerSignup() {
//		return new ModelAndView("signupOfficer");
//	}
	
	
	
	
	@PostMapping("/signup/client")
    public ModelAndView registerUser(
            @RequestParam("name") String name,
            @RequestParam("phone") String phoneNumber,
            @RequestParam("address") String address,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            ModelAndView modelAndView) {
        if (userService.existsByUsername(username)) {
            modelAndView.addObject("errorMessage", "Username already exists");
            modelAndView.setViewName("sign-up");
            return modelAndView;
        }
        User user = new User();
        user.setName(name);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        user.setUsername(username);
        user.setPassword(password);

        userService.SaveUser(user);

        modelAndView.addObject("message", "Registration successful! Please login.");
        modelAndView.setViewName("login");
        return modelAndView;
    }
	
	
	@PostMapping("/signup/officer")
	public ModelAndView registerOfficer(@RequestParam("serviceNumber") String Service,
			@RequestParam("name") String name , @RequestParam("email") String mail,
			@RequestParam("phone") String phone ,@RequestParam("state") String state ,
			@RequestParam("district") String dist ,@RequestParam("city") String ct,
			@RequestParam("policeStationName") String police ,@RequestParam("username") String user ,
			@RequestParam("password") String pass , ModelAndView modelAndView) {
		
		if(officerService.existsByUsername(name)) {
			modelAndView.addObject("errorMessage", "Username already exists");
            modelAndView.setViewName("sign-up");
            return modelAndView;
		}
		
		Officer officer = new Officer();
		officer.setCity(ct);
		officer.setDist(dist);
		officer.setEmail(mail);
		officer.setName(name);
		officer.setPassword(pass);
		officer.setPhone(phone);
		officer.setPoliceStation(police);
		officer.setServiceNumber(Service);
		officer.setState(state);
		officer.setUsername(user);
		
		officerService.SaveOfficer(officer);
		modelAndView.addObject("message", "Registration successful! Please login.");
        modelAndView.setViewName("login");
		return modelAndView;		
	}
	
	
	@PostMapping("/login/client")
    public ModelAndView loginUser(
            @RequestParam("username") String username,
            @RequestParam("password") String password) {
        ModelAndView modelAndView = new ModelAndView();

        User user = userService.findByUsername(username);
        if (user == null || !(userService.existsByUsername(username))) {
        	modelAndView.addObject("errorMessage", "Invalid username");
            modelAndView.setViewName("WrongUsername");
        } 
        else if (!user.getPassword().equals(password)) {
        	modelAndView.addObject("errorPassword", "Wrong Password");
        	modelAndView.setViewName("WrongPassword");
        }
        else {
        	isLogin = true;
            modelAndView.setViewName("home"); // Redirect to the home page on successful login
        }

        return modelAndView;
    }
	
	@PostMapping("/add-complaint")
	public ModelAndView addComplaint(
			@RequestParam("state") String state,
            @RequestParam("district") String district,
            @RequestParam("policeStation") String policeStation,
            @RequestParam("details") String details,
            @RequestParam("complainantName") String complainantName,
            @RequestParam("complainantPhone") String complainantPhone) {
		
		ModelAndView modelAndView = new ModelAndView();
		if(isLogin == false){
			modelAndView.setViewName("login");
			return modelAndView;		
		}
		else {
		FIR complaint = new FIR();
		complaint.setState(state);
        complaint.setDistrict(district);
        complaint.setPoliceStation(policeStation);
        complaint.setDetails(details);
        complaint.setComplainantName(complainantName);
        complaint.setComplainantPhone(complainantPhone);

        firService.SaveFir(complaint);

        modelAndView.setViewName("add-complaint");
        modelAndView.addObject("successMessage", "Complaint submitted successfully!");
        modelAndView.setViewName("home");
		
		return modelAndView;
		}
	}
}
