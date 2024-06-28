package com.swagger.bookmarks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bookmarks")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Bookmark {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookmark_id_generator")
	@SequenceGenerator(name = "bookmark_id_generator", sequenceName = "bookmark_id_seq")
	private Long id;

	@Column(nullable = false)
	@NotEmpty(message = "Title can't be empty")
	private String title;

	@Column(nullable = false)
	@NotEmpty(message = "URL can't be empty")
	private String url;

}
