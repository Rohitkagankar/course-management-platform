package com.project.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDto {
	
	private Long studentId;
	
	private String name;
	
	private String email;
	
	private String mobileNumber;
	
	private LocalDate dob;
	
	private String gender;
	
	private String address;
	
	private List<Long> courseIds;
}
