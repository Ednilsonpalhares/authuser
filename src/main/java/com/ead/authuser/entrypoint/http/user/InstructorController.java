package com.ead.authuser.entrypoint.http.user;

import com.ead.authuser.dtos.InstructorDto;
import com.ead.authuser.dataprovider.http.user.entity.UserTypeEntityEnum;
import com.ead.authuser.dataprovider.http.role.entity.RoleEntity;
import com.ead.authuser.dataprovider.http.user.entity.UserEntity;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/instructors")
public class InstructorController {

    //@Autowired
 //   RoleService roleService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/subscription")
    public ResponseEntity<Object> saveSubscriptionInstructor(@RequestBody @Valid InstructorDto instructorDto) {
       /* Optional<UserEntity> userModelOptional = userService.findById(instructorDto.getUserId());
        if(!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }else {
            RoleEntity roleModel = null; *//*roleService.findByRoleName(RoleType.ROLE_INSTRUCTOR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is Not Found."));*//*
            var userModel = userModelOptional.get();
            userModel.setUserType(UserTypeEntityEnum.INSTRUCTOR);
            userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
            userModel.getRoles().add(roleModel);
            userService.updateUser(userModel);
            return ResponseEntity.status(HttpStatus.OK).body(userModel);
            }*/
        return null;
    }
}
