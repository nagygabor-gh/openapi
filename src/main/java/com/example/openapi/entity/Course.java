package com.example.openapi.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@KeySpace("course")
@Getter
@Setter
@AllArgsConstructor
public class Course {

	@Id
	private Integer id;

	private String name;

	private String description;

	private Long price;

}
