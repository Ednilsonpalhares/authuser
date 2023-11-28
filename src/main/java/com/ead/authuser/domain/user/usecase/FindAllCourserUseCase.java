package com.ead.authuser.domain.user.usecase;

import com.ead.authuser.communs.pagination.Page;
import com.ead.authuser.domain.course.entity.Course;
import com.ead.authuser.domain.course.gateway.CourseGateway;
import com.ead.authuser.domain.exception.NotFoundException;
import com.ead.authuser.domain.user.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindAllCourserUseCase {

    private final UserGateway userGateway;
    private final CourseGateway courseGateway;

    public Page<Course> execute(final String token, final UUID uuid, final Pageable pageable) {
        if (isNull(userGateway.findById(uuid))) {
            throw new NotFoundException("User not found", HttpStatus.NOT_FOUND.value());
        }
        return courseGateway.findAllCoursesByUser(token, uuid, pageable);
    }
}
