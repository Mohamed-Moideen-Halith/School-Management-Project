package com.springboot.school;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchoolController {

	
	private SchoolService schoolService;

	public SchoolController(SchoolService schoolService) {
		super();
		this.schoolService = schoolService;
	}



	@PostMapping("/schools")
	public SchoolDto create(@RequestBody SchoolDto dto) {
		return schoolService.create(dto);
	}

	
	
	@GetMapping("/schools")
	public List<SchoolDto> getSchools() {
		return schoolService.getSchools();	
	}
	
}
