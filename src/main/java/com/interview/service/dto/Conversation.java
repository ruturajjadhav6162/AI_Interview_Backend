package com.interview.service.dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Embeddable
public class Conversation {
    private String candidateMessage;
    private String aiMessage;
    private LocalDateTime conversationDateTime;
}
