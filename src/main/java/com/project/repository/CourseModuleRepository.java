package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.entity.CourseModule;
import com.project.entity.Course;


@Repository
public interface CourseModuleRepository extends JpaRepository<CourseModule,Long> {
	
	boolean existsByTitleAndCourse(String title, Course course);
}
