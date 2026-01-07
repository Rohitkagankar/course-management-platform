package com.project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstructorRequestDto {
	
	@NotBlank(message = "instructor name is required.")
	private String name;
	
	@NotBlank(message = "instructor name is required.")
	@Email
	private String email;
	
	@NotBlank(message = "country name is required.")
	private String country;
	
	private boolean active;

}
