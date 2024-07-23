package com.woowahanrabbits.battle_people.domain.openvidu.service;

import io.openvidu.java.client.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OpenViduServiceImpl {

    private OpenVidu openVidu;
    private Map<String, Session> sessions = new HashMap<>();
    private Map<String, String> sessionNamesTokens = new HashMap<>();

    @Value("${openvidu.url}")
    private String openviduUrl;

    @Value("${openvidu.secret}")
    private String secret;

    @PostConstruct
    public void initialize() {
        this.openVidu = new OpenVidu(openviduUrl, secret);
    }

    public String createSession() throws OpenViduJavaClientException, OpenViduHttpException {
        Session session = this.openVidu.createSession();
        String sessionId = session.getSessionId();
        this.sessions.put(sessionId, session);
        return sessionId;
    }

    public String generateToken(String sessionId) throws OpenViduJavaClientException, OpenViduHttpException {
        Session session = this.sessions.get(sessionId);
        if (session == null) {
            throw new IllegalArgumentException("Session ID not found: " + sessionId);
        }
        ConnectionProperties connectionProperties = new ConnectionProperties.Builder().type(ConnectionType.WEBRTC).build();
        String token = session.createConnection(connectionProperties).getToken();
        this.sessionNamesTokens.put(token, sessionId);
        return token;
    }
}