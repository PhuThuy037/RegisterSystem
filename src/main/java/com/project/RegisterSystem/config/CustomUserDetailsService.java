package com.project.RegisterSystem.config;

import com.project.RegisterSystem.entity.User;
import com.project.RegisterSystem.exception.NotFoundException;
import com.project.RegisterSystem.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email).orElseThrow(()-> new NotFoundException("User not found"));

        return AuthUser.builder()
                .user(user)
                .build();
    }
}
