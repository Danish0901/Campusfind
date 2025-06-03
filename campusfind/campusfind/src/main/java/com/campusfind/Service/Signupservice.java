package com.campusfind.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusfind.Entity.Signup;
import com.campusfind.Repository.Signuprepository;

@Service
public class Signupservice {
    
    @Autowired
    public Signuprepository signuprepository;

    public List<Signup> registeruser(List<Signup> user){
        return signuprepository.saveAll(user);
    }

    public void deleteUser(Long id) {
        signuprepository.deleteById(id);
    }
}
