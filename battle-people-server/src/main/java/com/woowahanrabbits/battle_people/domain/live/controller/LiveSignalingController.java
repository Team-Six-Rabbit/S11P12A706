package com.woowahanrabbits.battle_people.domain.live.controller;

import com.woowahanrabbits.battle_people.domain.live.dto.SignalMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LiveSignalingController {

    @MessageMapping("/offer")
    @SendTo("/topic/offer")
    public SignalMessage handleOffer(SignalMessage offer) {
        log.info("Received offer: {}", offer);
        return offer;
    }

    @MessageMapping("/answer")
    @SendTo("/topic/answer")
    public SignalMessage handleAnswer(SignalMessage answer) {
        log.info("Received answer: {}", answer);
        return answer;
    }

    @MessageMapping("/candidate")
    @SendTo("/topic/candidate")
    public SignalMessage handleCandidate(SignalMessage candidate) {
        log.info("Received candidate: {}", candidate);
        return candidate;
    }
}
