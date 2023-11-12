package com.ead.authuser.dataprovider.http.course;

import java.util.UUID;

import com.ead.authuser.communs.pagination.Page;
import com.ead.authuser.domain.course.entity.Course;
import org.springframework.data.domain.Pageable;

public interface CourseGateway {

  Page<Course> findAllCoursesByUser(String token, UUID userId, Pageable pageable);
}
