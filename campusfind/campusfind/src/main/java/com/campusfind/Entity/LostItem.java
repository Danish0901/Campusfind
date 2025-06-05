package com.campusfind.Entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class LostItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Item name is required")
    @Size(max = 50, message = "Name must be at most 50 characters")
    private String name;

    @Size(max = 1000, message = "Full description must be at most 1000 characters")
    private String description;

    @ElementCollection
    private List<String> imageUrl = new ArrayList<>();

    private LocalDateTime uploadDate;

    private boolean isClosed = false;

    @ManyToOne
    private Signup uploadedBy;

    @PrePersist
    protected void onCreate() {
        this.uploadDate = LocalDateTime.now();
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Signup getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(Signup uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public List<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(List<String> imageUrl) {
        this.imageUrl = imageUrl;
    }
}

