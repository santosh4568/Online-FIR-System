package com.example.Online_FIR_System.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Online_FIR_System.Model.FIR;
import com.example.Online_FIR_System.Repository.FirRepository;

@Service
public class FirService {
	
	@Autowired
	private FirRepository FirRepo;
	
	public FIR SaveFir(FIR fir){
		return FirRepo.save(fir);
	}
	
	public List<FIR> getAllFIR(){
		return FirRepo.findAll();
	}
}
