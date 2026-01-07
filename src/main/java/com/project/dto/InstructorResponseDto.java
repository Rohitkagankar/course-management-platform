package com.project.dto;

import java.util.List;

import com.project.entity.Course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstructorResponseDto {
	
	private Long instructorId;
	
	private String name;
	
	private String email;
	
	private String country;
	
	private boolean active;
}
