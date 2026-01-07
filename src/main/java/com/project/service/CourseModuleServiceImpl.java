package com.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.project.dto.CourseModuleRequestDto;
import com.project.dto.CourseModuleResponseDto;
import com.project.entity.Course;
import com.project.entity.CourseModule;
import com.project.enums.ContentTypeEnum;
import com.project.exception.NotFoundException;
import com.project.exception.ValidationException;
import com.project.repository.CourseModuleRepository;
import com.project.repository.CourseRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseModuleServiceImpl implements CourseModuleService {
	
	private final CourseModuleRepository courseModuleRepository;
	
	private final CourseRepository courseRepository;
	
	@Override
	@Transactional
	public CourseModuleResponseDto addCourseModule( CourseModuleRequestDto dto) throws NotFoundException, ValidationException {
		
		CourseModule module=new CourseModule();
		Course course=courseRepository.findById(dto.getCourseId()).orElseThrow(()-> new NotFoundException("course is not found with this id"));
		
		if(courseModuleRepository.existsByTitleAndCourse(dto.getTitle(), course)) {
			throw new ValidationException("Course module already exist in course with this title.");
		}
		boolean flag=false;
		
		for(ContentTypeEnum e: ContentTypeEnum.values()) {
			
			if(dto.getContentType().equalsIgnoreCase(e.toString())) {
				flag=true;
			}
		}
		BeanUtils.copyProperties(dto, module,"contentType");
		if(flag) {
			module.setContentType(dto.getContentType().toUpperCase());
		}else {
			throw new ValidationException("Not valid content type.");
		}
		
		course.setTotalMinutes(course.getTotalMinutes()+dto.getDuration());
		course.setTotalModules(course.getTotalModules()+1);
		module.setCourse(course);
		
		return mapToDto(courseModuleRepository.save(module));
	}

	private CourseModuleResponseDto mapToDto (CourseModule module) {
		
		CourseModuleResponseDto dto=new CourseModuleResponseDto();
		BeanUtils.copyProperties(module, dto);
		dto.setCourseId(module.getCourse().getCourseId());	
		return dto;
		
	}

	@Override
	public List<CourseModuleResponseDto> getAllCourseModules() {
		
		List<CourseModule> modulelist=courseModuleRepository.findAll();
	
		return modulelist.stream().map(this::mapToDto).collect(Collectors.toList());
		
	}

	@Override
	@Transactional
	public void deleteCourseModule(Long id) throws NotFoundException {
		if(! courseModuleRepository.existsById(id)) {
			throw new NotFoundException("course module not found with this id");
		}
		
		courseModuleRepository.deleteById(id);
		
	}
	

}
