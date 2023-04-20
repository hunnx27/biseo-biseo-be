package com.onz.modules.auth.application;

import com.onz.common.web.dto.response.enums.ErrorCode;
import com.onz.common.exception.CustomException;
import com.onz.modules.account.domain.Account;
import com.onz.modules.account.infra.AccountRepository;
import com.onz.modules.auth.web.dto.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String encodedUserId) throws CustomException {
//        Account account = accountRepository.findByPlainUserId(userId, AuthProvider.local).get();
        Account account = accountRepository.findByEncodedUserId2(encodedUserId).get();

        if (account == null) {
            //throw new UsernameNotFoundException(String.format("계정정보가 없습니다. %s",nameOrEmail));
            throw new CustomException(ErrorCode.MEMBER_NOT_FOUND, new String[]{encodedUserId});
        }
        return UserPrincipal.create(account);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND, new String[]{String.valueOf(id)}));

        return UserPrincipal.create(account);
    }

//    public ResponseEntity<ApiR<?>> login( HttpServletResponse response, @RequestBody LoginRequest loginRequest) throws CustomException {
//        Account accountId = accountRepository.findByEncodedUserId2(loginRequest.getUserId()).get();
//        if (accountId == null) {
//            throw new CustomException(ErrorCode.MEMBER_NOT_FOUND, new String[]{loginRequest.getUserId()});
//        } else {
//            if (accountId.getPassword().equals(MD5Utils.getMD5(loginRequest.getPassword()))) {
//                    UserPrincipal.create(accountId);
//                    UserDetails principal = loadUserByUsername(loginRequest.getUserId());
//                    Authentication authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
//                    SecurityContext context = SecurityContextHolder.createEmptyContext();
//                    context.setAuthentication(authentication);
//                    String token = jwtProvider.createToken(authentication);
//                    response.setHeader("Authorization", token);
//                    CookieUtils.addCookie(response, "Authorization", token, 180);
//                    log.info(token);
//                    return ResponseEntity.ok(ApiR.createSuccess(new AuthResponse(token)));
//                }
//             else {
//                throw new CustomException(ErrorCode.INVALID_PASSWORD);
//            }
//        }
//    }
}
