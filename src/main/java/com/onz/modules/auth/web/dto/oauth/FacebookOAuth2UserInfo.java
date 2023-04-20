package com.onz.modules.auth.web.dto.oauth;

import java.util.Map;

public class FacebookOAuth2UserInfo extends OAuth2UserInfo {
    public FacebookOAuth2UserInfo(Map<String, Object> attributes, String registrationId) {
        super(attributes, registrationId);
    }
    @Override
    public String getId() {
        return (String) attributes.get("id");
    }

//    @Override
//    public String getName() {
//        return (String) attributes.get("name");
//    }
//    @Override
//    public String getEmail() {
//        return (String) attributes.get("email");
//    }
    @Override
    public String getrRegistrationId() {
        return (String) registrationId;
    }
}
