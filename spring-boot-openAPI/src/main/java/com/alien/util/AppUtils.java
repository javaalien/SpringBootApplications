package com.alien.util;

import com.alien.dto.CourseRequestDTO;
import com.alien.dto.CourseResponseDTO;
import com.alien.entity.CourseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AppUtils {

	public static CourseEntity mapDTOToEntity(CourseRequestDTO courseRequestDTO) {
		CourseEntity courseEntity = new CourseEntity();

		courseEntity.setName(courseRequestDTO.getName());
		courseEntity.setTrainerName(courseRequestDTO.getTrainerName());
		courseEntity.setDuration(courseRequestDTO.getDuration());
		courseEntity.setStartDate(courseRequestDTO.getStartDate());
		courseEntity.setCourseType(courseRequestDTO.getCourseType());
		courseEntity.setFees(courseRequestDTO.getFees());
		courseEntity.setCertificateAvailable(courseRequestDTO.isCertificateAvailable());
		courseEntity.setDescription(courseRequestDTO.getDescription());
		courseEntity.setEmailId(courseRequestDTO.getEmailId());
		courseEntity.setContact(courseRequestDTO.getContact());

		return courseEntity;

	}

	public static CourseResponseDTO mapEntityToDTO(CourseEntity courseEntity) {
		CourseResponseDTO courseResponseDTO = new CourseResponseDTO();
		courseResponseDTO.setCourseId(courseEntity.getCourseId());
		courseResponseDTO.setName(courseEntity.getName());
		courseResponseDTO.setTrainerName(courseEntity.getTrainerName());
		courseResponseDTO.setDuration(courseEntity.getDuration());
		courseResponseDTO.setStartDate(courseEntity.getStartDate());
		courseResponseDTO.setCourseType(courseEntity.getCourseType());
		courseResponseDTO.setFees(courseEntity.getFees());
		courseResponseDTO.setCertificateAvailable(courseEntity.isCertificateAvailable());
		courseResponseDTO.setDescription(courseEntity.getDescription());
		courseResponseDTO.setEmailId(courseEntity.getEmailId());
		courseResponseDTO.setContact(courseEntity.getContact());

		return courseResponseDTO;
	}

	public static String convertObjectToJson(Object object) {
		try {
			return new ObjectMapper().writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
