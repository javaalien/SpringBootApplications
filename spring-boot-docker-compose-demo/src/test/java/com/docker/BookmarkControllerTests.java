package com.docker;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("/test-data.sql")
public class BookmarkControllerTests {

	@LocalServerPort
	int port;

	@BeforeEach
	void setUp() {
		RestAssured.port = port;
	}

	@Test
	void shouldGetAllBookmarks() {
		given().when().get("/api/bookmarks").then().statusCode(200).body("size()", is(6));
	}

	@Test
	void shouldCreateBookmark() {
		given().contentType("application/json").body("""
				        {
				            "title":"test bookmark",
				            "url":"https://test.com",
				            "description":"test bookmark"
				        }
				""").when().post("/api/bookmarks").then().statusCode(201);
	}
}
