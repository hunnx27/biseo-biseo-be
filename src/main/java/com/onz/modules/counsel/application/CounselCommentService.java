package com.onz.modules.counsel.application;

import com.onz.common.web.dto.response.enums.YN;
import com.onz.common.util.FileUtil;
import com.onz.modules.account.application.AccountService;
import com.onz.modules.account.domain.Account;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.common.pointHistory.domain.enums.PointTable;
import com.onz.modules.counsel.domain.CounselComment;
import com.onz.modules.counsel.infra.counsel.CounselRepository;
import com.onz.modules.counsel.infra.counselComment.CounselCommentRepository;
import com.onz.modules.counsel.web.dto.request.counselComment.CounselCommentCreateRequest;
import com.onz.modules.counsel.web.dto.request.counselComment.CounselCommentUpdateRequest;
import com.onz.modules.counsel.web.dto.response.counselComment.CounselCommentDetailResponse;
import com.onz.modules.counsel.web.dto.response.counselComment.CounselCommentListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CounselCommentService {

    private final AccountService accountService;
    private final CounselCommentRepository counselCommentRepository;
    private final CounselRepository counselRepository;
    private final FileUtil fileUtil;

    public ResponseEntity<?> create(CounselCommentCreateRequest counselCommentCreateRequest, UserPrincipal me) {
        // 댓글 등록자 설정
        Account account = accountService.findOne(me.getId());
        // 답변 설정
        counselCommentCreateRequest.setParentCounsel(counselRepository.findById(counselCommentCreateRequest.getParentCounselId()).orElseGet(null));
        CounselComment counselComment = new CounselComment(counselCommentCreateRequest, account);
        CounselComment saved = counselCommentRepository.save(counselComment);
        accountService.createMyPointHistories(account, PointTable.COUNCEL_ANSWER_REGIST);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    public List<CounselCommentListResponse> commentList(Long answerId, Pageable pageable, UserPrincipal me){
        Account account = accountService.findOne(me.getId());
        List<CounselComment> list = counselCommentRepository.findCommentList(answerId, pageable);
        List<CounselCommentListResponse> result = list.stream().map(counselComment->new CounselCommentListResponse(counselComment, account)).collect(Collectors.toList());
        return result;
    }

    public CounselCommentDetailResponse detail(Long id, UserPrincipal me){
        CounselComment counselComment = counselCommentRepository.findById(id).orElse(null);
        CounselCommentDetailResponse result = null;
        if(counselComment != null){
            result = new CounselCommentDetailResponse(counselComment);
        }
        return result;
    }

    public CounselComment updateCounselComment(Long id, CounselCommentUpdateRequest counselCommentUpdateRequest, UserPrincipal me){
        Account account = accountService.findOne(me.getId());
        CounselComment counselComment = counselCommentRepository.findById(id).get();
        if(counselComment.getAccount().getUserId().equals(account.getUserId())) {
            counselComment.updateCounselComment(counselCommentUpdateRequest, account);
            CounselComment saved = counselCommentRepository.save(counselComment);
            return saved;
        }else{
            return counselComment;
        }
    }

    public CounselComment deleteCounselCommentSoft(Long id){
        CounselComment counselComment = counselCommentRepository.findById(id).orElseThrow();
        counselComment.setIsDelete(YN.Y);
        counselCommentRepository.save(counselComment);
        return counselComment;
    }

}
