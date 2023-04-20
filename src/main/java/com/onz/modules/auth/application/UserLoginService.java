package com.onz.modules.auth.application;

import com.onz.common.web.dto.response.enums.ErrorCode;
import com.onz.common.web.dto.response.enums.Role;
import com.onz.common.exception.CustomException;
import com.onz.common.web.ApiR;
import com.onz.modules.account.application.AccountService;
import com.onz.modules.account.domain.Account;
import com.onz.modules.account.infra.AccountRepository;
import com.onz.modules.auth.application.util.CookieUtils;
import com.onz.modules.auth.application.util.JwtProvider;
import com.onz.modules.auth.application.util.MD5Utils;
import com.onz.modules.auth.application.util.MysqlSHA2Util;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.auth.web.dto.request.LoginRequest;
import com.onz.modules.auth.web.dto.response.AuthResponse;
import com.onz.modules.common.pointHistory.domain.enums.PointTable;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserLoginService {
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final UserDetailsService userDetailsService;
    private final JwtProvider jwtProvider;


    public AuthResponse login(HttpServletResponse response, @RequestBody LoginRequest loginRequest) throws CustomException, ParseException {
        Account accountId = accountRepository.findByEncodedUserId2(MysqlSHA2Util.getSHA512(loginRequest.getUserId())).get();
        if (accountId == null) {
            throw new CustomException(ErrorCode.MEMBER_NOT_FOUND, new String[]{loginRequest.getUserId()});
        } else {
            if (accountId.getPassword().equals(MD5Utils.getMD5(loginRequest.getPassword()))) {
                if (accountId.getRole().equals(Role.ADMIN)) {
                    throw new CustomException(ErrorCode.UNAUTHORIZED_MEMBER);
                } else {
                    if (accountId.getLastedAt() != null) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                        String temp1 = accountId.getLastedAt().format(formatter);
                        String temp2 = ZonedDateTime.now().format(formatter);
                        Date tempResult = new SimpleDateFormat("yyyy/MM/dd").parse(temp1);
                        Date tempResult2 = new SimpleDateFormat("yyyy/MM/dd").parse(temp2);
                        long diffSec = (tempResult2.getTime()-tempResult.getTime())/1000;
                        if(diffSec>=60*60*24){
                            accountService.createMyPointHistories(accountId, PointTable.LOGIN_ATTEND);
                        }
                    }
                    accountId.setLastedAt(ZonedDateTime.now());
                    accountRepository.save(accountId);
                    UserPrincipal.create(accountId);
                    UserDetails principal = userDetailsService.loadUserByUsername(MysqlSHA2Util.getSHA512(loginRequest.getUserId()));
                    Authentication authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
                    SecurityContext context = SecurityContextHolder.createEmptyContext();
                    context.setAuthentication(authentication);
                    String token = jwtProvider.createToken(authentication);
                    response.setHeader("Authorization", token);
                    CookieUtils.addCookie(response, "Authorization", token, 180);
                    log.info(token);
                    return new AuthResponse(token);
                }
            } else {
                throw new CustomException(ErrorCode.INVALID_PASSWORD);
            }
        }
    }
}
