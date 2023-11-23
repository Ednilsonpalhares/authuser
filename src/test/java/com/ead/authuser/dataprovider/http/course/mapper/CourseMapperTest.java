package com.ead.authuser.dataprovider.http.course.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.ead.authuser.communs.pagination.Page;
import com.ead.authuser.comuns.util.TestUtils;
import com.ead.authuser.dataprovider.http.course.entity.CourseEntity;
import com.ead.authuser.domain.course.entity.Course;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CourseMapperTest {

  private static final EasyRandom EASY_RANDOM = TestUtils.EASY_RANDOM;
  private final CourseMapper mapper = Mappers.getMapper(CourseMapper.class);

  @Test
  void shouldPageCourseEntity() {
    final CourseEntity courseEntity = EASY_RANDOM.nextObject(CourseEntity.class);
    final Page<CourseEntity> pageCourseEntity =
        Page.<CourseEntity>builder().data(courseEntity).build();

    final Page<Course> pageCourse = mapper.pageCourseEntityToPageCourse(pageCourseEntity);
    final Course course = pageCourse.getData();

    assertEquals(courseEntity.getCourseId(), course.getCourseId());
    assertEquals(courseEntity.getName(), course.getName());
    assertEquals(courseEntity.getDescription(), course.getDescription());
    assertEquals(courseEntity.getImageUrl(), course.getImageUrl());
    assertEquals(courseEntity.getCourseStatus(), course.getCourseStatus());
    assertEquals(courseEntity.getUserInstructor(), course.getUserInstructor());
    assertEquals(courseEntity.getCourseLevel(), course.getCourseLevel());
  }
}
