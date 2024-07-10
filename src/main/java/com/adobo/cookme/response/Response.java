package com.adobo.cookme.response;

import org.springframework.stereotype.Component;

@Component("response")
public class Response {
    private Object response;
    private String message;
    private String code;

    public Response() {
    }

    public Response(Object response, String message, String code) {
        this.response = response;
        this.message = message;
        this.code = code;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
