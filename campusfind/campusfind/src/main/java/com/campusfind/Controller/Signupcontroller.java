package com.campusfind.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusfind.Entity.Signup;
import com.campusfind.Service.Signupservice;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/campusfind")
public class Signupcontroller {
    @Autowired
    private Signupservice signupservice;

    // POST: Register user
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody List<Signup> signup) {
        signupservice.registeruser(signup);
        return ResponseEntity.ok("User registered successfully!");
    }

    // DELETE: Delete user by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        signupservice.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully!");
    }
    @GetMapping("/after")
    public String getall() {
        return " You are authenticated and can access this protected endpoint!";
    }
    
}
