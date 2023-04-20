package com.onz.modules.auth.web;

import com.onz.common.web.ApiR;
import com.onz.modules.account.application.AccountService;
import com.onz.modules.account.domain.Account;
import com.onz.modules.auth.application.UserLoginService;
import com.onz.modules.auth.application.util.CookieUtils;
import com.onz.modules.auth.application.UserDetailServiceImpl;
import com.onz.modules.auth.application.util.JwtProvider;
import com.onz.modules.auth.web.dto.request.LoginRequest;
import com.onz.modules.auth.web.dto.request.SignupRequest;
import com.onz.modules.auth.web.dto.response.AuthResponse;
import com.onz.modules.common.pointHistory.domain.enums.PointTable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "계정 가입/로그인 제어", description = "계정의 가입과 로그인을 제어하는 api.")
public class AuthController {

    private final UserDetailServiceImpl userDetailService;
    private final UserLoginService userLoginService;
    private final JwtProvider jwtProvider;
    private final AccountService accountService;

    @Operation(summary = "로그인하기기", description = "로그인합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 완료", content = @Content(schema = @Schema(implementation = HttpServletResponse.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = HttpServletResponse.class)))
    })
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(HttpServletResponse response,
                                         @RequestBody @Validated LoginRequest loginRequest) throws ParseException {
        return ResponseEntity.ok(userLoginService.login(response,loginRequest));
    }

    @Operation(summary = "회원가입하기", description = "회원가입합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원가입 완료", content = @Content(schema = @Schema(implementation = HttpServletResponse.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = HttpServletResponse.class)))
    })
    @PostMapping("/auth/oauth2/signup")
    public ResponseEntity<ApiR<?>> oauth2Signup(HttpServletResponse response,
                                                @RequestBody SignupRequest signupRequest) {
        try {
            // 0. 파라미터 확인
            log.info("id : {}", signupRequest.getSocialId());
            log.info("gubn : {}", signupRequest.getGubnCode());
            log.info("snstype {}", signupRequest.getSnsTypeCode());
            log.info("allCheckSignup {}", signupRequest.getAllCheckSignup());
            log.info("checkSignupService {}", signupRequest.getCheckSignupService());
            log.info("checkSignupPrivacy {}", signupRequest.getCheckSignupPrivacy());

            // 1. Account 등록
            Account account = accountService.getNewUser(signupRequest);

            // 2. 회원가입 후 서비스 처리
            afterJoinService(account);

            // 2. Account 조회
            UserDetails principal = userDetailService.loadUserByUsername(account.getUserId());

            // 3. Authentication 저장
            Authentication authentication = new UsernamePasswordAuthenticationToken(principal,
                    principal.getPassword(), principal.getAuthorities());
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);

            // 4. 토큰생성
            String token = jwtProvider.createToken(authentication);

            // 5. 토큰반환
            response.setHeader("Authorization", token);
            CookieUtils.addCookie(response, "Authorization", token, 180);
            return ResponseEntity.ok(ApiR.createSuccess(new AuthResponse(token)));
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 회원가입후 서비스 처리
     */
    private void afterJoinService(Account account) {
        try {
            // 회원가입 최초 포인트(+3000point)
            accountService.createMyPointHistories(account, PointTable.WELCOME_JOIN);
            ResponseEntity.ok(ApiR.createSuccessWithNoContent());
        } catch (Exception e) {
            throw e;
        }
    }
}
