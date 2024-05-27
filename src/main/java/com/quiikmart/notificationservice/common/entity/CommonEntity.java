package com.quiikmart.notificationservice.common.entity;
import jakarta.persistence.*;
import jakarta.persistence.MappedSuperclass;
import java.time.Instant;

@MappedSuperclass
public abstract class CommonEntity {
    @Id
    private String id;

    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

    @Column(name = "created_at", nullable = false)
    private long createdAt;

    @Column(name = "updated_at", nullable = false)
    private long updatedAt;

    @PrePersist
    protected void onCreate() {
        long currentTime = Instant.now().toEpochMilli();
        this.createdAt = currentTime;
        this.updatedAt = currentTime;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now().toEpochMilli();
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
