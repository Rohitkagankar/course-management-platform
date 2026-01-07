package com.project.dto;

import java.time.LocalDate;

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
public class CourseRequestDto {
	
	@NotBlank(message = "title is required...")
	private String title;
	
	@NotBlank(message = "Category is required...")
	private String category;
	
	@NotBlank(message = "difficulty level is required...")
	private String difficulty;
	
	private LocalDate publishedOn;
	
	@NotNull(message = "instrudtor id is required.")
	private long instructorId;
	
}
