package com.project.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CourseModule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long moduleId;
	
	private String title;
	
	private Integer duration;
	
	private String contentType;
	
	@ManyToOne
	@JoinColumn(name = "courseId")
	private Course course;
		
}
