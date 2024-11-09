package com.escamilla.courseinfo.repository;

import com.escamilla.courseinfo.domain.Course;

import java.util.List;

public interface CourseRepository {
    /**
     * Store a new course if the id does not exist, if the id is already in the
     * database, it will update the record.
     * @param course Course object to store
     */
    void saveCourse(Course course);

    /**
     * Retrieve all the courses stored in the database as an unmodifiable list
     * of Courses
     * @return List<Course>
     */
    List<Course> getAllCourses();

    static CourseRepository openCourseRepository(String databaseFile) {
        return new CourseJdbcRepository(databaseFile);
    }
}
