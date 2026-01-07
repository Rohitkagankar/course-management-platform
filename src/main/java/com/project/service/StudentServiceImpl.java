package com.project.service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.project.dto.StudentRequestDto;
import com.project.dto.StudentResponseDto;
import com.project.entity.Course;
import com.project.entity.Student;
import com.project.exception.ValidationException;
import com.project.repository.CourseRepository;
import com.project.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
	
	private final StudentRepository studentRepository;
	private final CourseRepository courseRepository;
	
	@Override
	public StudentResponseDto addStudent(StudentRequestDto dto) throws ValidationException {
		
		if(studentRepository.existsByEmail(dto.getEmail())) {
			throw new ValidationException("Student already exist with this email.");
		}
		
		if(dto.getMobileNumber().length() != 10) {
			throw new ValidationException("mobile number is not valid.");
		}
		
		
		try {
			Integer mobile=Integer.parseInt(dto.getMobileNumber());
			if(studentRepository.existsByMobileNumber(dto.getMobileNumber())) {
				throw new ValidationException("Student already exist with this mobile number.");
			}
		}catch ( NumberFormatException e) {
			throw new ValidationException("mobile number is not valid.");
		}
		
		
		Student student=new Student();
		List<Course> courses=dto.getCourseIds().stream().map(courseRepository::findById).filter(Optional::isPresent).map(Optional::get).toList();
		BeanUtils.copyProperties(dto, student,"gender");
		
		if( dto.getGender().equalsIgnoreCase("MALE") || dto.getGender().equalsIgnoreCase("FEMALE")) {
			student.setGender(dto.getGender().toUpperCase());
		}else {
			throw new ValidationException("Gender is not valid."); 
		}
		
		student.setCourseSet(courses.stream().collect(Collectors.toSet()));
		return mapToDto(studentRepository.save(student));
	}
	
	private StudentResponseDto mapToDto(Student student) {
		StudentResponseDto dto=new StudentResponseDto();
		BeanUtils.copyProperties(student, dto);
		dto.setCourseIds(student.getCourseSet().stream().map(Course::getCourseId).toList());
		return dto;
	}

}
