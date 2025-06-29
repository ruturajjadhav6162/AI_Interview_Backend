package com.interview.service.service;

import com.interview.service.dto.Conversation;
import com.interview.service.entity.Interview;
import com.interview.service.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InterviewService implements InterviewServiceImpl {
    List<Conversation> conversations=new ArrayList<>();
    @Autowired
    InterviewRepository interviewRepository;
    @Override
    public String addConversation(String message) {
        Conversation conversation = new Conversation();

        conversation.setCandidateMessage(message);


        conversations.add(conversation);
        return "Hi retrieving info from dd conversation ";
    }

    public String addConversationInTable(List<Conversation> conversations) {
//        Interview interview = Interview.builder()
//                .jobId()
//                .companyId()
//                .userId()
//                .conversation(conversations)
//                .build();
//        interviewRepository.save(interview);
        return "Conversation Stored successfully";
    }
}
