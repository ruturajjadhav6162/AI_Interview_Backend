package com.interview.service.service;

import com.interview.service.dto.Conversation;
import com.interview.service.entity.Interview;
import com.interview.service.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class InterviewService implements InterviewServiceImpl {
    List<Conversation> conversations=new ArrayList<>();
    @Autowired
    InterviewRepository interviewRepository;
    @Autowired
    gRPCClientService gRPCClientService;
    @Override
    public String addConversation(String message,String token) {
        Conversation conversation = new Conversation();

        conversation.setCandidateMessage(message);

        String message1= null;
        try {
            message1 = gRPCClientService.getResponse(message,token).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        conversations.add(conversation);
        return message1;
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
