package com.project.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.dto.InstructorRequestDto;
import com.project.dto.InstructorResponseDto;
import com.project.entity.Instructor;
import com.project.exception.BaseRuntimeException;
import com.project.exception.NotFoundException;
import com.project.exception.ValidationException;
import com.project.repository.InstructorRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
	
	private final InstructorRepository instructorRepository;

	@Override
	@Transactional
	public InstructorResponseDto createInstructor(InstructorRequestDto dto) throws ValidationException {
		
		if(instructorRepository.existsByEmail(dto.getEmail())) {
			throw new ValidationException("instructor already exist with this email");
		}
		
		Instructor instructor =new Instructor();
		BeanUtils.copyProperties(dto, instructor);
		return mapToDto(instructorRepository.save(instructor));
	}
	
	
	private InstructorResponseDto mapToDto(Instructor instructor) {
		InstructorResponseDto response=new InstructorResponseDto();
		BeanUtils.copyProperties(instructor, response);
		
		return response;
	}


	@Override
	@Transactional
	public List<InstructorResponseDto> getAllInstructors() {
		List<Instructor> result= instructorRepository.findAll();
		
		if(result.isEmpty()) {
			throw new BaseRuntimeException(HttpStatus.NO_CONTENT, "No instructors found");
		}
		List<InstructorResponseDto> dtos=result.stream().map(this::mapToDto).collect(Collectors.toList());
		return dtos;
	}


	@Override
	public void deleteInstructor(Long id) throws NotFoundException {
		
		if(! instructorRepository.existsById(id)) {
			throw new NotFoundException("Instructor not found with this id");
		}
		
		instructorRepository.deleteById(id);
		
	}


	@Override
	@Transactional
	public InstructorResponseDto updateInstructor(Long id, @Valid InstructorRequestDto dto) throws NotFoundException {
		Instructor instructor=instructorRepository.findById(id).orElseThrow(()-> new NotFoundException("Instructor not found with this id"));
		BeanUtils.copyProperties(dto,instructor );
		
		return mapToDto(instructorRepository.save(instructor));
	}


	@Override
	@Transactional
	public List<InstructorResponseDto> getAllInstructorswithNoCourses() {
		List<Instructor> result= instructorRepository.findAll().stream().filter((i)->i.getCourses().size()==0).toList();	
		return result.stream().map(this::mapToDto).collect(Collectors.toList());
	}

}
