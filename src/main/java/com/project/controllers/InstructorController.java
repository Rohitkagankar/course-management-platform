package com.project.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.project.dto.InstructorRequestDto;
import com.project.dto.InstructorResponseDto;
import com.project.exception.NotFoundException;
import com.project.exception.ValidationException;
import com.project.service.InstructorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
@RequestMapping("/instructor")
public class InstructorController {
	
	private final InstructorService instructorService;
	
	@PostMapping("/add")
	public ResponseEntity<InstructorResponseDto> addInstructor(@Valid @RequestBody InstructorRequestDto dto) throws ValidationException {
		
		InstructorResponseDto result=instructorService.createInstructor(dto);
		return ResponseEntity.created(null).body(result);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<InstructorResponseDto>> getAllInstructors() {
		List<InstructorResponseDto> result=instructorService.getAllInstructors();
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteInstructor(@RequestParam("id") Long id) throws NotFoundException{
		instructorService.deleteInstructor(id);
		return ResponseEntity.ok("instructor deleted successfully.");
	}
	 
	@PutMapping("update/{id}")
	public ResponseEntity<InstructorResponseDto> updateInstructor(@PathVariable("id") Long id,@Valid @RequestBody InstructorRequestDto dto) throws NotFoundException {
		
		InstructorResponseDto result=instructorService.updateInstructor(id,dto);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/query/five")
	public ResponseEntity<List<InstructorResponseDto>> getMethodName5() {
		
		List<InstructorResponseDto> result=instructorService.getAllInstructorswithNoCourses();
		return ResponseEntity.ok(result);
	}
	

}
