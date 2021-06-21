package com.akil.services.apigateway.commons.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiStatus {

    public static final ApiStatus CREATED = new ApiStatus(201, "CREATED");
    public static final ApiStatus OK = new ApiStatus(200, "OK");
    public static final ApiStatus UNAUTHORIZED = new ApiStatus(401, "UNAUTHORIZED");
    public static final ApiStatus NOT_FOUND = new ApiStatus(404, "NOT_FOUND");
    public static final ApiStatus INTERNAL_ERROR = new ApiStatus(500, "INTERNAL_SERVER_ERROR");
    public static final ApiStatus UNPROCESSABLE_ENTITY = new ApiStatus(422, "Unprocessable Entity");
    public static final ApiStatus BAD_REQUEST = new ApiStatus(400, "Bad Request");
    public static final ApiStatus DUPLICATE_REQUEST = new ApiStatus(409, "Duplicate request");

    private int code;
    private String message;

    public ApiStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
