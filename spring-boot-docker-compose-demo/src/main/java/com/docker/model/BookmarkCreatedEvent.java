package com.docker.model;

import java.time.Instant;

public record BookmarkCreatedEvent(
        String eventId,
        String title,
        String url,
        Instant createdAt) {
}
