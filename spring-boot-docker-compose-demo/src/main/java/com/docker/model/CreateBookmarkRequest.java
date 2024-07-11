package com.docker.model;

public record CreateBookmarkRequest(
        String title,
        String url) {
}
