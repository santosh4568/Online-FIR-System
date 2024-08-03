package com.example.Online_FIR_System.Controller;
import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.Online_FIR_System.Model.ComplaintStatus;
import com.example.Online_FIR_System.Model.FIR;
import com.example.Online_FIR_System.Model.Officer;
import com.example.Online_FIR_System.Model.User;
import com.example.Online_FIR_System.Services.ComplaintStatusService;
import com.example.Online_FIR_System.Services.FirService;
import com.example.Online_FIR_System.Services.OfficerService;
import com.example.Online_FIR_System.Services.UserService;

//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;



@Controller
public class MyController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FirService firService;
	
	@Autowired
	private OfficerService officerService; 
	
	@Autowired
    private ComplaintStatusService complaintStatusService;
	
	boolean isLogin  = false;
	
	String activePoliceStation = "";
	
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
	
	
	@GetMapping("/admin/fir")
	public ModelAndView AllFir() {
		ModelAndView modelAndView = new ModelAndView();
		List<FIR> complaints = firService.getAllFIR();
		modelAndView.addObject("complaints", complaints);
        modelAndView.setViewName("allFIR");
		
		return modelAndView;
	}
	
	
	@GetMapping("/admin/user")
	public ModelAndView AllUser() {
		ModelAndView modelAndView = new ModelAndView();
		List<User> users = userService.getAllUser();
		modelAndView.addObject("users", users);
		modelAndView.setViewName("alluser");
		return modelAndView;	
	}
	
	
	@GetMapping("/admin/officer")
	public ModelAndView AllOfficer() {
		ModelAndView modelAndView = new ModelAndView();
		List<Officer> officers = officerService.getAllOfficer();
		modelAndView.addObject("officers", officers);
		modelAndView.setViewName("allofficer");
		return modelAndView;	
		
	}
	
	
	
//	@GetMapping("/login/officer")
//	public ModelAndView showOfficerHome() {
//		return new ModelAndView("login");
//	}
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
        	//session.setAttribute("username", username);
        	modelAndView.setViewName("home"); // Redirect to the home page on successful login
        }

        return modelAndView;
    }
	
	
	@PostMapping("/login/officer")
	public ModelAndView loginOfficer(@RequestParam("policeServiceNumber") String serviceNumberString , 
			@RequestParam("password") String password , ModelAndView modelAndView) {
		
		Officer officer = officerService.findByUsername(serviceNumberString);
		
		if (officer == null || !(officerService.existsByUsername(serviceNumberString))){
        	modelAndView.addObject("errorMessage", "Invalid username");
            modelAndView.setViewName("WrongUsername");
        } 
        else if (!officer.getPassword().equals(password)) {
        	modelAndView.addObject("errorPassword", "Wrong Password");
        	modelAndView.setViewName("WrongPassword");
        }
        else {
        	//session.setAttribute("username", serviceNumberString);
        	activePoliceStation = officer.getPoliceStation();
        	//List<FIR> complaints = firService.getAllFIR();
        	List<FIR> complaints = firService.findByPoliceStation(activePoliceStation);           
        	modelAndView.addObject("complaints", complaints);
            modelAndView.setViewName("officerHome"); // Redirect to the home page on successful login
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

		FIR complaint = new FIR();
		complaint.setState(state);
        complaint.setDistrict(district);
        complaint.setPoliceStation(policeStation);
        complaint.setDetails(details);
        complaint.setComplainantName(complainantName);
        complaint.setComplainantPhone(complainantPhone);

        FIR savedComplaint = firService.SaveFir(complaint);

        ComplaintStatus complaintStatus = new ComplaintStatus();
        complaintStatus.setComplaintId(savedComplaint.getId());
        complaintStatus.setStatus(savedComplaint.getStatus());

        complaintStatusService.saveComplaintStatus(complaintStatus);

        //modelAndView.addObject("successMessage", "Complaint submitted successfully!");
        modelAndView.addObject("savedComplaint", savedComplaint);       
        
        modelAndView.setViewName("complaintSummary");
		
		return modelAndView;
	}
	
	
	
	@GetMapping("/officerHome")
    public ModelAndView officerHome() {
        ModelAndView modelAndView = new ModelAndView();
        //List<FIR> complaints = firService.getAllFIR();
        List<FIR> complaints = firService.findByPoliceStation(activePoliceStation);
        modelAndView.addObject("complaints", complaints);
        modelAndView.setViewName("officerHome");
        return modelAndView;
    }

	@PostMapping("/acceptComplaint")
    public String acceptComplaint(@RequestParam("complaintId") Long complaintId) {
        FIR complaint = firService.findById(complaintId).orElse(null);
        if (complaint != null) {
            complaint.setStatus("Accepted");
            firService.SaveFir(complaint);
            firService.updateFirStatus(complaintId, "Accepted");
        }
        return "redirect:/officerHome";
    }

	@PostMapping("/rejectComplaint")
    public String rejectComplaint(@RequestParam("complaintId") Long complaintId) {
        FIR complaint = firService.findById(complaintId).orElse(null);
        if (complaint != null) {
            //firService.deleteById(complaintId);
            firService.rejectComplaint(complaintId);
            firService.updateFirStatus(complaintId, "Rejected");
        }
        return "redirect:/officerHome";
    }
	
	@PostMapping("/culpritArrested")
    public String culpritArrested(@RequestParam("complaintId") Long complaintId) {
        FIR complaint = firService.findById(complaintId).orElse(null);
        if (complaint != null) {
            //firService.deleteById(complaintId);
            //firService.rejectComplaint(complaintId);
        	firService.arrestedCulprit(complaintId);
        	
        	
            firService.updateFirStatus(complaintId, "Culprit Arrested");
        }
        return "redirect:/officerHome";
    }
	
	
	@GetMapping("/track-complaint")
    public ModelAndView showTrackComplaintForm() {
        return new ModelAndView("track-complaint-form");
    }

    @PostMapping("/trackComplaint")
    public ModelAndView trackComplaint(@RequestParam("complaintId") Long complaintId) {
    	 ModelAndView modelAndView = new ModelAndView("track-complaint");
         
         ComplaintStatus complaintStatus = complaintStatusService.findByComplaintId(complaintId);

         if (complaintStatus == null) {
             modelAndView.addObject("errorMessage", "No complaint found with the provided ID.");
         } else {
             modelAndView.addObject("statusMessage", "The status of your complaint is: " + complaintStatus.getStatus());
         }

         return modelAndView;
    }
    
//    @GetMapping("/logout")
//    public String logout(HttpServletRequest request, HttpServletResponse response) {
//        // Invalidate the session
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            session.invalidate();
//        }
//        return "redirect:/";
//    }
}
