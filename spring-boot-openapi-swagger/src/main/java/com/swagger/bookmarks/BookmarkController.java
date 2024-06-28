package com.swagger.bookmarks;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwtBearerAuth")
@Tag(name = "Bookmarks")
public class BookmarkController {

	private final BookmarkService bookmarkService;

	@GetMapping 			//http://localhost:8080/api/bookmarks
	public List<Bookmark> getAllBookmarks() {
		return bookmarkService.getAllBookmarks();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Bookmark> getBookmarkById(@PathVariable Long id) {
		Bookmark bookmark = bookmarkService.getBookmarkById(id);
		return ResponseEntity.ok(bookmark);
	}

	@PostMapping				//http://localhost:8080/api/bookmarks
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	 @Operation(description = "Create a new bookmark", security =
	 @SecurityRequirement(name = "jwtBearerAuth"))
	@ApiResponses(value = { @ApiResponse(description = "Successfully created new bookmark", responseCode = "201"),
			@ApiResponse(description = "Forbidden", responseCode = "403"), })
	public Bookmark createBookmark(@RequestBody @Valid Bookmark bookmark) {
		return bookmarkService.createBookmark(bookmark);
	}
}
