package com.campusfind.Service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.campusfind.DTO.UploadPage;
import com.campusfind.Entity.LostItem;
import com.campusfind.Entity.Signup;
import com.campusfind.Repository.LostItemrepo;
import com.campusfind.Repository.Signuprepository;

@Service
public class UploadService {

    @Autowired
    private Signuprepository signupRepository;

    @Autowired
    private LostItemrepo lostItemRepository;

    public void saveItem(UploadPage dto, Principal principal) {
        String email = principal.getName(); // email from JWT
        Signup user = signupRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        LostItem item = new LostItem();
        item.setName(dto.getName());
        item.setDescription(dto.getDescription());
        item.setImageUrl(dto.getImageUrl());
        item.setUploadedBy(user);

        lostItemRepository.save(item);
    }
}

