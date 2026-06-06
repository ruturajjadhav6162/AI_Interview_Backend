package com.interview.service.controller;

import com.interview.service.service.InterviewService;
import com.interview.service.service.gRPCClientService;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/interview/")
    public class InterviewServiceController {
        @Autowired
        SimpMessagingTemplate messagingTemplate;
        @Autowired
        InterviewService interviewService;
        @Autowired
        gRPCClientService gRPCClientService;
        List<String> chats=new ArrayList<>();
        @MessageMapping("/chat")
        public void sendMessage(@Payload String message, Principal principal, SimpMessageHeaderAccessor headerAccessor) throws InterruptedException, ExecutionException {
            List<String> chats = (List<String>) headerAccessor.getSessionAttributes().get("chats");
            if (chats == null) {
                chats = new ArrayList<>();
                headerAccessor.getSessionAttributes().put("chats", chats);
            }

            String userId = principal.getName();

            String response = gRPCClientService.getResponse(userId,message);

            chats.add("User: " + message);
            chats.add("Bot: " + response);

            System.out.println("Message received from " + userId + ": " + message);

           messagingTemplate.convertAndSendToUser(userId, "/queue/chat", response);
        }

//    @GetMapping("start")
//    public ResponseEntity<String> start(HttpServletRequest request) {
//        String token=request.getHeader("Authorization").startsWith("Bearer")?request.getHeader("Authorization").substring(7):null;
//        String message="Hello nice to meet you";
//        String response=interviewService.addConversation(message,token);
//        return new ResponseEntity<>("Interview Initiated", HttpStatus.OK);
//    }

}
