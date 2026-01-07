package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	boolean existsByEmail(String email);
	boolean existsByMobileNumber(String mobileNumber);
}
