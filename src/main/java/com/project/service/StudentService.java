package com.project.service;

import com.project.dto.StudentRequestDto;
import com.project.dto.StudentResponseDto;
import com.project.exception.ValidationException;


public interface StudentService {

	StudentResponseDto addStudent(StudentRequestDto dto) throws ValidationException;

}
