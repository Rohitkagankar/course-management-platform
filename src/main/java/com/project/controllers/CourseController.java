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
import com.project.dto.CourseRequestDto;
import com.project.dto.CourseResponseDto;
import com.project.exception.NotFoundException;
import com.project.exception.ValidationException;
import com.project.service.CourseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {

	private final CourseService courseService;
	
	/**
	 * add course
	 * @param dto
	 * @return
	 * @throws ValidationException
	 * @throws NotFoundException
	 */
	@PostMapping("/add")
	public ResponseEntity<CourseResponseDto> addCourse(@Valid @RequestBody CourseRequestDto dto)
			throws ValidationException, NotFoundException {

		CourseResponseDto result = courseService.addCourse(dto);
		return ResponseEntity.created(null).body(result);
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<CourseResponseDto>> getAllCourses() {

		List<CourseResponseDto> result = courseService.getAllCourses();
		return ResponseEntity.ok(result);
	}
	
	
	/**
	 * delete course
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCourse(@RequestParam("id") Long id) throws NotFoundException {
		courseService.deleteCourse(id);
		return ResponseEntity.ok("course deleted successfully.");
	}
	
	/**
	 *  2. top 3 courses with no of modules
	 * @return
	 */
	@GetMapping("/query/2")
	public ResponseEntity<List<CourseResponseDto>> getMethodName2() {
		List<CourseResponseDto> result=courseService.topCourseswithModules();
		return ResponseEntity.ok(result);

	}

	// 4. courses shorter than 60 min 
	@GetMapping("/query/4")
	public ResponseEntity<List<CourseResponseDto>> getMethodName() {

//		List<CourseResponseDto> result 
//		return ResponseEntity.ok(result); 
		return null;

	}

}
