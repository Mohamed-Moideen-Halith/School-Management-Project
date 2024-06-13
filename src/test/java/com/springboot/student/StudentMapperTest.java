package com.springboot.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class StudentMapperTest {


	private StudentMapper studentMapper;
	@BeforeEach
	void setUp() {
		studentMapper = new StudentMapper();
		
	}
	
	
	@Test
	public void shouldMapStudentDtoToStudent() {
	StudentDto dto = new StudentDto("Mohamed",
			"Halith",
			"ummhalith@gmail.com",
			1);
	
	Student student = studentMapper.toStudent(dto);
	
	assertEquals(dto.firstName(), student.getFirstName());
	assertEquals(dto.LastName(), student.getLastName());
	assertEquals(dto.email(), student.getEmail());
	assertNotNull(student.getSchool());
	assertEquals(dto.SchoolId(), student.getSchool().getId());
		
	}
	
	public void shouldMapStudentToStudentDto() {
		Student student = new Student(
				"moideen",
				"halith2",
				"halith@gmail.com",
				21
				);
		
		StudentResponseDto dto = studentMapper.toStudentResponseDto(student);
		
		assertEquals(student.getFirstName(),dto.firstName());
		assertEquals(student.getLastName(),dto.lastName());
		assertEquals(student.getEmail(),dto.email());
		
		
	}

}
