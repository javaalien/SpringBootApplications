package com.swagger.bookmarks;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Bookmarks")
public class TagController {

    @GetMapping("/api/tags")
    public void handle() {

    }
}