package com.onz.modules.auth.web;

import com.onz.modules.auth.application.util.CookieUtils;
import com.onz.modules.auth.infra.HttpCookieOAuth2AuthorizationRequestRepository;
import com.onz.modules.auth.application.util.JwtProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.onz.modules.auth.web.dto.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Optional;

import static com.onz.modules.auth.infra.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URI_PARAM_COOKIE_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtProvider provider;
    private final ObjectMapper objectMapper;
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {

        String targetUrl = determineTargetUrl(request, response, authentication);
        if (response.isCommitted()) {
            log.info("응답이 이미 커밋됐습니다. :: {} 로 리디렉션 할 수 없습니다.", targetUrl);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        clearAuthenticationAttributes(request, response);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private void writeTokenResponse(HttpServletResponse response, String token)
        throws IOException {
        response.setHeader("Authorization", token);
        response.setContentType("application/json;charset=UTF-8");

        CookieUtils.addCookie(response, "Authorization", token, 180);
    }

    protected String determineTargetUrl(HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication) {
        String gubnCode = null;
        String snsTypeCode = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserPrincipal) {
            UserPrincipal userPrincipal = ((UserPrincipal) authentication.getPrincipal());
            gubnCode = userPrincipal.getGubnCode();
            snsTypeCode = userPrincipal.getSnsTypeCode();
        }
        Optional<String> redirectUri = CookieUtils.getCookie(request,
                REDIRECT_URI_PARAM_COOKIE_NAME)
            .map(Cookie::getValue);

        String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

        String token = null;
        if(gubnCode != null){
            token = provider.createToken(authentication);
        }

        return UriComponentsBuilder.fromUriString(targetUrl)
                .queryParam("token", token)
                .queryParam("userId", authentication.getName())
                .queryParam("snsTypeCode", snsTypeCode)
            .build().toString();
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request,
        HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);

        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request,
            response);
    }

}
