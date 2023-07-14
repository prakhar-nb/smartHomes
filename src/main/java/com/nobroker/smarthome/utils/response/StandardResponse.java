package com.nobroker.smarthome.utils.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StandardResponse<T> {

    private String message;
    private int statusCode;
    private T data;

    public StandardResponse(String message,
                            int statusCode,
                            T data) {
        this.message = message;
        this.statusCode = statusCode;
        this.data = data;
    }

    public static <T> StandardResponse <T> create(StatusCode statusCode, T data) {
        return new StandardResponse<>(statusCode.displayText, statusCode.statusCode, data);
    }

    public static <T> StandardResponse <T> validationFailure(T data) {
        return new StandardResponse<>(StatusCode.VALIDATION_FAILURE.displayText, StatusCode.VALIDATION_FAILURE.statusCode, data);
    }

    public static <T> StandardResponse <T> validationFailure(T data, String failureReason) {
        return new StandardResponse<>(failureReason, StatusCode.VALIDATION_FAILURE.statusCode, data);
    }

    public static <T> StandardResponse <T> success(T data) {
        return new StandardResponse<>(StatusCode.SUCCESS.displayText, StatusCode.SUCCESS.statusCode, data);
    }

    public static <T> StandardResponse <T> success() {
        return new StandardResponse<>(StatusCode.SUCCESS.displayText, StatusCode.SUCCESS.statusCode,null);
    }

    public enum StatusCode {

        SUCCESS(2000 ,"Success"),

        //reserved codes: 4000-5000 for failures, bad-requests
        VALIDATION_FAILURE(4001 ,"validation failed");

        public int statusCode;
        public String displayText;

        StatusCode(int statusCode, String displayText) {
            this.statusCode =statusCode;
            this.displayText = displayText;
        }
    }

}
