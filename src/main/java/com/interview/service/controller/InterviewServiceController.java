package com.interview.service.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/interview/")
public class InterviewServiceController {
    @MessageMapping("/chat")
    @SendTo("/ai/response")
    public String sendMessage(@Payload String message) {
        return "Hello WebSocket Connected Successfully";
    }
}
