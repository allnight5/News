package com.example.news.error.dto;

import lombok.Getter;

@Getter
public class ExceptionResponse {
    private final int status;
    private final String error;
    private final String message;

    /**
     * @param message 오류 메세지
     * @param status  오류 상태 코드
     */
    public ExceptionResponse(int status,
                             String error,
                             String message){
        this.status = status;
        this.error = error;
        this.message = message;
    }
}
