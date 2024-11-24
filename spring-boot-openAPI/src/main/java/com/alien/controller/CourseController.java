package com.alien.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alien.dto.CourseRequestDTO;
import com.alien.dto.CourseResponseDTO;
import com.alien.dto.ServiceResponse;
import com.alien.service.CourseService;
import com.alien.util.AppUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/course")
@Slf4j
public class CourseController {

	private CourseService courseService;

	public CourseController(CourseService courseService) {
		this.courseService = courseService;
	}

	@Operation(summary = "add a new course to the system")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "course added successfully", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = CourseResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Validation error") })
	@PostMapping
	public ServiceResponse<CourseResponseDTO> addNewCourse(@RequestBody @Valid CourseRequestDTO courseRequestDTO) {

		log.info("CourseController:addCourse Request payload : {}", AppUtils.convertObjectToJson(courseRequestDTO));

		ServiceResponse<CourseResponseDTO> serviceResponse = new ServiceResponse<>();

		CourseResponseDTO newCourse = courseService.onboardNewCourse(courseRequestDTO);

		serviceResponse.setStatus(HttpStatus.CREATED);
		serviceResponse.setResponse(newCourse);

		log.info("CourseController:addCourse response  : {}", AppUtils.convertObjectToJson(serviceResponse));
		return serviceResponse;
	}

	@GetMapping
	@Operation(summary = "Fetch all course object")
	public ServiceResponse<List<CourseResponseDTO>> findALlCourse() {
		List<CourseResponseDTO> courseResponseDTOS = courseService.viewAllCourses();
		return new ServiceResponse<>(HttpStatus.OK, courseResponseDTOS);
	}

	@Operation(summary = "Find course by courseId")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "course found", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = CourseResponseDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "course not found with given id") })
	@GetMapping("/search/path/{courseId}")
	public ServiceResponse<CourseResponseDTO> findCourse(@PathVariable Integer courseId) {
		CourseResponseDTO responseDTO = courseService.findByCourseId(courseId);
		return new ServiceResponse<>(HttpStatus.OK, responseDTO);
	}

	@Operation(summary = "Find course by courseId using RequestParam")
	@GetMapping("/search/request")
	public ServiceResponse<CourseResponseDTO> findCourseUsingRequestParam(
			@RequestParam(required = false) Integer courseId) {
		CourseResponseDTO responseDTO = courseService.findByCourseId(courseId);
		return new ServiceResponse<>(HttpStatus.OK, responseDTO);
	}

	@Operation(summary = "Delete course by courseId")
	@DeleteMapping("/{courseId}")
	public ResponseEntity<?> deleteCourse(@PathVariable int courseId) {
		log.info("CourseController:deleteCourse deleting a course with id {}", courseId);
		courseService.deleteCourse(courseId);
		return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
	}

	@Operation(summary = "update the course in system")
	@PutMapping("/{courseId}")
	public ServiceResponse<CourseResponseDTO> updateCourse(@PathVariable int courseId,
			@RequestBody CourseRequestDTO courseRequestDTO) {
		log.info("CourseController:updateCourse Request payload : {} and {}",
				AppUtils.convertObjectToJson(courseRequestDTO), courseId);
		CourseResponseDTO courseResponseDTO = courseService.updateCourse(courseId, courseRequestDTO);
		ServiceResponse<CourseResponseDTO> response = new ServiceResponse<>(HttpStatus.OK, courseResponseDTO);
		log.info("CourseController:updateCourse response body : {}", AppUtils.convertObjectToJson(response));
		return response;
	}

	private void validateRequestPayload(CourseRequestDTO courseRequestDTO) {
		if (courseRequestDTO.getDuration() == null || courseRequestDTO.getDuration().isEmpty()) {
			throw new RuntimeException("duration field must need to be pass");
		}
		if (courseRequestDTO.getFees() == 0) {
			throw new RuntimeException("fees must be provided");
		}
	}

	@GetMapping("/log")
	public String loggingLevel() {
		log.trace("trace message");
		log.debug("debug message");
		log.info("info message");
		log.warn("warn message");
		log.error("error message");
		return "log printed in console";
	}
}
