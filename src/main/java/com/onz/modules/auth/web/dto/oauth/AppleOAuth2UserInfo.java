package com.onz.modules.auth.web.dto.oauth;

import java.util.Map;

public class AppleOAuth2UserInfo extends OAuth2UserInfo {
    public AppleOAuth2UserInfo(Map<String, Object> attributes, String registrationId) {
        super(attributes, registrationId);
    }
    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }
    @Override
    public String getrRegistrationId() {
        return (String) registrationId;
    }
}
