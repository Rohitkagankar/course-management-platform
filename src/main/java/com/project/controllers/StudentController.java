package com.project.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.StudentRequestDto;
import com.project.dto.StudentResponseDto;
import com.project.exception.ValidationException;
import com.project.responseHandler.GenericResponseHandlers;
import com.project.service.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
	
	private final StudentService studentService;
	
	@PostMapping("/add")
	public Object addStudentMethod(@Valid @RequestBody StudentRequestDto dto) throws ValidationException {
		StudentResponseDto result=studentService.addStudent(dto);
		return new GenericResponseHandlers.Builder().setData(result).setStatus(HttpStatus.CREATED).setMessage("Student created successfully.").create();
	}
	
}
