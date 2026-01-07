package com.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.project.dto.CourseRequestDto;
import com.project.dto.CourseResponseDto;
import com.project.entity.Course;
import com.project.entity.CourseModule;
import com.project.entity.Instructor;
import com.project.enums.CategoryEnum;
import com.project.enums.DifficultyEnum;
import com.project.exception.NotFoundException;
import com.project.exception.ValidationException;
import com.project.repository.CourseModuleRepository;
import com.project.repository.CourseRepository;
import com.project.repository.InstructorRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{
	
	private final CourseRepository courseRepository;
	private final InstructorRepository instructorRepository;
	private final CourseModuleRepository courseModuleRepository;

	@Override
	@Transactional
	public CourseResponseDto addCourse(CourseRequestDto dto) throws NotFoundException, ValidationException {
		Course course=new Course();
		Instructor instructor=instructorRepository.findById(dto.getInstructorId()).orElseThrow(()->new NotFoundException("Instructor not found with this id"));
		
		if(courseRepository.existsByTitleAndInstructor(dto.getTitle(), instructor)) {
			throw new ValidationException("Course is already exist with this title and Instructor.");
		}
		
		BeanUtils.copyProperties(dto, course);
		if(instructor.isActive()) {
			course.setInstructor(instructor);
		}else {
			throw new NotFoundException("Instructor is not active with this id");
		}
		boolean categoryCheck=false;
		boolean difficultyCheck=false;
		for(CategoryEnum e : CategoryEnum.values()) {
			if(dto.getCategory().equalsIgnoreCase(e.toString())) {
				categoryCheck=true;
			}
		}
		if(categoryCheck) {
			course.setCategory(dto.getCategory().toUpperCase());
		}else {
			throw new ValidationException("Not valid category.");
		}
		
		for(DifficultyEnum e : DifficultyEnum.values()) {
			
			if(dto.getDifficulty().equalsIgnoreCase(e.toString())) {
				difficultyCheck=true;
			}
		}
		if(difficultyCheck) {
			course.setDifficulty(dto.getDifficulty().toUpperCase());
		}else {
			throw new ValidationException("Not valid difficulty level.");
		}
		course.setTotalModules(0);
		course.setTotalMinutes(0);
		return mapToDto(courseRepository.save(course));
	}
	
	private CourseResponseDto mapToDto(Course course) {
		CourseResponseDto dto=new CourseResponseDto();
		BeanUtils.copyProperties(course, dto);
		dto.setInstructorId(course.getInstructor().getInstructorId());
		dto.setCourseModulesIds(course.getCourseModules().stream().map(CourseModule::getModuleId).collect(Collectors.toList()));
		return dto;
	}

	@Override
	@Transactional
	public List<CourseResponseDto> getAllCourses() {
		List<Course> courses=courseRepository.findAll();
		
		return courses.stream().map(this::mapToDto).collect(Collectors.toList());
	}

	
	@Override
	@Transactional
	public void deleteCourse(Long id) throws NotFoundException {
		if(! courseRepository.existsById(id)) {
			throw new NotFoundException("course not found with this id");
		}
		courseRepository.deleteById(id);
		
	}

	@Override
	public List<CourseResponseDto> topCourseswithModules() {
//		List<Course> courses=courseRepository.findAll().stream().collect(Collectors.maxBy(Course::getCourseModules)).limit(3).toList();
//		return courses.stream().map(this::mapToDto).toList();
		return null;
	}
	
	
	
	
}
