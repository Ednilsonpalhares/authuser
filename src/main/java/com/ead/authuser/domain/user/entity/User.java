package com.ead.authuser.domain.user.entity;

import com.ead.authuser.domain.role.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private UUID id;
    private String username;
    private String email;
    private String password;
    private String fullName;
    private UserStatusEnum userStatus;
    private UserTypeEnum userType;
    private String phoneNumber;
    private String cpf;
    private String imageUrl;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;
    private Set<Role> roles;
}
