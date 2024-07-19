package com.woowahanrabbits.battle_people.domain.live.dto;


import lombok.Data;

@Data
public class SignalMessage {
    private String type;
    private String sdp;
    private String candidate;
}
