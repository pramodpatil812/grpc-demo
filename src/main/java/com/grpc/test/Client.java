package com.grpc.test;

import grpctest.helloproto.HelloProtoOuterClass;
import grpctest.helloproto.HelloRequest;
import grpctest.helloproto.HelloResponse;
import grpctest.helloproto.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Client {
    public static void main(String[] args) {
        // Channel is the abstraction to connect to a service endpoint
        // Let's use plaintext communication because we don't have certs
        final ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:8080")
                .build();

        // It is up to the client to determine whether to block the call
        // Here we create a blocking stub, but an async stub,
        // or an async stub with Future are always possible.
        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);
        HelloRequest request = HelloRequest.newBuilder()
                .setFirstname("Pramod")
                .setLastname("Patil")
                .build();

        // Finally, make the call using the stub
        HelloResponse response = stub.sayHello(request);

        System.out.println("Response: "+response);

        // A Channel should be shutdown before stopping the process.
        channel.shutdownNow();
    }
}
