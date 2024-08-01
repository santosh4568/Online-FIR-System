package com.example.Online_FIR_System.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Online_FIR_System.Model.Officer;

public interface OfficerRepository extends JpaRepository<Officer, Long> {

	boolean existsByUsername(String username);

}
