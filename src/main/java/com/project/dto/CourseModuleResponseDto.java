package com.project.dto;

import com.project.enums.ContentTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseModuleResponseDto {
	
	private Long moduleId;
	private String title;
	private Integer duration;
	private String contentType;
	private Long courseId;
}
