package com.escamilla.courseinfo.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    @Test
    void testExpectedExceptionIsThrown() {
        String id = "",
                name = "unit testing course",
                url = "http://localhost:8080";
        assertThrows(IllegalArgumentException.class, () -> {
            new Course(id, name, 19, url, Optional.empty());
        });
    }

    @Test
    void rejectBlankNotes() {
        assertThrows(IllegalArgumentException.class, () ->
                new Course("1", "t", 1, "url", Optional.of("")));
    }
}