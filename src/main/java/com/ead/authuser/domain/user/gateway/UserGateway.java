package com.ead.authuser.domain.user.gateway;

import com.ead.authuser.dataprovider.database.user.entity.UserEntity;
import com.ead.authuser.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public interface UserGateway {

    boolean existsByUsername(String name);

    boolean existsByEmail(String name);

    User save(User user);

    void delete(User user);

    User update(User user);

    Page<User> finAll(Specification<UserEntity> spec, Pageable pageable);

    User findById(UUID userId);
}
