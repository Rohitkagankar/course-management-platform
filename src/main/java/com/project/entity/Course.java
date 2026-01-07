package com.project.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long courseId;
	
	private String title;
	
	private String category;
	
	private String difficulty;
	
	private LocalDate publishedOn;
	
	private Integer totalModules;
	
	private Integer totalMinutes;
	
	@ManyToOne
	@JoinColumn(name = "instructorId")
	private Instructor instructor;
		
	@OneToMany(mappedBy = "course")
	private List<CourseModule> courseModules=new ArrayList<>();
	
	@ManyToMany(mappedBy = "courseSet")
	private Set<Student> students=new HashSet<>();
	
}
