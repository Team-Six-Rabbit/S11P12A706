package com.woowahanrabbits.battle_people.domain.user.domain;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {
    public UserEntity(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String role;
    // @Column(nullable = false)
    // private String nickname;
    //
    // @Column(nullable = false)
    // private String imgUrl;
    //
    // @Column(nullable = false)
    // private int rating;
    // private String accessToken;
    // private LocalDate penaltyStartDate;
    // private LocalDate penaltyEndDate;
}
