package com.akil.services.apigateway;

import com.akil.services.apigateway.core.ApiGatewayApplication;
import com.akil.services.apigateway.core.ResourceManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainClass {

    public static void main(String[] args) {
        try {

            log.debug("Initializing Resource Manager");
            ResourceManager.initialize();
            log.debug("Resource Manager Initialized");

            new ApiGatewayApplication().run(args);

        } catch (Exception e) {
            log.error("Failed to start " + e);
        }
    }
}