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
public class CourseResponseDto {

	private Long courseId;
	private String title;
	private String category;
	private String difficulty;
	private LocalDate publishedOn;
	private Integer totalModules;
	private Integer totalMinutes;
	private long instructorId;
	private List<Long> courseModulesIds;
}
