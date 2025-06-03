package com.campusfind.Util;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.campusfind.Service.CustomUserDetailService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Autowired
    JwtUtil jwtutil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{
        String authHeader= request.getHeader("Authorization");
        String token=null;
        String email=null;
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token=authHeader.substring(7);
            email= jwtutil.extractUsername(token);
        }
        if(email!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails= customUserDetailService.loadUserByUsername(email);
            if(jwtutil.validateToken(token, email, userDetails)){
               UsernamePasswordAuthenticationToken authtoken = 
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

               authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(authtoken);
            }
        }
        filterChain.doFilter(request, response);
    }
    
}
