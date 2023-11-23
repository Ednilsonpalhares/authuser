package com.ead.authuser.dataprovider.http.course;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ead.authuser.communs.pagination.Page;
import com.ead.authuser.comuns.util.TestUtils;
import com.ead.authuser.dataprovider.http.course.client.CourseFeignClient;
import com.ead.authuser.dataprovider.http.course.entity.CourseEntity;
import com.ead.authuser.dataprovider.http.course.mapper.CourseMapper;
import com.ead.authuser.domain.course.entity.Course;
import java.util.UUID;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class CourseGatewayImplTest {

  private static final String TOKEN = "token";
  private static final EasyRandom EASY_RANDOM = TestUtils.EASY_RANDOM;
  @Mock private CourseMapper courseMapper;
  @Mock private CourseFeignClient courseFeignClient;
  @InjectMocks private CourseGatewayImpl courseGateway;

  @Test
  void shouldReturnPageCourse() {
    final Course course = EASY_RANDOM.nextObject(Course.class);

    final Page<Course> pageCourseEntity = Page.<Course>builder().data(course).build();

    when(courseFeignClient.getAllCoursesByUser(any(), any(), any()))
        .thenReturn(Page.<CourseEntity>builder().data(CourseEntity.builder().build()).build());
    when(courseMapper.pageCourseEntityToPageCourse(any()))
        .thenReturn(Page.<Course>builder().build());

    final Pageable pageable = mock(Pageable.class);
    final Page<Course> pageCourse =
        courseGateway.findAllCoursesByUser(TOKEN, UUID.randomUUID(), pageable);

    assertEquals(pageCourseEntity.getData().getCourseId(), pageCourse.getData().getCourseId());
  }
}
