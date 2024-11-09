package com.escamilla.courseinfo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    @Test
    void testExpectedExceptionIsThrown() {
        String id = "",
                name = "unit testing course",
                url = "http://localhost:8080";
        assertThrows(IllegalArgumentException.class, () -> {
            new Course(id, name, 19, url);
        });
    }
}