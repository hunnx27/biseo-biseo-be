package com.onz.common.exception;

// serialVersionUID를 정의해주지 않은 경우 나타나는 warnning을 체크하지 않음
@SuppressWarnings("serial")
public class FileException extends RuntimeException {

    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}
