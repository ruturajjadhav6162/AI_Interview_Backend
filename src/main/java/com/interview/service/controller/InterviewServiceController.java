package com.interview.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/interview/")
    public class InterviewServiceController {
        @Autowired
        SimpMessagingTemplate messagingTemplate;
        @MessageMapping("/chat")
        public void sendMessage(@Payload String message, Principal principal) {
            String username = principal.getName();
            String response = "Hi " + username + ", I got your message: " + message;
            System.out.println(response);
            messagingTemplate.convertAndSendToUser(principal.getName(),"/queue/chat", response);
    }
    @MessageMapping("/ping")
    public void handlePing(Principal principal) {
        // Optional: just to maintain the session
        System.out.println("Ping received from: " + principal.getName());
    }
}
