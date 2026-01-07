package com.project.dto;

import java.time.LocalDate;
import java.util.Set;

import com.project.entity.Course;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDto {
	
	@NotBlank(message = "student name is required.")
	private String name;
	
	@Email
	private String email;
	
	@NotNull(message = "mobile number is required.")
	private String mobileNumber;
	
	@NotBlank(message = "address is required.")
	private String address;
	
	private LocalDate dob;
	
	@NotBlank(message ="gender is required.")
	private String gender;
	
	private Set<Long> courseIds;
}
