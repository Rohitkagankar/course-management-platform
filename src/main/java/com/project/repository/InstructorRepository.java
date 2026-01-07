package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.Instructor;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
	
	boolean existsByEmail(String email);
	
}
