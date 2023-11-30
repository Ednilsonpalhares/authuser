package com.ead.authuser.entrypoint.http.user;

import com.ead.authuser.domain.user.entity.User;
import com.ead.authuser.domain.user.usecase.InstructorUseCase;
import com.ead.authuser.dto.InstructorDTO;
import com.ead.authuser.entrypoint.http.user.dto.response.UserResponseDTO;
import com.ead.authuser.entrypoint.http.user.mapper.UserEntrypointMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/instructors")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorUseCase instructorUseCase;
    private final UserEntrypointMapper userMapper;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/subscription")
    public ResponseEntity<UserResponseDTO> saveSubscriptionInstructor(
            @RequestBody @Valid InstructorDTO instructorDto) {
        final User user = instructorUseCase.execute(instructorDto.getUserId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(userMapper.userToUserResponseDTO(user));
    }
}
