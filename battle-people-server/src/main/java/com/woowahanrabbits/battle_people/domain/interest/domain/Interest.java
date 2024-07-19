package com.woowahanrabbits.battle_people.domain.interest.domain;

import com.woowahanrabbits.battle_people.domain.user.domain.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private int category;
    private int count;
}
