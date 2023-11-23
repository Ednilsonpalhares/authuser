package com.ead.authuser.domain.course.gateway;

import com.ead.authuser.communs.pagination.Page;
import com.ead.authuser.domain.course.entity.Course;
import java.util.UUID;
import org.springframework.data.domain.Pageable;

public interface CourseGateway {

  Page<Course> findAllCoursesByUser(String token, UUID userId, Pageable pageable);
}
