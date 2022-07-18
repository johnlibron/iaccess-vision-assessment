package com.iaccess.vision.data.model;

import lombok.Data;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@Setter
@ToString
public class ResponseModel<T> implements Serializable {
    private HttpStatus status;
    private T data;
    private String message;

    public ResponseModel(T data, String message, HttpStatus status) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public ResponseModel(String message, HttpStatus status) {
        this.status = status;
        this.message = message;
    }

    public ResponseModel(T data, HttpStatus status) {
        this.status = status;
        this.data = data;
    }

    public ResponseModel(HttpStatus status) {
        this.status = status;
    }
}
