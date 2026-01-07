package com.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseModuleRequestDto {
	
	@NotBlank(message = "title is required.")
	private String title;
	
	@NotNull(message = "duration is required.")
	private Integer duration;
	
	@NotBlank(message = "content type is required.")
	private String contentType;
	
	@NotNull(message = "course id is required.")
	private Long courseId;
}
