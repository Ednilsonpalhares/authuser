package com.ead.authuser.domain.user.usecase;

import static java.util.Objects.isNull;

import com.ead.authuser.communs.pagination.Page;
import com.ead.authuser.domain.course.entity.Course;
import com.ead.authuser.domain.course.gateway.CourseGateway;
import com.ead.authuser.domain.exception.NotFoundException;
import com.ead.authuser.domain.user.gateway.UserGateway;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindAllCourserUseCase {

  private final UserGateway userGateway;
  private final CourseGateway courseGateway;

  public Page<Course> execute(final String token, final UUID uuid, final Pageable pageable) {
    if (isNull(userGateway.findBUuid(uuid))) {
      throw new NotFoundException("User not found", HttpStatus.NOT_FOUND.value());
    }
    return courseGateway.findAllCoursesByUser(token, uuid, pageable);
  }
}
