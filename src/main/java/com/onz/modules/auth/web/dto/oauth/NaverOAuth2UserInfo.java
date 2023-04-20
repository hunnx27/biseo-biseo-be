package com.onz.modules.auth.web.dto.oauth;

import java.util.HashMap;
import java.util.Map;

public class NaverOAuth2UserInfo extends OAuth2UserInfo{
    Map<String, Object> response = new HashMap<>();
    public NaverOAuth2UserInfo(Map<String, Object> attributes, String registrationId) {
        super(attributes, registrationId);
        response = (Map<String, Object>) attributes.get("response");
    }

    @Override
    public String getId() {
        return (String) response.get("id");
    }

//    @Override
//    public String getName() {
//        return (String) response.get("name");
//    }
//
//    @Override
//    public String getEmail() {
//        return (String) response.get("email");
//    }
//
//    @Override
//    public String getPicture() {
//        return (String) response.get("profile_image");
//    }

    @Override
    public String getrRegistrationId() {
        return (String) registrationId;
    }
}
