package com.interview.service.config;

import com.interview.service.repository.InterviewRepository;
import com.interview.service.service.gRPCClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;

public class ChatWebSocketHandler extends TextWebSocketHandler {
//    @Autowired
//    private gRPCClientService gRPCClientService;
    @Autowired
    InterviewRepository interviewRepository;

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        String userId = (String) session.getAttributes().get("userId");
        List<String> chats = (List<String>) session.getAttributes().get("chats");


    }
}
