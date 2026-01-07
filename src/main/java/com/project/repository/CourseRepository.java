package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.Course;
import com.project.entity.Instructor;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	
	boolean existsByTitleAndInstructor(String title, Instructor instructor);
}
