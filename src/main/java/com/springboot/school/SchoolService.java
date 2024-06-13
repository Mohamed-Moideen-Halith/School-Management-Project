package com.springboot.school;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class SchoolService {

private SchoolRepository schoolRepository;
	
	private SchoolMapper schoolMapper;
	
	public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
		super();
		this.schoolRepository = schoolRepository;
		this.schoolMapper = schoolMapper;
	}
	
	public SchoolDto create(@RequestBody SchoolDto dto) {
		var school = schoolMapper.toSchool(dto);
		schoolRepository.save(school);
		return dto;
	}
	
	public List<SchoolDto> getSchools() {
		return schoolRepository.findAll()
				.stream()
				.map(schoolMapper::toSchoolDto)
				.collect(Collectors.toList());
	}
	
}
