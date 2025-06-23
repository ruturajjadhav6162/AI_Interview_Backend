package com.interview.service.service;

import com.interview.service.dto.Conversation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InterviewService implements InterviewServiceImpl {
    List<Conversation> conversations=new ArrayList<>();
    @Override
    public String addConversation(String message) {
        Conversation conversation = new Conversation();

        conversation.setCandidateMessage(message);


        conversations.add(conversation);
        return "Hi retrieving info from dd conversation ";
    }

    public String addConversationInTable(List<Conversation> conversations) {

        return "hi in addConversationInTable";
    }
}
