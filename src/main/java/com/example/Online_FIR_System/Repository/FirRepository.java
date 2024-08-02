package com.example.Online_FIR_System.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Online_FIR_System.Model.FIR;

public interface FirRepository  extends JpaRepository<FIR , Long > {
	Optional<FIR> findById(Long id);
	List<FIR> findByPoliceStation(String policeStation);
}
