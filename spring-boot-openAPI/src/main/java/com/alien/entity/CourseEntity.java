package com.alien.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "COURSE_TBL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int courseId;
	private String name;
	private String trainerName;
	private String duration;
	private Date startDate;
	private String courseType;
	private double fees;
	private boolean isCertificateAvailable;
	private String description;
	private String emailId;
	private String contact;
}
