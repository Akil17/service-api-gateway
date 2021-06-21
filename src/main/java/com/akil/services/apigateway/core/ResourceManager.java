package com.akil.services.apigateway.core;

import com.assignment.services.youtube_api.YoutubeApiServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ResourceManager {

    private static final Object LOCK = new Object();
    private static ResourceManager mInstance;

    public static void initialize() {

        if(mInstance != null) {
            return;
        }

        synchronized(LOCK) {
            mInstance = new ResourceManager();
        }
    }

    public static ResourceManager getInstance() {
        return mInstance;
    }

    public YoutubeApiServiceGrpc.YoutubeApiServiceBlockingStub getYoutubeApiServiceBlocking() throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50001)
                .usePlaintext()
                .build();
        return YoutubeApiServiceGrpc.newBlockingStub(channel);
    }
}
