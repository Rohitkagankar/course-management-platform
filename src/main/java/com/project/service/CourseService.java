package com.project.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.project.dto.CourseRequestDto;
import com.project.dto.CourseResponseDto;
import com.project.dto.InstructorResponseDto;
import com.project.exception.NotFoundException;
import com.project.exception.ValidationException;

import jakarta.validation.Valid;

public interface CourseService {

	CourseResponseDto addCourse( CourseRequestDto dto) throws NotFoundException, ValidationException;

	List<CourseResponseDto> getAllCourses();

	void deleteCourse(Long id) throws NotFoundException;

	List<CourseResponseDto> topCourseswithModules();

}
