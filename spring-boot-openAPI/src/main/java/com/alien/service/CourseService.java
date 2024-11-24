package com.alien.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alien.dao.CourseDao;
import com.alien.dto.CourseRequestDTO;
import com.alien.dto.CourseResponseDTO;
import com.alien.entity.CourseEntity;
import com.alien.exception.CourseServiceBusinessException;
import com.alien.util.AppUtils;

@Service
public class CourseService {

	@Autowired
	private CourseDao courseDao;

	Logger log = LoggerFactory.getLogger(CourseService.class);

	public CourseResponseDTO onboardNewCourse(CourseRequestDTO courseRequestDTO) {
		CourseEntity courseEntity = AppUtils.mapDTOToEntity(courseRequestDTO);
		CourseEntity entity = null;
		log.info("CourseService::onboardNewCourse method execution started.");

		try {
			entity = courseDao.save(courseEntity);
			log.debug("course entity response from Database {} ", AppUtils.convertObjectToJson(entity));
			CourseResponseDTO courseResponseDTO = AppUtils.mapEntityToDTO(entity);
			courseResponseDTO.setCourseUniqueCode(UUID.randomUUID().toString().split("-")[0]);
			log.debug("CourseService::onboardNewCourse method response {} ",
					AppUtils.convertObjectToJson(courseResponseDTO));
			log.info("CourseService::onboardNewCourse method execution ended.");

			return courseResponseDTO;

		} catch (Exception e) {
			log.error("CourseService::onboardNewCourse exception occurs while persisting the course object to DB");
			throw new CourseServiceBusinessException("onboardNewCourse service method failed..");
		}
	}

	public List<CourseResponseDTO> viewAllCourses() {
		Iterable<CourseEntity> courseEntities = null;

		try {
			courseEntities = courseDao.findAll();
			return StreamSupport.stream(courseEntities.spliterator(), false).map(AppUtils::mapEntityToDTO)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new CourseServiceBusinessException("viewAllCourses service method failed..");
		}
	}

	public CourseResponseDTO findByCourseId(Integer courseId) {
		CourseEntity courseEntity = courseDao.findById(courseId)
				.orElseThrow(() -> new CourseServiceBusinessException(courseId + " doesn't exist in system"));
		return AppUtils.mapEntityToDTO(courseEntity);
	}

	public void deleteCourse(int courseId) {
		log.info("CourseService::deleteCourse method execution started ..");
		try {
			log.debug("CourseService::deleteCourse method input {}", courseId);
			courseDao.deleteById(courseId);
		} catch (Exception ex) {
			log.error("CourseService::deleteCourse exception occurs while deleting the course object {} ",
					ex.getMessage());
			throw new CourseServiceBusinessException("deleteCourse service method failed.." + ex.getMessage());
		}
		log.info("CourseService::deleteCourse method execution ended ..");
	}

	public CourseResponseDTO updateCourse(int courseId, CourseRequestDTO courseRequestDTO) {
		log.info("CourseService::updateCourse method execution started ..");

		try {

			log.debug("CourseService::updateCourse request payload {} & id {} ",
					AppUtils.convertObjectToJson(courseRequestDTO), courseId);
			CourseEntity existingCourseEntity = courseDao.findById(courseId)
					.orElseThrow(() -> new RuntimeException("course object not present in db with id " + courseId));

			log.debug("CourseService::updateCourse getting existing course object as {} ",
					AppUtils.convertObjectToJson(existingCourseEntity));

			existingCourseEntity.setName(courseRequestDTO.getName());
			existingCourseEntity.setTrainerName(courseRequestDTO.getTrainerName());
			existingCourseEntity.setDuration(courseRequestDTO.getDuration());
			existingCourseEntity.setStartDate(courseRequestDTO.getStartDate());
			existingCourseEntity.setCourseType(courseRequestDTO.getCourseType());
			existingCourseEntity.setFees(courseRequestDTO.getFees());
			existingCourseEntity.setCertificateAvailable(courseRequestDTO.isCertificateAvailable());
			existingCourseEntity.setDescription(courseRequestDTO.getDescription());

			CourseEntity updatedCourseEntity = courseDao.save(existingCourseEntity);

			CourseResponseDTO courseResponseDTO = AppUtils.mapEntityToDTO(updatedCourseEntity);

			log.debug("CourseService::updateCourse getting updated course object as {} ",
					AppUtils.convertObjectToJson(courseResponseDTO));
			log.info("CourseService::updateCourse method execution ended ..");

			return courseResponseDTO;
		} catch (Exception ex) {
			log.error("CourseService::updateCourse exception occurs while updating the course object {} ",
					ex.getMessage());
			throw new CourseServiceBusinessException("updateCourse service method failed.." + ex.getMessage());
		}
	}

}
