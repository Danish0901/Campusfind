package com.campusfind.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusfind.Entity.Signup;
import com.campusfind.Repository.Signuprepository;

@Service
public class Signupservice {
    
    @Autowired
    public Signuprepository signuprepository;

    public Signup registeruser(Signup user){
        return signuprepository.save(user);
    }

    public void deleteUser(Long id) {
        signuprepository.deleteById(id);
    }
}
