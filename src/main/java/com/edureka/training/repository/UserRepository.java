package com.edureka.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edureka.training.entity.UserCredential;

public interface UserRepository extends JpaRepository<UserCredential, Long>{

	List<UserCredential> findByName(String name);
	
	List<UserCredential> findByEmail(String email);
	List<UserCredential> findByPassword(String password);

	//int findByEmail(String email);
	
}

