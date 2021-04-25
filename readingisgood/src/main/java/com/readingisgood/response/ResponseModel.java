package com.readingisgood.response;

import com.readingisgood.enums.ResponseStatus;

import java.io.Serializable;

public class ResponseModel<T> implements Serializable {

    private T data;
    private ResponseStatus responseType;
    private String message;

    public ResponseModel() {
    }

    public ResponseModel(T data, ResponseStatus responseType, String message) {
        this.data = data;
        this.responseType = responseType;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseStatus getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseStatus responseType) {
        this.responseType = responseType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
