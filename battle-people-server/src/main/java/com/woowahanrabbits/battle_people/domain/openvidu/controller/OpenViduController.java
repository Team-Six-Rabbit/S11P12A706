package com.woowahanrabbits.battle_people.domain.openvidu.controller;

import com.woowahanrabbits.battle_people.domain.openvidu.service.OpenViduServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/openvidu")
public class OpenViduController {

    @Autowired
    private OpenViduServiceImpl openViduService;

    @PostMapping("/sessions")
    public String createSession() {
        try {
            return this.openViduService.createSession();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/sessions/{sessionId}/tokens")
    public String generateToken(@PathVariable String sessionId) {
        try {
            return this.openViduService.generateToken(sessionId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}