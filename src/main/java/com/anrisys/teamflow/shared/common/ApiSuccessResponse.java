package com.anrisys.teamflow.shared.common;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiSuccessResponse<T>(String code, String message, T data) {
    // Factory method for success with data
    public static <T> ApiSuccessResponse<T> success(String message, T data) {
        return new ApiSuccessResponse<>("SUCCESS", message, data);
    }
    
    // Factory method for success without data
    public static ApiSuccessResponse<Void> success(String message) {
        return new ApiSuccessResponse<>("SUCCESS", message, null);
    }
    
    // Factory method for success with default message
    public static <T> ApiSuccessResponse<T> success(T data) {
        return new ApiSuccessResponse<>("SUCCESS", "Operation completed successfully", data);
    }
    
    // Factory method for created resources
    public static <T> ApiSuccessResponse<T> created(String message, T data) {
        return new ApiSuccessResponse<>("CREATED", message, data);
    }
    
    public static <T> ApiSuccessResponse<T> created(T data) {
        return new ApiSuccessResponse<>("CREATED", "Resource created successfully", data);
    }
}
