package com.lab.userdetails;

import com.lab.repositories.UserRepository;
import com.lab.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        if(!userRepository.existsByUsername(username)){
            throw new UsernameNotFoundException("Username not found");
        }
        User user = userRepository.findByUsername(username);
        return new UserDetailsModel(user);
    }
}
