package com.akil.services.apigateway.commons.models;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ApiResponse<T extends Payload> {

    private ApiStatus status;
    private T payload;

    public ApiResponse() {
    }

    public ApiResponse(ApiStatus status) {
        this(status, null);
    }

    public ApiResponse(ApiStatus status, T payload) {
        this.status = status;
        this.payload = payload;
    }

    public ApiStatus getStatus() {
        return status;
    }

    public void setStatus(ApiStatus status) {
        this.status = status;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
