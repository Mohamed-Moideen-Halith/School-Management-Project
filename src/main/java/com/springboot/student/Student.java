package com.springboot.student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.springboot.school.School;
import com.springboot.studentProfile.StudentProfile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Student_table")
public class Student {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(
			name = "c_fname"
			)
	private String firstName;
	
	private String lastName;
	
	@Column(unique = true)
	private String email;
	
	private Integer age;
	
	@OneToOne(
			mappedBy = "student",
			cascade = CascadeType.ALL
			)
	private StudentProfile studentProfile;
	
	
	@ManyToOne
	@JoinColumn(
			name = "school_id"
			)
	@JsonBackReference
	private School school;

	public StudentProfile getStudentProfile() {
		return studentProfile;
	}

	public void setStudentProfile(StudentProfile studentProfile) {
		this.studentProfile = studentProfile;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Student(String firstName, String lastName, String email, Integer age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
	}
	
	
	
	public Student(Integer id, String firstName, String lastName, String email, Integer age) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
	}

	public Student() {
		
	}
	
}
