package com.onz.modules.auth.web.dto.oauth;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {
    Map<String, Object> kakaoAccount;
    Map<String, Object> profile;
    public KakaoOAuth2UserInfo(Map<String, Object> attributes, String registrationId) {
        super(attributes, registrationId);
        kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        profile = (Map<String, Object>) kakaoAccount.get("profile");
    }

    @Override
    public String getId() {
        return String.valueOf(attributes.get("id"));
    }

//    @Override
//    public String getName() {
//        return (String)profile.get("nickname");
//    }
//
//    @Override
//    public String getEmail() {
//        return (String)kakaoAccount.get("email");
//    }
//
//    @Override
//    public String getPicture() {
//        return (String)profile.get("profile_image_url");
//    }

    @Override
    public String getrRegistrationId() {
        return (String) registrationId;
    }
}
