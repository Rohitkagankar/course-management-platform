package com.project.service;

import java.util.List;

import com.project.dto.InstructorRequestDto;
import com.project.dto.InstructorResponseDto;
import com.project.exception.NotFoundException;
import com.project.exception.ValidationException;

import jakarta.validation.Valid;

public interface InstructorService {

	InstructorResponseDto createInstructor(InstructorRequestDto dto) throws ValidationException;

	List<InstructorResponseDto> getAllInstructors();

	void deleteInstructor(Long id) throws NotFoundException;

	InstructorResponseDto updateInstructor(Long id, InstructorRequestDto dto) throws NotFoundException;

	List<InstructorResponseDto> getAllInstructorswithNoCourses();

}
