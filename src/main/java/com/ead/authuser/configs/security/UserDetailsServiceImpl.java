package com.ead.authuser.configs.security;

import com.ead.authuser.dataprovider.user.entity.UserEntity;
import com.ead.authuser.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userModel = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        return UserDetailsImpl.build(userModel);
    }

    public UserDetails loadUserById(UUID userId) throws AuthenticationCredentialsNotFoundException {
        UserEntity userModel = userRepository.findById(userId)
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("User Not Found with userId: " + userId));
        return UserDetailsImpl.build(userModel);
    }

}
