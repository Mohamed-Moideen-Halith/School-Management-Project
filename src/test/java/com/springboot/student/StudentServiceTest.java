package com.springboot.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class StudentServiceTest {

	@InjectMocks
	private StudentService studentService;

	@Mock
	private StudentRepository studentRepository;
	
	@Mock
	private StudentMapper studentMapper;
	
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void shouldSaveStudent() {
		
		//Given
		StudentDto dto = new StudentDto(
				"Mohamed",
				"Halith",
				"ummhalith@gmail.com",
				1);
		
		Student student = new Student(
				"Mohamed",
				"Halith",
				"ummhalith@gmail.com",
				21);
		
		Student savedStudent = new Student(
				"Mohamed",
				"Halith",
				"ummhalith@gmail.com",
				21);
		
		savedStudent.setId(1);
		
		//Mock the calls
		when(studentMapper.toStudent(dto)).thenReturn(student);
		
		when(studentRepository.save(student)).thenReturn(savedStudent);
		
		when(studentMapper.toStudentResponseDto(savedStudent)).thenReturn(new StudentResponseDto("Mohamed",
				"Halith",
				"ummhalith@gmail.com"));
		
		//when
		
		StudentResponseDto responseDto = studentService.saveStudent(dto);
		
		//Then 
		
		assertEquals(dto.firstName(),responseDto.firstName());
		assertEquals(dto.LastName(),responseDto.lastName());
		assertEquals(dto.email(),responseDto.email());
		
		verify(studentMapper,times(1)).toStudent(dto);
		verify(studentRepository,times(1)).save(student);
		verify(studentMapper,times(1)).toStudentResponseDto(savedStudent);
		
	}
	
	@Test
	public void ShouldReturnAllStudents() {
		
		List<Student> students = new ArrayList<>();
		students.add(new Student(
				"Mohamed",
				"Halith",
				"ummhalith@gmail.com",
				21
				));
		
		//Mock the calls
		
		when(studentRepository.findAll()).thenReturn(students);
		
		when(studentMapper.toStudentResponseDto(any(Student.class)))
		.thenReturn(new StudentResponseDto(
				"Mohamed",
				"Halith",
				"ummhalith@gmail.com"
				));
		
		//when
		List<StudentResponseDto> responseDtos = studentService.findAllStudents();
		
		//then
		
		assertEquals(students.size(),responseDtos.size());
		
		verify(studentRepository,times(1)).findAll();
		
		
	}
	
	@Test
	public void ShouldFindStudentById() {
		
		Student student = new Student(
				"Mohamed",
				"Halith",
				"ummhalith@gmail.com",
				21
				);
		student.setId(1);
		Integer studentId = 1;
		
		when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
		
		when(studentMapper.toStudentResponseDto(any(Student.class)))
		.thenReturn(new StudentResponseDto(
				"Mohamed",
				"Halith",
				"ummhalith@gmail.com"
				));
		
		
		StudentResponseDto responseDto = studentService.findStudentById(studentId);
		
		assertEquals(student.getFirstName(),responseDto.firstName());
		assertEquals(student.getLastName(),responseDto.lastName());
		assertEquals(student.getEmail(),responseDto.email());
		
		verify(studentRepository,times(1)).findById(studentId);
		
	}
	
	@Test
	public void shouldFindByFirstName() {
		
		List<Student> students = new ArrayList<>();
		Student student = new Student(
				"Mohamed",
				"Halith",
				"ummhalith@gmail.com",
				21
				);
		
		when(studentRepository.findAllByFirstNameContaining(student.getFirstName())).thenReturn(students);
		
		when(studentMapper.toStudentResponseDto(any(Student.class)))
		.thenReturn(new StudentResponseDto(
				"Mohamed",
				"Halith",
				"ummhalith@gmail.com"
				));
		
		List<StudentResponseDto> responseDto = studentService.findAllByFirstname(student.getFirstName());
		
		assertEquals(students.size(),responseDto.size());
		
		verify(studentRepository,times(1)).findAllByFirstNameContaining(student.getFirstName());
		
		
		
	}

}
