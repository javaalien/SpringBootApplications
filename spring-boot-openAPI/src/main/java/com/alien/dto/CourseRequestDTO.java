package com.alien.dto;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.alien.annotation.CourseTypeValidation;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDTO {

	@NotBlank(message = "Course name shouldn't be NULL OR EMPTY")
	private String name;
	@NotEmpty(message = "Trainer name should be define")
	private String trainerName;
	@NotNull(message = "duration must need to specify")
	private String duration;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Past(message = "start date can't be before date from current")
	private Date startDate;

	@CourseTypeValidation
	private String courseType; // Live OR Recodring

	@Min(value = 1500, message = "course price can't be less than 1500")
	@Max(value = 5000, message = "course price can't be more than 5000")
	private double fees;

	private boolean isCertificateAvailable;

	@NotEmpty(message = "description must be present")
	@Length(min = 5, max = 10)
	private String description;
	@Email(message = "invalid email id")
	private String emailId;
	@Pattern(regexp = "^[0-9]{10}$")
	private String contact;

}
