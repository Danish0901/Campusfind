package com.campusfind.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.campusfind.Entity.LostItem;
import com.campusfind.Entity.Signup;

@Repository
public interface LostItemrepo extends JpaRepository<LostItem, Long> {
    List<LostItem> findByUploadedBy(Signup user);
    List<LostItem> findByIsClosedFalse();
}


