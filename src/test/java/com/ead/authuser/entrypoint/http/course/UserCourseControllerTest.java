package com.ead.authuser.entrypoint.http.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoSettings;

@MockitoSettings
class UserCourseControllerTest {

  @InjectMocks private UserCourseController userCourseController;

  @Test
  void givenValidPlaceIdAndEventIdShouldReturnEventDetailDtoWithSuccess() {

    //userCourseController.getAllCoursesByUser(null);
  }
}
