package com.ead.authuser.dataprovider.http.course.entity;

import com.ead.authuser.dataprovider.http.user.entity.CourseLevel;
import com.ead.authuser.dataprovider.http.user.entity.CourseStatus;
import java.util.UUID;

import lombok.Data;

@Data
public class CourseEntity {

    private UUID courseId;
    private String name;
    private String description;
    private String imageUrl;
    private CourseStatus courseStatus;
    private UUID userInstructor;
    private CourseLevel courseLevel;
}
