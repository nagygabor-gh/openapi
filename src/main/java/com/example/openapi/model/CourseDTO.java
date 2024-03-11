package com.example.openapi.model;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Schema(description = "Course data for customers")
public class CourseDTO {

	@Schema(name = "id",
			description = "Unique course identifier",
			implementation = Integer.class,
			requiredMode = RequiredMode.REQUIRED)
	@NotNull
	private Integer id;

	@Schema(name = "name",
			description = "Course name",
			implementation = String.class,
			requiredMode = RequiredMode.NOT_REQUIRED)
	private String name;

	@Schema(name = "description",
			description = "Course details and summary",
			implementation = String.class,
			requiredMode = RequiredMode.NOT_REQUIRED)
	private String description;

	@Schema(name = "price",
			description = "Course price",
			implementation = Long.class,
			requiredMode = RequiredMode.NOT_REQUIRED)
	private Long price;

}
