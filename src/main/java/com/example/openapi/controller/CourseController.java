package com.example.openapi.controller;

import com.example.openapi.exception.NoSuchCourseException;
import com.example.openapi.model.CourseDTO;
import com.example.openapi.service.CourseService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
@Tag(name = "/api/courses", description = "Course API")
public class CourseController {

	private final CourseService service;

	@Operation(summary = "Retrieve all courses.", description = "Get all courses available.",
			   security = {@SecurityRequirement(name = "MB auth")})
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successful operation.",
				content = { @Content(array = @ArraySchema(schema = @Schema(implementation = CourseDTO.class)),
						mediaType = "application/json") }),
		@ApiResponse(responseCode = "500", description = "Server side error.",
				content = { @Content(schema = @Schema()) })
	})
	@GetMapping
	public ResponseEntity<List<CourseDTO>> getCourses() {
		return ResponseEntity.ok(service.getAllCourses());
	}

	@Operation(summary = "Retrieve a course by id.",
			   description = "Get a course by specifying its id.",
			   parameters = {
					   @Parameter(description = "Course identifier in database.", in = ParameterIn.PATH, name = "id",
							   example = "1", required = true, schema = @Schema())
			   },
			   security = {@SecurityRequirement(name = "MB auth")})
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successful operation",
				content = { @Content(schema = @Schema(implementation = CourseDTO.class),
						mediaType = "application/json")} ),
		@ApiResponse(responseCode = "400", description = "Invalid course id.",
				content = { @Content(schema = @Schema()) }),
		@ApiResponse(responseCode = "404", description = "Course not found.",
				content = { @Content(schema = @Schema()) }),
		@ApiResponse(responseCode = "500", description = "Server side error.",
				content = { @Content(schema = @Schema()) })
	})
	@GetMapping("/{id}")
	public ResponseEntity<CourseDTO> getCourse(@PathVariable("id") Integer id) {
		try {
			CourseDTO dto = service.getCourseById(id);
			return ResponseEntity.ok(dto);
		} catch (NoSuchCourseException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@Operation(summary = "Add a new course definition.", description = "Add a course with all details, without an id.",
			   security = {@SecurityRequirement(name = "MB auth")})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation.",
					content = { @Content(schema = @Schema(implementation = CourseDTO.class),
							mediaType = "application/json")} ),
			@ApiResponse(responseCode = "400", description = "Data validation failed.",
					content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", description = "Server side error.",
					content = { @Content(schema = @Schema()) })
	})
	@PostMapping
	public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO course) {
		return ResponseEntity.ok(service.addCourse(course));
	}

	@Operation(summary = "Update a course definition.", description = "Amend details of a course based on its id.",
			   security = {@SecurityRequirement(name = "MB auth")})
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation.",
					content = { @Content(schema = @Schema(implementation = CourseDTO.class),
							mediaType = "application/json")} ),
			@ApiResponse(responseCode = "400", description = "Data validation failed.",
					content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", description = "Server side error.",
					content = { @Content(schema = @Schema()) })
	})
	@PutMapping
	public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO course) {
		return ResponseEntity.ok(service.updateCourse(course));
	}

}
