package com.ead.authuser.dto;

import com.ead.authuser.dataprovider.http.user.entity.CourseLevel;
import com.ead.authuser.dataprovider.http.user.entity.CourseStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class CourseDTO {

    private UUID courseId;
    private String name;
    private String description;
    private String imageUrl;
    private CourseStatus courseStatus;
    private UUID userInstructor;
    private CourseLevel courseLevel;

}
