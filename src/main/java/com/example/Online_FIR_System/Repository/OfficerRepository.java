package com.example.Online_FIR_System.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Online_FIR_System.Model.Officer;
//import java.util.List;


public interface OfficerRepository extends JpaRepository<Officer, Long> {

	boolean existsByUsername(String username);
	Officer findByUsername(String username);
}
