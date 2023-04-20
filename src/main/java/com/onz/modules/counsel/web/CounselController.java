package com.onz.modules.counsel.web;

import com.onz.common.web.ApiR;
import com.onz.common.web.BaseApiController;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.common.pointHistory.web.dto.response.PointHistoryResponse;
import com.onz.modules.company.web.dto.reponse.CounselSearchCountDto;
import com.onz.modules.counsel.application.CounselService;
import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.counsel.web.dto.request.counsel.*;
import com.onz.modules.counsel.web.dto.response.CounselAnswerListResponse;
import com.onz.modules.counsel.web.dto.response.counsel.CounselAnswerDetailResponse;
import com.onz.modules.counsel.web.dto.response.counsel.CounselDetailResponse;
import com.onz.modules.counsel.web.dto.response.counsel.CounselListResponse;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "상담제어", description = "상담 관련 api입니다.")
public class CounselController extends BaseApiController {

    private final CounselService counselService;
    private final ModelMapper modelMapper;

    /**
     * QUESTION
     */

    @Operation(summary = "상담 생성", description = "변수를 이용하여 counsel 레코드를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상담 생성 완료", content = @Content(schema = @Schema(implementation = CounselQCreateRequest.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CounselQCreateRequest.class)))
    })
    @PostMapping(value = "/counsel",consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> create(@AuthenticationPrincipal UserPrincipal me, @RequestPart (value = "files", required = false) List<MultipartFile> files, @RequestPart(name="data") @Validated CounselQCreateRequest counselQCreateRequest) {
        return ResponseEntity.ok().body(counselService.create(counselQCreateRequest, me, files));
    }

    @Operation(summary = "상담 목록 조회", description = "변수를 이용하여 counsel 레코드를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상담 목록 조회 완료", content = @Content(schema = @Schema(implementation = CounselListResponse.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CounselListResponse.class)))
    })
    @GetMapping("/counsel")
    public ResponseEntity<ApiR<?>> list(@AuthenticationPrincipal UserPrincipal me, CounselSearchRequest counselSearchRequest, Pageable pageable) {
        try {
            List<CounselListResponse> result = counselService.list(counselSearchRequest, pageable, me);
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(result));
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "내가 작성한 상담질문 목록 조회", description = "변수를 이용하여 counsel 레코드를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상담 목록 조회 완료", content = @Content(schema = @Schema(implementation = CounselListResponse.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CounselListResponse.class)))
    })
    @GetMapping("/counsel/my/q")
    public ResponseEntity<ApiR<?>> myqList(@AuthenticationPrincipal UserPrincipal me, Pageable pageable,@RequestBody String option) {
        try {
            List<CounselListResponse> result = counselService.myqList(pageable, me,option);
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(result));
        } catch (Exception e) {
            throw e;
        }
    }


    @Operation(summary = "내가 작성한 상담답변 목록 조회", description = "변수를 이용하여 counsel 레코드를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상담 목록 조회 완료", content = @Content(schema = @Schema(implementation = CounselListResponse.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CounselListResponse.class)))
    })
    @GetMapping("/counsel/my/a")
    public ResponseEntity<ApiR<?>> myaList(@AuthenticationPrincipal UserPrincipal me, Pageable pageable,@RequestBody String option) {
        try {
            List<CounselListResponse> result = counselService.myaList(pageable, me,option);
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(result));
        } catch (Exception e) {
            throw e;
        }
    }
    @Operation(summary = "상담 조회", description = "변수를 이용하여 counsel 레코드를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상담 조회 완료", content = @Content(schema = @Schema(implementation = CounselDetailResponse.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CounselDetailResponse.class)))
    })
    @GetMapping("/counsel/{id}")
    public ResponseEntity<ApiR<?>> info(@AuthenticationPrincipal UserPrincipal me, @PathVariable Long id) {
        try {
            CounselDetailResponse result = counselService.detail(id, me);
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(result));
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "상담 내용 수정", description = "변수를 이용하여 counsel 레코드를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상담 수정 완료", content = @Content(schema = @Schema(implementation = CounselDetailResponse.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CounselDetailResponse.class)))
    })
    @PutMapping("/counsel/{id}")
    public void updateCounsel(@AuthenticationPrincipal UserPrincipal up, @RequestPart(name="data") @Validated CounselQUpdateRequest
            counselQUpdateRequest, @RequestPart (value = "files", required = false) List<MultipartFile> files, @PathVariable Long id) {
        try {
            CounselDetailResponse counselDetail = counselService.updateCounsel(id, counselQUpdateRequest,files, up);
            ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(counselDetail));
        } catch (Exception e) {
            throw e;
        }
    }
    @Operation(summary = "상담 삭제", description = "변수를 이용하여 counsel 레코드를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상담 삭제 완료", content = @Content(schema = @Schema(implementation = CounselDetailResponse.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CounselDetailResponse.class)))
    })
    @DeleteMapping("/counsel/{id}")
    public ResponseEntity<ApiR<?>> deleteCounsel(@PathVariable Long id) {
        try {
            Counsel counsel = counselService.deleteCounselSoft(id);
            new CounselDetailResponse(counsel);
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccessWithNoContent());
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * ANSWER
     */

    @Operation(summary = "상담 답변 조회", description = "변수를 이용하여 counsel 레코드의 answer 레코드를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상담 답변 조회 완료", content = @Content(schema = @Schema(implementation = CounselAnswerListResponse.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CounselAnswerListResponse.class)))
    })
    @GetMapping("/counsel/{counselId}/answer")
    public ResponseEntity<ApiR<?>> answerList(@AuthenticationPrincipal UserPrincipal me, @PathVariable Long
            counselId, Pageable pageable) {
        try {
            List<CounselAnswerListResponse> result = counselService.answerList(counselId, pageable, me);
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(result));
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "상담 답변 단일 조회", description = "변수를 이용하여 counsel 레코드의 변수의 answer 레코드를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상담 답변 단일 조회 완료", content = @Content(schema = @Schema(implementation = CounselAnswerDetailResponse.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CounselAnswerDetailResponse.class)))
    })
    @GetMapping("/counsel/answer/{id}")
    public ResponseEntity<ApiR<?>> answerOne(@AuthenticationPrincipal UserPrincipal me, @PathVariable Long
            id, Pageable pageable) {
        try {
            CounselAnswerDetailResponse result = counselService.answerById(id);
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(result));
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "상담 답변 생성", description = "변수를 이용하여 counsel 레코드에 답변을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상담 답변 생성 완료", content = @Content(schema = @Schema(implementation = CounselACreateRequest.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CounselACreateRequest.class)))
    })
    @PostMapping("/counsel/answer")
    public ResponseEntity<?> createAnswer(@AuthenticationPrincipal UserPrincipal me, @RequestPart(name = "data") @Validated CounselACreateRequest counselACreateRequest, List<MultipartFile> files) {
        return ResponseEntity.ok().body(counselService.createAnswer(counselACreateRequest, me,files));
//        counselService.createAnswer(counselACreateRequest,me);

    }

    @Operation(summary = "상담 답변 수정", description = "변수를 이용하여 counsel 레코드에 답변을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상담 답변 수정 완료", content = @Content(schema = @Schema(implementation = CounselAUpdateRequest.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CounselAUpdateRequest.class)))
    })
    @PutMapping("/counsel/answer/{id}")
    public void updateAnswer(@AuthenticationPrincipal UserPrincipal up,@RequestPart(name="data") @Validated CounselAUpdateRequest
            counselAUpdateRequest, @RequestPart (value = "files", required = false) List<MultipartFile> files, @PathVariable Long id) {
        try {
            Counsel counsel = counselService.updateAnswer(id, counselAUpdateRequest, up,files);
            ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(new CounselDetailResponse(counsel)));
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "상담 답변 삭제", description = "변수를 이용하여 counsel 레코드에 답변을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상담 답변 삭제 완료", content = @Content(schema = @Schema(implementation = CounselDetailResponse.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CounselDetailResponse.class)))
    })
    @DeleteMapping("/counsel/answer/{id}")
    public ResponseEntity<ApiR<?>> deleteAnswer(@PathVariable Long id) {
        try {
            Counsel counsel = counselService.deleteAnswerSoft(id);
            new CounselDetailResponse(counsel);
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccessWithNoContent());
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "상담 채택하기", description = "counsel 레코드의 answer에 작성된 답변을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상담 답변 댓글 수정 완료", content = @Content(schema = @Schema(implementation = CounselAAdoptRequest.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CounselAAdoptRequest.class)))
    })
    @PutMapping("/counsel/answer/{id}/adopt")
    public ResponseEntity<?> updateAnswerAdopt(@AuthenticationPrincipal UserPrincipal
                                          up, @RequestBody @Validated  CounselAAdoptRequest counselAAdoptRequest, @PathVariable Long id) {
        return ResponseEntity.ok().body(counselService.updateAnswerAdopt(id, counselAAdoptRequest, up));
    }

    @Operation(summary = "상담 답변의 댓글 추천하기", description = "counsel 레코드의 answer에 작성된 답변을 추천합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상담 답변 댓글 추천 완료", content = @Content(schema = @Schema(implementation = UserPrincipal.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = UserPrincipal.class)))
    })
    @PostMapping("/counsel/answer/{id}/recommend")
    public ResponseEntity<?> recommendAnswer(@AuthenticationPrincipal UserPrincipal me, @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(counselService.recommendAnswer(id, me));
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "상담 답변의 댓글을 신고하기", description = "counsel 레코드의 answer에 작성된 답변을 신고합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "상담 답변 댓글 신고 완료", content = @Content(schema = @Schema(implementation = Long.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = Long.class)))
    })
    @PostMapping("/counsel/answer/{id}/notice")
    public ResponseEntity<?> noticeAnswer(@AuthenticationPrincipal UserPrincipal me, @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(counselService.noticeAnswer(id, me));
        } catch (Exception e) {
            throw e;
        }
    }

    @Deprecated
    @Operation(summary = "상담 검색", description = "태그를 이용해 상담을 검색합니다 #를제외함.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "접속한 계정의 포인트 조회 완료", content = @Content(schema = @Schema(implementation = PointHistoryResponse.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = PointHistoryResponse.class)))
    })
    @GetMapping("/counsel/search")
    public ResponseEntity<ApiR<?>> search(String tag) {
        try {
            return ResponseEntity.ok(ApiR.createSuccess(counselService.search(tag)));
        } catch (Exception e) {
            throw e;
        }
    }
    @Operation(summary = "직업구분을 이용한 카테고리 통계", description = "직업구분을 이용한 카테고리 통계")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "접속한 계정의 포인트 조회 완료", content = @Content(schema = @Schema(implementation = PointHistoryResponse.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = PointHistoryResponse.class)))
    })
    @GetMapping("/counsel/search/gubn/{gubn}")
    public ResponseEntity<ApiR<?>> tagMoa(@PathVariable String gubn) {
        try {
            CounselSearchCountDto result = counselService.tagmoa(gubn);
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(result));
        } catch (Exception e) {
            throw e;
        }
    } // FIXME ADMIN api같음
//    @Operation(summary = "3243424", description = "테스트중")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "접속한 계정의 포인트 조회 완료", content = @Content(schema = @Schema(implementation = PointHistoryResponse.class))),
//            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = PointHistoryResponse.class)))
//    })
//    @GetMapping("/counsel/search/gubn/{code}")
//    public ResponseEntity<ApiR<?>> tagMoaGo(@PathVariable String code){
//        try{
//            List<CounselListResponse> result = counselService.tagMoaGo(code);
//            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(result));
//        }catch (Exception e){
//            throw  e;
//        }
//    }



//    @PatchMapping("/counsel/{id}")
//    public void update(@PathVariable Long id,
//        @RequestBody OrganizationUpdateRequest updateRequest) {
//        updateRequest.setId(id);
//        organizationService.update(updateRequest);
//    }

//    @GetMapping("/counsel/{id}")
//    public Organization findOne(@PathVariable Long id) {
//        return organizationService.findOne(id);
//    }

}
