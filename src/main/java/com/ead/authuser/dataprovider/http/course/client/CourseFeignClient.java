package com.ead.authuser.dataprovider.http.course.client;

import com.ead.authuser.communs.configs.feign.FeignConfig;
import com.ead.authuser.communs.pagination.Page;
import com.ead.authuser.dataprovider.http.course.entity.CourseEntity;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    name = "course-client",
    url = "${spring.cloud.openfeign.client.config.course.baseUrl}",
    configuration = FeignConfig.class)
public interface CourseFeignClient {

  @GetMapping
  Page<CourseEntity> getAllCoursesByUser(
      @RequestHeader("Authorization") String token,
      @RequestParam UUID userId,
      @RequestParam Pageable pageable);
}
