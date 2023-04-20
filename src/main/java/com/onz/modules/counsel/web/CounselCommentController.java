package com.onz.modules.counsel.web;

import com.onz.common.web.ApiR;
import com.onz.common.web.BaseApiController;
import com.onz.modules.account.domain.Account;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.counsel.domain.CounselComment;
import com.onz.modules.counsel.web.dto.request.counselComment.CounselCommentCreateRequest;
import com.onz.modules.counsel.web.dto.request.counselComment.CounselCommentUpdateRequest;
import com.onz.modules.counsel.web.dto.response.counselComment.CounselCommentDetailResponse;
import com.onz.modules.counsel.web.dto.response.counselComment.CounselCommentListResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name="상담 댓글 제어",description = "N번째 상담에 달린 댓글들을 제어하는 api.")
@Slf4j
public class CounselCommentController extends BaseApiController {
    private final com.onz.modules.counsel.application.CounselCommentService counselCommentService;
    private final ModelMapper modelMapper;
    /**
     *
     *      ANSWER COMMENT
     *
     */
    @Operation(summary = "상담 답변 댓글 불러오기", description = "N번째 상담에 있는 답변레코드를 리스트로 불러옵니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "불러오기 완료", content = @Content(schema = @Schema(implementation = CounselCommentListResponse.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CounselCommentListResponse.class)))
    })
    @GetMapping("/counsel/comment/answer/{answerId}")
    public ResponseEntity<ApiR<?>> commentList(@AuthenticationPrincipal UserPrincipal me,
                                               @PathVariable Long answerId,
                                               Pageable pageable){
        try {
            List<CounselCommentListResponse> result = counselCommentService.commentList(answerId, pageable, me);
            return  ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(result));
        }catch (Exception e){
            throw e;
        }
    }

    @Operation(summary = "상담 답변 댓글 추가하기", description = "N번째 상담에 작성된 답글 추가하기.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "답변 추가 완료", content = @Content(schema = @Schema(implementation = CounselCommentCreateRequest.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CounselCommentCreateRequest.class)))
    })
    @PostMapping(value = "/counsel/comment",consumes = "application/json")
    public ResponseEntity<?> createComment(@AuthenticationPrincipal UserPrincipal me,@RequestBody @Validated CounselCommentCreateRequest counselCommentCreateRequest) {
//        @RequestBody -> 삭제
        return ResponseEntity.ok().body(counselCommentService.create(counselCommentCreateRequest, me));

    }

    @Operation(summary = "상담 답변 댓글 수정하기", description = "N번째 상담에 작성된 답글 수정하기.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "답변 수정 완료", content = @Content(schema = @Schema(implementation = CounselCommentUpdateRequest.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CounselCommentUpdateRequest.class)))
    })
    @PutMapping("/counsel/comment/{id}")
    public void updateComment(@AuthenticationPrincipal UserPrincipal up,
                                           CounselCommentUpdateRequest counselCommentUpdateRequest,
                                           @PathVariable Long id) {
        try {
            CounselComment counselComment = counselCommentService.updateCounselComment(id, counselCommentUpdateRequest, up);
            ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(new CounselCommentDetailResponse(counselComment)));
        }catch (Exception e){
            throw e;
        }
    }

    @Operation(summary = "상담 답변 댓글 삭제하기", description = "N번째 상담에 작성된 답글 삭제하기.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "답변 삭제 완료", content = @Content(schema = @Schema(implementation = CounselComment.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CounselComment.class)))
    })
    @DeleteMapping("/counsel/comment/{id}")
    public ResponseEntity<ApiR<?>> deleteComment(@PathVariable Long id) {
        try {
            CounselComment counselComment = counselCommentService.deleteCounselCommentSoft(id);
            new CounselCommentDetailResponse(counselComment);
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccessWithNoContent());
        }catch (Exception e){
            throw e;
        }
    }

}
