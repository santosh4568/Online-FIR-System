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

	public void updatePassword(String username, String newPassword) {
		// TODO Auto-generated method stub
		Officer tempOfficer = officerRepo.findByUsername(username);
		officerRepo.deleteById(tempOfficer.getID());
		
		Officer officer = new Officer();
		
		officer.setCity(tempOfficer.getCity());
		officer.setDist(tempOfficer.getDist());
		officer.setEmail(tempOfficer.getEmail());
		officer.setID(tempOfficer.getID());
		officer.setName(tempOfficer.getName());
		officer.setPassword(newPassword);
		officer.setPhone(tempOfficer.getPhone());
		officer.setPoliceStation(tempOfficer.getPoliceStation());
		officer.setServiceNumber(tempOfficer.getServiceNumber());
		officer.setState(tempOfficer.getState());
		officer.setUsername(tempOfficer.getUsername());
		
		officerRepo.save(officer);
		
	}
}
