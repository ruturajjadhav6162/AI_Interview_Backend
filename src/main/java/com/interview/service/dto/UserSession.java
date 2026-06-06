package com.interview.service.dto;

import com.interview.chat.Message;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;

public class UserSession {
    private final StreamObserver<Message> requestObserver;
    private final CountDownLatch latch;

    public UserSession(StreamObserver<Message> requestObserver, CountDownLatch latch) {
        this.requestObserver = requestObserver;
        this.latch = latch;
    }

    public StreamObserver<Message> getRequestObserver() {
        return requestObserver;
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}

