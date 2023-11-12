package com.ead.authuser.domain.user.entity;

import com.ead.authuser.domain.role.entity.Role;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private UUID userId;
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
    private Set<Role> roles = new HashSet<>();
}