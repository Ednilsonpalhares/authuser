package com.ead.authuser.dataprovider.http.course;

import com.ead.authuser.communs.pagination.Page;
import com.ead.authuser.dataprovider.http.course.client.CourseFeignClient;
import com.ead.authuser.dataprovider.http.course.entity.CourseEntity;
import com.ead.authuser.dataprovider.http.course.mapper.CourseMapper;
import com.ead.authuser.domain.course.entity.Course;
import com.ead.authuser.domain.course.gateway.CourseGateway;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CourseGatewayImpl implements CourseGateway {

  private final CourseFeignClient courseFeignClient;
  private final CourseMapper courseMapper;

  @Override
  public Page<Course> findAllCoursesByUser(
      final String token, final UUID userId, final Pageable pageable) {

    final Page<CourseEntity> courseEntity =
        courseFeignClient.getAllCoursesByUser(token, userId, pageable);

    return courseMapper.pageCourseEntityToPageCourse(courseEntity);
  }
}
