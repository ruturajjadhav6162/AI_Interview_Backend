package com.interview.service.controller;

import com.interview.service.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
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
        public void sendMessage(@Payload String message, Principal principal) throws InterruptedException {
            String[] list = principal.getName().split(" ");
            String username=list[0];
            String token=list[1];
            String response = "Hi " + username + ", I got your message: " + message;
            System.out.println(response);
            response=interviewService.addConversation(message,token);
            messagingTemplate.convertAndSendToUser(username,"/queue/chat", response);
    }
}
