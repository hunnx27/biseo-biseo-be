package com.onz.common.web.dto.response.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.Min;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "리프레시 토큰이 유효하지 않습니다"),
    MISMATCH_REFRESH_TOKEN(HttpStatus.BAD_REQUEST, "리프레시 토큰의 유저 정보가 일치하지 않습니다"),
    CANNOT_FOLLOW_MYSELF(HttpStatus.BAD_REQUEST, "자기 자신은 팔로우 할 수 없습니다"),
    INVALID_PARAM(HttpStatus.BAD_REQUEST, "잘못된 파라미터입니다."),
    INVALID_PARAM_NULL(HttpStatus.BAD_REQUEST, "%s은(는) 널이면 안됩니다."),
    INVALID_PARAM_ONE(HttpStatus.BAD_REQUEST, "%s은(는) 1이상 만됩니다."),
    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    INVALID_AUTH_TOKEN(HttpStatus.UNAUTHORIZED, "권한 정보가 없는 토큰입니다"),
    INVALID_AUTH_TOKEN_DETAIL(HttpStatus.UNAUTHORIZED, "권한 정보가 없는 토큰입니다 : %s"),
    UNAUTHORIZED_MEMBER(HttpStatus.UNAUTHORIZED, "현재 내 계정 정보가 존재하지 않습니다"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "패스워드가 일치하지 않습니다."),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다 : %s"),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "로그아웃 된 사용자입니다"),
    NOT_FOLLOW(HttpStatus.NOT_FOUND, "팔로우 중이지 않습니다"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "데이터를 찾을 수 없습니다"),
    /* 406 NOT_ACCEPTABLE : 허용되지 않은 요청값 */
    NOT_REQUESTED_SATISFIABLE(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE, "요청을 충족할 수 없습니다. 잘못된 요청입니다"),
    NOT_ACCEPTABLE(HttpStatus.NOT_ACCEPTABLE, "Not Acceptable"),

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_RESOURCE(HttpStatus.CONFLICT, "데이터가 이미 존재합니다"),

    /* 500 :*/
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 오류가 있습니다."),
    INTERNAL_SERVER_ERROR_DETAIL(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 오류가 있습니다. : %s")
    ;



    private final HttpStatus httpStatus;
    private final String detail;
}
