package com.onz.common.exception;


import com.onz.common.web.dto.response.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{//RuntimeException을 상속해 Uncheck 예외로 정의함(불필요한 throw전파를 막는다)
    private final ErrorCode errorCode;
    private String[] args;

    public CustomException(ErrorCode errorCode){
        this.errorCode = errorCode;
        this.args = new String[]{};
    }

}
