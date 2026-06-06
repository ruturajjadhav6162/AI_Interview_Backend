package com.interview.service.service;

import com.interview.chat.InterviewChatGrpc;
import com.interview.chat.Message;
import com.interview.chat.Response;
import com.interview.service.dto.UserSession;
import com.interview.service.utils.JWTUtil;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

@Service
public class gRPCClientService {

    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    SimpMessagingTemplate messagingTemplate;

    private final InterviewChatGrpc.InterviewChatStub interviewChatStub;
    private final ConcurrentHashMap<String, UserSession> userSessions = new ConcurrentHashMap<>();

    public gRPCClientService() {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 9091)
                .usePlaintext()
                .build();

        this.interviewChatStub = InterviewChatGrpc.newStub(channel);
    }

    public String getResponse(String userName, String message) {
        try {
            UserSession session = userSessions.computeIfAbsent(userName, this::createNewSession);

            // Send user message
            session.getRequestObserver().onNext(Message.newBuilder().setMessage(message).build());

            // Wait for response to complete
            session.getLatch().await();

            // Return response collected earlier, optionally store or pass as needed
            return "Message sent"; // or manage response storage per session
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while waiting for gRPC response", e);
        }
    }

    private UserSession createNewSession(String userName) {
        CountDownLatch latch = new CountDownLatch(1);
        final StringBuilder responseHolder = new StringBuilder();
//        Claims claims = jwtUtil.extractToken(userToken);

        StreamObserver<Response> responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(Response response) {
                System.out.println("Server Response: " + response.getResponseLLM());
                responseHolder.append(response.getResponseLLM());
                messagingTemplate.convertAndSendToUser(userName, "/queue/chat", response.getResponseLLM());
            }

            @Override
            public void onError(Throwable throwable) {
                System.err.println("Server Error: " + throwable.getMessage());
                throwable.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("Server Completed");
                latch.countDown();
            }
        };

        StreamObserver<Message> requestObserver = interviewChatStub.getResponse(responseObserver);

        // Extract data from token
        /*Claims claims = jwtUtil.extractToken(userToken);
        String tokenData = claims.get("userId") + " " + claims.get("jobId") + " " + claims.get("companyId");*/
        String tokenData=59+" "+62+" "+19;

        // Send initial message
        requestObserver.onNext(Message.newBuilder().setMessage(tokenData).build());

        return new UserSession(requestObserver, latch);
    }
}
