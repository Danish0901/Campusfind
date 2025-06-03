package com.campusfind.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusfind.Entity.Signup;

@Repository
public interface  Signuprepository extends JpaRepository<Signup, Long> {

    Optional<Signup> findByEmail(String email);
    
}
