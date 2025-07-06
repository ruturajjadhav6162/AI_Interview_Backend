//package com.interview.service.service;
//
//import com.interview.chat.InterviewChatGrpc;
//import com.interview.chat.Message;
//import com.interview.chat.Response;
//import io.grpc.stub.StreamObserver;
//import org.springframework.grpc.server.service.GrpcService;
////import org.springframework.grpc.server.service.GrpcService;
//
//@GrpcService
//public class GrpcServiceImpl extends InterviewChatGrpc.InterviewChatImplBase {
//
//    @Override
//    public StreamObserver<Message> getResponse(StreamObserver<Response> responseObserver) {
//        return new StreamObserver<>() {
//            @Override
//            public void onNext(Message message) {
//                System.out.println("Client Response "+message.getMessage());
//                Response response = Response.newBuilder()
//                        .setResponseLLM(message.getMessage())
//                        .build();
//                responseObserver.onNext(response);
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//
//            }
//
//            @Override
//            public void onCompleted() {
//            responseObserver.onCompleted();
//            }
//        };
//    }
//}
