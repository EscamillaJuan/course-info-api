package com.escamilla.courseinfo.cli.service;

import com.escamilla.courseinfo.domain.Course;
import com.escamilla.courseinfo.repository.CourseRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseStorageServiceTest {

    @Test
    void storePluralsightCourses() {
        CourseRepository repository = new InMemoryRepository();
        CourseStorageService courseStorageService = new CourseStorageService(repository);
        PluralsightCourse ps1 = new PluralsightCourse("1", "Title",
                "01:40:00.231", "/url", false);
        courseStorageService.storePluralsightCourses(List.of(ps1));

        Course expected = new Course("1", "Title",
                100, "https://app.pluralsight.com/url");
        assertEquals(List.of(expected), repository.getAllCourses());
    }

    static class InMemoryRepository implements CourseRepository {
        private final List<Course> courses = new ArrayList<>();

        @Override
        public void saveCourse(Course course) {
            courses.add(course);
        }

        @Override
        public List<Course> getAllCourses() {
            return courses;
        }
    }
}