package com.example.openapi.mapper;

import com.example.openapi.entity.Course;
import com.example.openapi.model.CourseDTO;

import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

	public CourseDTO toDto(Course course) {
		return CourseDTO.builder()
				.id(course.getId())
				.name(course.getName())
				.description(course.getDescription())
				.price(course.getPrice())
				.build();
	}

	public Course fromDto(CourseDTO dto) {
		return new Course(dto.getId(), dto.getName(), dto.getDescription(), dto.getPrice());
	}

}
