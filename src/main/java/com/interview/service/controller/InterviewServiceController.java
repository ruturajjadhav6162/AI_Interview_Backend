package com.interview.service.controller;

import com.interview.service.service.InterviewService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/interview/")
    public class InterviewServiceController {
        @Autowired
        SimpMessagingTemplate messagingTemplate;
        @Autowired
        InterviewService interviewService;
        @MessageMapping("/chat")
        public void sendMessage(@Payload String message, Principal principal ,SimpMessageHeaderAccessor headerAccessor) throws InterruptedException {
            String token=(String) headerAccessor.getSessionAttributes().get("token");
            String response=interviewService.addConversation(message,token);
            System.out.println(response);
            messagingTemplate.convertAndSendToUser(principal.getName(),"/queue/chat", response);
    }
//    @GetMapping("start")
//    public ResponseEntity<String> start(HttpServletRequest request) {
//        String token=request.getHeader("Authorization").startsWith("Bearer")?request.getHeader("Authorization").substring(7):null;
//        String message="Hello nice to meet you";
//        String response=interviewService.addConversation(message,token);
//        return new ResponseEntity<>("Interview Initiated", HttpStatus.OK);
//    }
}
