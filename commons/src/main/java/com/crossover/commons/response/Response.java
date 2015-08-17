package com.crossover.commons.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
    private final ResultType status;

    private final String message;

    public ResultType getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public enum ResultType {
        SUCCESS,
        FAILURE
    }

    public Response() {
        this.message = null;
        this.status = null;
    }

    private Response(ResultType status, String message) {
        this.message = message;
        this.status = status;
    }

    public static Response get(ResultType status) {
        return new Response(status, null);
    }

    public static Response get(ResultType status, String msg) {
        return new Response(status, msg);
    }

}
