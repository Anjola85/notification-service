package com.quiikmart.notificationservice.user;

import com.quiikmart.notificationservice.auth.AuthEntity;
import com.quiikmart.notificationservice.common.entity.CommonEntity;
import jakarta.persistence.*;

@Entity(name = "user")
public class UserEntity extends CommonEntity {
    @OneToOne
    @JoinColumn(name = "auth_id")
    private AuthEntity auth;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_profile", nullable = false)
    private UserProfile userProfile = UserProfile.CUSTOMER;

    @Column(name = "delete_reason")
    String deleteReason;

    @Column(name = "delete_comment")
    String deleteComment;

    @Column(name = "deleted_at")
    long deletedAt;

    @Column(nullable = false)
    private int active;

    public AuthEntity getAuth() {
        return auth;
    }

    public void setAuth(AuthEntity auth) {
        this.auth = auth;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }

    public String getDeleteComment() {
        return deleteComment;
    }

    public void setDeleteComment(String deleteComment) {
        this.deleteComment = deleteComment;
    }

    public long getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(long deletedAt) {
        this.deletedAt = deletedAt;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    /**
     *
     * @return 1 - true if the user is active otherwise 0 - false
     */
    public boolean isActive() {
        return this.active == 1;
    }
}
