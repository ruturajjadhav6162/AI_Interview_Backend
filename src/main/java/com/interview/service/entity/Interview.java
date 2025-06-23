package com.interview.service.entity;

import com.interview.service.dto.Conversation;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="interview_service")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int interviewId;
    private int userId;
    private int companyId;
    private int jobId;
    @ElementCollection
    private List<Conversation> conversation;
}
