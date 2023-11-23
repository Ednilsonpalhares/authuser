package com.ead.authuser.entrypoint.http.course;

import com.ead.authuser.domain.user.usecase.FindAllCourserUseCase;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class UserCourseController {

  private final FindAllCourserUseCase findAllCourserUseCase;

  @PreAuthorize("hasAnyRole('STUDENT')")
  @GetMapping("/users/{userId}/courses")
  public ResponseEntity<Object> getAllCoursesByUser(
      @PageableDefault(page = 0, size = 10, sort = "courseId", direction = Sort.Direction.ASC)
          Pageable pageable,
      @PathVariable(value = "userId") UUID userId,
      @RequestHeader("Authorization") String token) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(findAllCourserUseCase.execute(token, userId, pageable));
  }
}
