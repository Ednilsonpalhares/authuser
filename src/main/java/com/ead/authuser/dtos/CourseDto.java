package com.ead.authuser.dtos;

import com.ead.authuser.dataprovider.http.user.entity.CourseLevel;
import com.ead.authuser.dataprovider.http.user.entity.CourseStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class CourseDto {

    private UUID courseId;
    private String name;
    private String description;
    private String imageUrl;
    private CourseStatus courseStatus;
    private UUID userInstructor;
    private CourseLevel courseLevel;

}
