package com.project.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long studentId;
	
	private String name;
	
	private String email;
	
	private String mobileNumber;
	
	private LocalDate dob;
	
	private String gender;
	
	private String address;
	
	@ManyToMany
	@JoinTable(name = "student_courses",joinColumns = @JoinColumn(name="studentId"),
	inverseJoinColumns = @JoinColumn(name="courseId"))
	private Set<Course> courseSet=new HashSet<>();
}
