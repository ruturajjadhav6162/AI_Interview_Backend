package com.interview.service.entity;

import com.interview.service.dto.Conversation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@Table(name="interview_service")
@NoArgsConstructor
@AllArgsConstructor
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
