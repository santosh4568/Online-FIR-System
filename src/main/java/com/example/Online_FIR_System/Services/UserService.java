package com.example.Online_FIR_System.Services;

import java.util.List;

import com.example.Online_FIR_System.Model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Online_FIR_System.Model.User;
import com.example.Online_FIR_System.Repository.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public User SaveUser(User user) {
		return userRepo.save(user);
	}

	public boolean existsByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepo.existsByUsername(username);
	}

	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepo.findByUsername(username);
	}

	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

	public void updatePassword(String username, String newPassword) {
	    User user = userRepo.findByUsername(username);
	    if (user != null) {
	        user.setPassword(newPassword);
	        userRepo.save(user); // Save the updated user object
	    }
	}

	public List<User> getUsersByDistrict(String district) {
		return userRepo.findAll().stream()
				.filter(user -> user.getDistrict().equalsIgnoreCase(district))
				.toList();
	}

	public List<User> getUsersByState(String state) {
		return userRepo.findAll().stream()
				.filter(user -> user.getState().equalsIgnoreCase(state))
				.toList();
	}

	public List<User> getUsersByCity(String city) {
		return userRepo.findAll().stream()
				.filter(user -> user.getCity().equalsIgnoreCase(city))
				.toList();
	}

	public List<User> getUsersByPoliceStation(String policeStation) {
		return userRepo.findAll().stream()
				.filter(user -> user.getPoliceStation().equalsIgnoreCase(policeStation))
				.toList();
	}

	// Method to update the role of a user


	public void updateRole(String username, String newRole) {
	    User user = userRepo.findByUsername(username);
	    if (user != null) {
	        user.setRole(Role.valueOf(newRole));
	        userRepo.save(user); // Save the updated user object
	    }
	}

	public String getName(String username) {
		return userRepo.findByUsername(username).getName();
	}

}
