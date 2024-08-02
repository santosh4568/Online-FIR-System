package com.example.Online_FIR_System.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Online_FIR_System.Model.Officer;
import com.example.Online_FIR_System.Repository.OfficerRepository;

@Service
public class OfficerService {
	
	@Autowired
	private OfficerRepository officerRepo;
	
	public Officer SaveOfficer(Officer officer) {
		return officerRepo.save(officer);
	}
	
	public boolean existsByUsername(String username) {
		// TODO Auto-generated method stub
		return officerRepo.existsByUsername(username);
	}

	public Officer findByUsername(String serviceNumberString) {
		// TODO Auto-generated method stub
		return officerRepo.findByUsername(serviceNumberString);
		
	}

	public List<Officer> getAllOfficer() {
		// TODO Auto-generated method stub
		return officerRepo.findAll();
	}
}
