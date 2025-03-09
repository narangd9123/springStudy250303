package com.myCho.springStudy250303.service;

import com.myCho.springStudy250303.model.User;
import com.myCho.springStudy250303.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username : " + username);
        User userEntity = userRepository.findByUsername(username);
        if(userEntity != null){
            return new UserDetails(userEntity);
        }
        return null;
    }
}
