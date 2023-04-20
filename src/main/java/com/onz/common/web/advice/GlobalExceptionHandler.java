package com.onz.common.web.advice;

import com.onz.common.web.dto.response.enums.ErrorCode;
import com.onz.common.web.dto.response.ErrorResponse;
import com.onz.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 사용자 정의 Exception
     * @param e
     * @return
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        return ErrorResponse.toResponseEntity(e.getErrorCode(), e.getArgs());
    }

    /**
     *
     * Exception들 추가 필요...
     * ...
     * ...
     * ...+
     */

    /**
     *  InternalAuthenticationServiceException 인증 Exception
     */
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<ErrorResponse> internalAuthenticationServiceException(InternalAuthenticationServiceException e, HttpServletRequest request){
        ResponseEntity<ErrorResponse> response;
        // Default 처리
        ErrorCode errorCode = ErrorCode.INVALID_AUTH_TOKEN;
        response = ErrorResponse.toResponseEntity(errorCode);

        // CustomException인 경우 처리
        if(e.getCause() instanceof CustomException){
            CustomException ce = (CustomException) e.getCause();
            response = ErrorResponse.toResponseEntity(ce.getErrorCode(), ce.getArgs());
        }
        return response;
    }
    /**
     * @valid  유효성체크에 통과하지 못하면  MethodArgumentNotValidException 이 발생한다.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodValidException(MethodArgumentNotValidException e, HttpServletRequest request){
//        log.warn("MethodArgumentNotValidException 발생!!! url:{}",request.getRequestURI());
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder builder = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append("[");
            builder.append(fieldError.getField());
            builder.append("](은)는 ");
            builder.append(fieldError.getDefaultMessage());
            builder.append(" 입력된 값: [");
            builder.append(fieldError.getRejectedValue());
            builder.append("]\n");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(builder.toString());
//       return builder.toString();
    }

    /**
     * @valid  유효성체크에 통과하지 못하면  MethodArgumentNotValidException 이 발생한다.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> accessDeniedException(AccessDeniedException ex, HttpServletRequest request){
        log.warn("handleAllException", ex);
        return ErrorResponse.toResponseEntity(ErrorCode.INVALID_AUTH_TOKEN_DETAIL);
    }



    /**
     * 공통 Exception
     * @param ex
     * @return
     */
    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleAllException(Exception ex) {
        log.warn("handleAllException", ex);
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        if(ex.getMessage()!=null){
            return ErrorResponse.toResponseEntity(ErrorCode.INTERNAL_SERVER_ERROR_DETAIL, ex.getMessage());
        }else {
            return ErrorResponse.toResponseEntity(ErrorCode.INTERNAL_SERVER_ERROR);
        }

    }

}
