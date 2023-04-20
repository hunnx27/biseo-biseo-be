package com.onz.modules.common.pointHistory.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PointTable {
    WELCOME_JOIN("MJ", "회원가입", 3000),
    LOGIN_ATTEND("MA", "로그인-출석", 50),
    ATTEND_100PERCENT("MP", "출석-100%", 1000),//포인트 히스토리로 체크
    TAG_REGIST("MT", "관심 #태그등록", 50),
    REVIEW_REGIST("RR", "리뷰등록", 1000),
    AMT_REGIST("RS", "연봉등록", 500),
    INTERVIEW_REGIST("RI", "면접등록", 500),
    COUNCEL_QUESTION_REGIST("IQ", "상담질문등록	", -100),
    COUNCEL_ANSWER_REGIST("IA", "상담답변등록참여", 10),
    COUNCEL_CHOSEN("IC", "상담답변채택됨", 100),
    COUNCEL_SELECT("IS", "상담답변채택함", 50),
    APP_RECOMMEND("AR", "앱추천", 100),
    GRADE_RATE("GR", "등급조정", 0),
    CASH_REFUND("MU", "현금환급", 0),
    COUNCEL_DELETE("ID", "상담답변삭제	", -10),
    ;
    String code;
    String codeName;
    Integer amt;
}
