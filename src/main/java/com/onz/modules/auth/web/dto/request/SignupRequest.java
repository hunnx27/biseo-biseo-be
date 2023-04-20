package com.onz.modules.auth.web.dto.request;

import lombok.Getter;


@Getter
public class SignupRequest {
    //{ "socialId": "3295686887319897", "snsTypeCode": "F", "checkSignupService": false, "allCheckSignup": false, "checkSignupPrivacy": false }
    private String socialId;
    private String snsTypeCode;
    private String gubnCode;
    private String allCheckSignup;
    private String checkSignupService;
    private String checkSignupPrivacy;

}
