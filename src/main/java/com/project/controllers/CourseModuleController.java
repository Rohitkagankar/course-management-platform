package com.project.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.project.dto.CourseModuleRequestDto;
import com.project.dto.CourseModuleResponseDto;
import com.project.exception.NotFoundException;
import com.project.exception.ValidationException;
import com.project.service.CourseModuleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/coursemodule")
public class CourseModuleController {
	
	private final CourseModuleService courseModuleService;
	
	/**
	 * add course module
	 * @param dto
	 * @return
	 * @throws ValidationException
	 * @throws NotFoundException
	 */
	@PostMapping("/add")
	public ResponseEntity<CourseModuleResponseDto> addCourseModule(@Valid @RequestBody CourseModuleRequestDto dto) throws ValidationException, NotFoundException {
		
		CourseModuleResponseDto result=courseModuleService.addCourseModule(dto);
		return ResponseEntity.created(null).body(result);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<CourseModuleResponseDto>> getAllCourseModules() {
		
		List<CourseModuleResponseDto> result=courseModuleService.getAllCourseModules();
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCourseModule(@RequestParam("id") Long id) throws NotFoundException{
		courseModuleService.deleteCourseModule(id);
		return ResponseEntity.ok("course module deleted successfully.");
	}  
	
	
}
