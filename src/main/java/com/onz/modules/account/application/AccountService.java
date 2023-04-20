package com.onz.modules.account.application;

import com.onz.common.exception.CustomException;
import com.onz.common.web.ApiR;
import com.onz.common.web.dto.response.enums.*;
import com.onz.modules.account.domain.embed.Myinfo;
import com.onz.modules.account.web.dto.request.AccountMyinfoUpdateRequest;
import com.onz.modules.account.web.dto.request.AccountSearchRequest;
import com.onz.modules.account.domain.enums.AuthProvider;
import com.onz.modules.account.web.dto.request.AccountUpdateRequest;
import com.onz.modules.auth.application.util.MD5Utils;
import com.onz.modules.auth.application.util.MysqlSHA2Util;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.auth.web.dto.request.SignupRequest;
import com.onz.modules.common.grade.domain.Grade;
import com.onz.modules.common.grade.infra.GradeRepository;
import com.onz.modules.common.pointHistory.domain.PointHistory;
import com.onz.modules.common.pointHistory.domain.embed.PointInfo;
import com.onz.modules.common.pointHistory.domain.enums.PointTable;
import com.onz.modules.common.pointHistory.infra.PointHistoryRepository;
import com.onz.modules.common.pointHistory.web.dto.response.PointHistoryResponse;
import com.onz.modules.account.domain.Account;
import com.onz.modules.account.infra.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.expression.Maps;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;
    private final PointHistoryRepository pointHistoryRepository;
    private final GradeRepository gradeRepository;

    public ResponseEntity<ApiR<Account>> create(Account account) throws CustomException {
        account.setTemp1(account.getUserId());
        account.setTemp2(account.getPassword());
        account.setPassword(MD5Utils.getMD5(account.getPassword())); //수정 아이디 암호화
        account.setUserId(MysqlSHA2Util.getSHA512(account.getUserId()));
        account.setRole(Role.USER);
        account.setGrade(gradeRepository.findByGrade("1"));
        if (accountRepository.existsByUserId(account.getUserId())) {
            throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
        } else {
            createMyPointHistories(account, PointTable.WELCOME_JOIN);
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(accountRepository.save(account)));
        }
    }

    public Page<Account> list(AccountSearchRequest accountSearchRequest, Pageable pageable) {
        return accountRepository.accounts(accountSearchRequest, pageable);
    }

    public Account findOne(Long id) {

        return accountRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    public Long update(UserPrincipal up, AccountUpdateRequest account) {
        Account findOne = accountRepository.findById(up.getId())
                .orElseThrow(NoSuchElementException::new);
        findOne.setUpdateData(account);
        return accountRepository.save(findOne).getId();
    }

    @Deprecated
    public boolean delete(Long id) {
        Account account = accountRepository.deleteAccount(id);
        return true;
    }

    public Account updateMyItem(Long id, AccountMyinfoUpdateRequest req) {
        Optional<Account> accountOpt = accountRepository.findById(id);
        Account rsAccount = null;
        if (!accountOpt.isEmpty()) {
            Account account = accountOpt.get();
            account.setUpdateMyinfo(req);
            rsAccount = accountRepository.save(account);
        }
        return rsAccount;
    }

    public Account deleteMeSoft(Long id) {
        Account account = accountRepository.findById(id).orElseThrow();
        account.setIsDelete(YN.Y);
        account.setDeletedAt(ZonedDateTime.now());
        accountRepository.save(account);
        return account;
    }

    public Account getNewUser(SignupRequest signupRequest) {
        Account user = Account.builder()
                .userId(signupRequest.getSocialId())
                .gubn(signupRequest.getGubnCode() != null ? Gubn.of(signupRequest.getGubnCode()) : null)
                .provider(AuthProvider.of(signupRequest.getSnsTypeCode()))
                .role(Role.USER)
                .grade(gradeRepository.findByGrade("1"))
                .build();
        accountRepository.save(user);
        return user;
    }

    public Account getMember() {
        Account account = new Account();
        Myinfo myinfo = new Myinfo();
        myinfo.setInterestCompany(InterestCompany.valueOf("ALL"));
        account.setMyinfo(myinfo);
        return null;
    }

    public void createMyPointHistories(Account account, PointTable pointTable) {
//        Account account = accountRepository.findById(id).orElseGet(null);
//        if(account != null){
        if (account.getPoint() != null) {
            updateGrade(account);
        }
        pointHistoryRepository.save(new PointHistory(account, pointTable));
//        }
    }

    // FIXME Pageable 추가 필수
    // 이력 조회
    public Page<PointHistoryResponse> getMyPointHistories(Long accountId) {
        Pageable pageable = Pageable.ofSize(1000).first();
        Page<PointHistory> pageList = pointHistoryRepository.findByAccountId(accountId, pageable);
        List<PointHistoryResponse> rs = pageList.get().map(PointHistoryResponse::new).collect(Collectors.toList());
        return new PageImpl<>(rs);
    }

    public void updateGrade(Account account) {
        Grade temp = gradeRepository.pointCheck(account);
        //Grade grade = gradeRepository.findByCode(temp);
        if (!temp.getGrade().equals(account.getGrade().getGrade())) {
            account.setGrade(temp);
            accountRepository.save(account);
        }
    }

}
