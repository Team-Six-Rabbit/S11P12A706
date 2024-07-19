package com.woowahanrabbits.battle_people.domain.BalanceGame.domain;

import com.woowahanrabbits.battle_people.domain.battle.domain.BattleBoard;
import com.woowahanrabbits.battle_people.domain.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.ConnectionBuilder;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BalanceGameBoardComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "battle_board_id")
    private BattleBoard battleBoard;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String content;
    private Date registDate;
    private boolean isDeleted;


    @PrePersist
    protected void onCreate() {
        this.registDate = new Date();
    }
}
