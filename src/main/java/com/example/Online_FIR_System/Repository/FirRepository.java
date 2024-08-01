package com.example.Online_FIR_System.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Online_FIR_System.Model.FIR;

public interface FirRepository  extends JpaRepository<FIR , Long > {
	

}
