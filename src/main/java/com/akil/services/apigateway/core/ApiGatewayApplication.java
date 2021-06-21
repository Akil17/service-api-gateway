package com.akil.services.apigateway.core;

import com.akil.services.apigateway.youtube_api.YoutubeApi;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiGatewayApplication extends Application<ApiConfiguration> {

    @Override
    public void run(ApiConfiguration apiConfiguration, Environment environment) {
        log.info("Registering services");
        environment.jersey().register(new YoutubeApi());
        log.info("Successfully registered services");
    }
}