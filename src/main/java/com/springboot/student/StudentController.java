package com.springboot.student;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class StudentController {

	
	private StudentService studentService;
	

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}


	@PostMapping("/students")
	public StudentResponseDto post(@Valid @RequestBody StudentDto dto) {
	return studentService.saveStudent(dto);
	}

	
	@GetMapping("students/{id}")
	public StudentResponseDto findStudentById(@PathVariable Integer id) {
		return studentService.findStudentById(id);
	}
	
	
	@GetMapping("/students")
	public List<StudentResponseDto> findAllStudents() {
		
		return studentService.findAllStudents(); 
	}
	
	@GetMapping("students/search/{firstname}")
	public List<StudentResponseDto> findAllByFirstname(@PathVariable String firstname) {
		return studentService.findAllByFirstname(firstname);
	}

	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable Integer id) {
		studentService.delete(id);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleExceptions(MethodArgumentNotValidException exception){
		var errors = new HashMap<String,String>();
		exception.getBindingResult().getAllErrors()
		.forEach(error ->{
			var fieldName = ((FieldError) error).getField();
			var errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
}
