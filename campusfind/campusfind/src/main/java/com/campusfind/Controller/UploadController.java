package com.campusfind.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusfind.DTO.UploadPage;
import com.campusfind.Service.UploadService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/campusfind")
public class UploadController {
    @Autowired
    private UploadService uploadService;

    @PostMapping("/uploaditems")
    public ResponseEntity<String> uploadItem(@RequestBody @Valid UploadPage dto, Principal principal) {
        try{
            uploadService.saveItem(dto, principal);
            System.out.println("Current user: " + principal.getName());
            return ResponseEntity.ok("Item uploaded successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error uploading item: " + e.getMessage());
        }
        
    }
}
