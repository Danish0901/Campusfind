package com.campusfind.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.campusfind.Repository.Signuprepository;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private Signuprepository signupRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return signupRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found with email: " + email));
    }
    
}
