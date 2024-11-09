package com.escamilla.courseinfo.cli.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Duration;
import java.time.LocalTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PluralsightCourse(
        String id,
        String title,
        String duration,
        String contentUrl,
        boolean isRetired
) {
    // It will break for more than 24 hours
    public long durationInMinutes() {
        return Duration.between(
                LocalTime.MIN, // 00:00:00
                LocalTime.parse(duration())
        ).toMinutes();
    }
}
