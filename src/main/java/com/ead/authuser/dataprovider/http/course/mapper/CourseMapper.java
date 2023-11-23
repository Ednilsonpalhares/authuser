package com.ead.authuser.dataprovider.http.course.mapper;

import com.ead.authuser.communs.pagination.Page;
import com.ead.authuser.dataprovider.http.course.entity.CourseEntity;
import com.ead.authuser.domain.course.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CourseMapper {

  Page<Course> pageCourseEntityToPageCourse(Page<CourseEntity> courseEntity);
}
