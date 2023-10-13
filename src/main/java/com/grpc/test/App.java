package com.grpc.test;

import com.grpc.test.service.HelloServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class App {
    public static void main(String[] args) throws Exception{
        // Create a new server to listen on port 8080
        int port = 8080;
        Server server = ServerBuilder.forPort(port)
                .addService(new HelloServiceImpl())
                .build();

        // Start the server
        server.start();

        // Server threads are running in the background.
        System.out.println("Server started on port "+port+"....");

        // Don't exit the main thread. Wait until server is terminated.
        server.awaitTermination();
    }
}
