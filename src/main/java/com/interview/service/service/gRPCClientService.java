package com.interview.service.service;

import com.interview.chat.InterviewChatGrpc;
import com.interview.chat.Message;
import com.interview.chat.Response;
import com.interview.service.utils.JWTUtil;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class gRPCClientService {
    @Autowired
    private JWTUtil jwtUtil;
    private final InterviewChatGrpc.InterviewChatStub interviewChatStub;

    public gRPCClientService() {
        // Create channel manually (replace host & port)
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 9090)  // your gRPC server host and port
                .usePlaintext()                 // disable TLS for local testing
                .build();

        // Create async stub from channel
        this.interviewChatStub = InterviewChatGrpc.newStub(channel);
    }

    public CompletableFuture<String> getResponse(String message,String token) {
        Message msg = Message.newBuilder().setMessage(message).build();
        Claims claims=jwtUtil.extractToken(token);
        String tokenData= claims.get("userId") + " " + claims.get("jobId") + " " + claims.get("companyId");
        CompletableFuture<String> future = new CompletableFuture<>();

        StreamObserver<Response> responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(Response response) {
                System.out.println("Server Response: " + response.getResponseLLM());
                future.complete(response.getResponseLLM());
            }

            @Override
            public void onError(Throwable throwable) {
                System.err.println("Server Error: " + throwable.getMessage());
                future.completeExceptionally(throwable);
            }

            @Override
            public void onCompleted() {
                System.out.println("Server Completed");
            }
        };

        StreamObserver<Message> requestObserver = interviewChatStub.getResponse(responseObserver);
        boolean initialize=true;
        if(initialize){
            requestObserver.onNext(Message.newBuilder().setMessage(tokenData).build());
            initialize=false;
        }else{
        requestObserver.onNext(msg);
        requestObserver.onCompleted();
}
        return future;
    }
}
