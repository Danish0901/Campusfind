package com.campusfind.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusfind.Entity.AuthRequest;
import com.campusfind.Util.JwtUtil;

@RestController
@RequestMapping("/campusfind")
public class Authcontroller {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody AuthRequest authrequest) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authrequest.getEmail(), authrequest.getPassword())
            );

            String token = jwtUtil.generateToken(authrequest.getEmail());
            Map<String, String> response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            return new ResponseEntity<>(Map.of("error", "Invalid username or password"), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "Something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
