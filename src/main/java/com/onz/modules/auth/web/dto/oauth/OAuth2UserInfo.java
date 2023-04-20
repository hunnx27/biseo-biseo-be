package com.onz.modules.auth.web.dto.oauth;

import java.util.Map;


public abstract class OAuth2UserInfo {

    protected Map<String, Object> attributes;
    protected String registrationId;
    public OAuth2UserInfo(Map<String, Object> attributes, String registrationId){
        this.attributes = attributes;
        this.registrationId = registrationId;
    }
    public Map<String, Object> getAttributes(){return attributes;}
    public abstract String getId();
    public abstract String getrRegistrationId();
}
