package com.ead.authuser.domain.user.gateway;

import com.ead.authuser.domain.user.entity.User;

public interface UserGateway {

    boolean existsByUsername(String name);
    boolean existsByEmail(String name);
    User save(User user);
}
