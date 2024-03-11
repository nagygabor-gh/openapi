package com.example.openapi.service;

import com.example.openapi.entity.Course;
import com.example.openapi.exception.NoSuchCourseException;
import com.example.openapi.mapper.CourseMapper;
import com.example.openapi.model.CourseDTO;
import com.example.openapi.repository.CourseRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {

	private final CourseRepository repository;
	private final CourseMapper mapper;

	public List<CourseDTO> getAllCourses() {
		return StreamSupport.stream(repository.findAll().spliterator(), false)
				.map(mapper::toDto)
				.toList();
	}

	public CourseDTO getCourseById(Integer id) throws NoSuchCourseException {
		return repository.findById(id)
				.map(mapper::toDto)
				.orElseThrow(() -> new NoSuchCourseException("No course with id " + id));
	}

	public CourseDTO addCourse(CourseDTO course) {
		Course savedCourse = repository.save(mapper.fromDto(course));
		return mapper.toDto(savedCourse);
	}

	public CourseDTO updateCourse(CourseDTO dto) {
		Optional<Course> existingCourse = repository.findById(dto.getId());
		CourseDTO savedDto;
		if (existingCourse.isPresent()) {
			Course course = existingCourse.get();
			course.setName(dto.getName());
			course.setDescription(dto.getDescription());
			course.setPrice(dto.getPrice());
			Course savedCourse = repository.save(course);
			savedDto = mapper.toDto(savedCourse);
		} else {
			savedDto = addCourse(dto);
		}
		return savedDto;
	}

}
