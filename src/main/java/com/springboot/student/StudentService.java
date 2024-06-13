
package com.springboot.student;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class StudentService {

private StudentRepository studentRepo;
	
	private StudentMapper studentMapper;
	
	
	public StudentService(StudentRepository studentRepo, StudentMapper studentMapper) {
		this.studentRepo = studentRepo;
		this.studentMapper = studentMapper;
	}
	
	public StudentResponseDto saveStudent(StudentDto dto) {
		var student = studentMapper.toStudent(dto);
		var savedStudent =  studentRepo.save(student);
		
		return studentMapper.toStudentResponseDto(savedStudent);
	}
	
    public List<StudentResponseDto> findAllStudents() {
		
		return studentRepo.findAll()
				.stream()
				.map(studentMapper::toStudentResponseDto)
				.collect(Collectors.toList()); 
	}
    
    public StudentResponseDto findStudentById(@PathVariable Integer id) {
		return studentRepo.findById(id)
				.map(studentMapper::toStudentResponseDto)
				.orElse(null );
	}
    
    public List<StudentResponseDto> findAllByFirstname(@PathVariable String firstname) {
		return studentRepo.findAllByFirstNameContaining(firstname)
				.stream()
				.map(studentMapper::toStudentResponseDto)
				.collect(Collectors.toList());
	}
    
    public void delete(@PathVariable Integer id) {
		studentRepo.deleteById(id);
	}
	
}
