package com.grpc.test.service;

import grpctest.helloproto.HelloRequest;
import grpctest.helloproto.HelloResponse;
import grpctest.helloproto.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void sayHello(HelloRequest request,
                         StreamObserver<HelloResponse> responseObserver) {
        // HelloRequest has toString auto-generated.
        System.out.println("Request: "+request);

        // You must use a builder to construct a new Protobuffer object
        HelloResponse response = HelloResponse.newBuilder()
                .setData("Hello there, " + request.getFirstname()+" "+request.getLastname())
                .build();

        // Use responseObserver to send a single response back
        responseObserver.onNext(response);

        // When you are done, you must call onCompleted.
        responseObserver.onCompleted();
    }
}