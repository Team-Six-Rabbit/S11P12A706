package com.woowahanrabbits.battle_people.domain.notify.domain;

import com.woowahanrabbits.battle_people.domain.user.domain.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Notify {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private int notifyCode;
    private String context;
    private String url;
    private Date registDate;
    private boolean isRead;

    @PrePersist
    protected void onCreate() {
        this.registDate = new Date();
    }
}
