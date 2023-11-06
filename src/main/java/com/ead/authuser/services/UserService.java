package com.ead.authuser.services;

import com.ead.authuser.dataprovider.user.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<UserEntity> findAll();
    Optional<UserEntity> findById(UUID userId);

    void delete(UserEntity userModel);

    UserEntity save(UserEntity userModel);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Page<UserEntity> findAll(Specification<UserEntity> spec, Pageable pageable);

    UserEntity saveUser(UserEntity userModel);
    void deleteUser(UserEntity userModel);
    UserEntity updateUser(UserEntity userModel);
    UserEntity updatePassword(UserEntity userModel);

}
