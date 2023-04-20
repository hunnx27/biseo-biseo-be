package com.onz.modules.auth.web.dto;

import com.onz.modules.account.domain.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.ZonedDateTime;
import java.util.*;

public class UserPrincipal implements OAuth2User, UserDetails {
    private Long id;
    private String userId;
    private String password;
    private String gubnCode;
    private String snsTypeCode;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;


    public UserPrincipal(Long id, String userId, String password, String gubnCode, String snsTypeCode, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.authorities = authorities;
        this.gubnCode = gubnCode;
        this.snsTypeCode = snsTypeCode;
    }

    public static UserPrincipal create(Account account) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority(account.getRole().getRole()));
        String gubnCode = account.getGubn()!=null? account.getGubn().getCode() : null;
        String snsTypeCode = account.getSnsType()!=null? account.getSnsType().getCode() : null;

        return new UserPrincipal(
                account.getId(),
                account.getUserId(),
                account.getPassword(),
                gubnCode,
                snsTypeCode,
                authorities
        );
    }

    public static UserPrincipal create(Account account, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(account);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    public String getGubnCode(){
        return gubnCode;
    }
    public String getSnsTypeCode(){
        return snsTypeCode;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return String.valueOf(id);
    }
}
