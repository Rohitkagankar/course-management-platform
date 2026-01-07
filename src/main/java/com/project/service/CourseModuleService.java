package com.project.service;

import java.util.List;

import com.project.dto.CourseModuleRequestDto;
import com.project.dto.CourseModuleResponseDto;
import com.project.exception.NotFoundException;
import com.project.exception.ValidationException;


public interface CourseModuleService {

	CourseModuleResponseDto addCourseModule(CourseModuleRequestDto dto) throws NotFoundException, ValidationException;

	List<CourseModuleResponseDto> getAllCourseModules();

	void deleteCourseModule(Long id) throws NotFoundException;

}
