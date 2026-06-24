package com.bufete.app.security;

import com.bufete.app.model.User;
import com.bufete.app.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    	User user = userRepository.findByUsername(username);
    	if (user == null) {
    	    throw new UsernameNotFoundException("Usuario no encontrado: " + username);
    	}

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())   // debe estar encriptada
                .roles(user.getRole())          // ADMIN → ROLE_ADMIN
                .build();
    }
}

