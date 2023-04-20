package com.onz.modules.review.web;

import com.onz.common.web.ApiR;
import com.onz.common.web.BaseApiController;
import com.onz.common.web.dto.response.enums.ErrorCode;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.review.application.AmtReviewService;
import com.onz.modules.review.application.CompanyReviewService;
import com.onz.modules.review.application.InterviewService;
import com.onz.modules.review.application.ReviewService;
import com.onz.modules.review.domain.CompanyReview;
import com.onz.modules.review.domain.YearAmtReview;
import com.onz.modules.review.web.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/*
레거시 많아서 정리가 필요함
 */
@RequiredArgsConstructor
@RestController
@Tag(name = "리뷰 제어", description = "리뷰를 제어하는 api.")
public class ReviewController extends BaseApiController {


    private final CompanyReviewService companyReviewService;
    private final AmtReviewService amtReviewService;
    private final InterviewService interviewService;
    private final ReviewService reviewService;

    /*
    ALL
     */
    @Operation(summary = "모든리뷰검색 ", description = "모든 종류의 리뷰를 검색합니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = ReviewResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ReviewResponseDto.class)))
    })
    @GetMapping("/reviews/all")
    public ResponseEntity<ApiR<?>> findByAllReview( FindEstaRequestDto findEstaRequestDto,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC)
                    Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(reviewService.findByAllReview(findEstaRequestDto,pageable)));
        } catch (Exception e) {
            throw e;
        }
    }
    /*
    모든 내 리뷰 보기
     */
    @Operation(summary = "내가 작성한 리뷰 ", description = "모든 종류의 리뷰를 검색합니다...")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "완료", content = @Content(schema = @Schema(implementation = ReviewResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ReviewResponseDto.class)))
    })
    @GetMapping("/reviews/my")
    public ResponseEntity<ApiR<?>> findByAllMyReview( @AuthenticationPrincipal UserPrincipal me,
                                                    @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC)
                                                            Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(reviewService.findByAllMyReview(me,pageable)));
        } catch (Exception e) {
            throw e;
        }
    }
    /*
    AMT
     */
    @Operation(summary = "연봉 리뷰 등록", description = "연봉 리뷰를 등록합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "리뷰 등록 완료", content = @Content(schema = @Schema(implementation = AmtRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = AmtRequestDto.class)))
    })
    @PostMapping("/reviews/amt")
    public ResponseEntity<?> create(@AuthenticationPrincipal UserPrincipal me, @RequestBody @Validated AmtRequestDto amtRequestDto) {
            return ResponseEntity.ok().body(amtReviewService.create(amtRequestDto, me));

    }

    @Operation(summary = "연봉리뷰 보기", description = "연봉 리뷰를 조회합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 완료", content = @Content(schema = @Schema(implementation = YearAmtReview.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = YearAmtReview.class)))
    })
    @GetMapping("/reviews/amts")
    public ResponseEntity<ApiR<?>> amtReviewList(FindEstaRequestDto findEstaRequestDto,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC)
                    Pageable pageable
    ) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(amtReviewService.amtReviewList(findEstaRequestDto,pageable)));
        } catch (Exception e) {
            throw e;
            //return ApiR.createError(e.getMessage());
        }
        //return ApiR.createSuccess(amtReviewService.amtReviewList(pageable));
    }

//    @Operation(summary = "연봉리뷰 보기에서 상세검색", description = "연봉 리뷰를 조회합니다..")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "조회 완료", content = @Content(schema = @Schema(implementation = YearAmtReview.class))),
//            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = YearAmtReview.class)))
//    })
//    @GetMapping("/reviews/amt/find")
//    public ResponseEntity<ApiR<?>> amtReviewListfind(
//            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC)
//                    Pageable pageable, FindEstaRequestDto findEstaRequestDto
//    ) {
//
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(amtReviewService.amtReviewListfind(pageable)));
//        } catch (Exception e) {
//            throw e;
//            //return ApiR.createError(e.getMessage());
//        }
//        //return ApiR.createSuccess(amtReviewService.amtReviewList(pageable));
//    }
    /*
    Interview
     */
    @Operation(summary = "인터뷰 리뷰 등록", description = "인터뷰 리뷰를 등록합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "리뷰 등록 완료", content = @Content(schema = @Schema(implementation = InterviewRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = InterviewRequestDto.class)))
    })
    @PostMapping("/reviews/interview")
    public ResponseEntity<?> create(@AuthenticationPrincipal UserPrincipal me, @RequestBody @Validated InterviewRequestDto interviewRequestDto) {
        return ResponseEntity.ok().body(interviewService.create(interviewRequestDto, me));
    }

    @Operation(summary = "인터뷰 리뷰 보기", description = "인터뷰 리뷰를 조회합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "리뷰 조회 완료", content = @Content(schema = @Schema(implementation = YearAmtReview.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = YearAmtReview.class)))
    })
    @GetMapping("/reviews/interviews")
    public ResponseEntity<ApiR<?>> interviewReviewList(FindEstaRequestDto findEstaRequestDto,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC)
                    Pageable pageable
    ) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(interviewService.interviewReviewList(findEstaRequestDto,pageable)));
        } catch (Exception e) {
            throw e;
        }
    }

    /*
    companies
     */
    @Operation(summary = "기업 리뷰 등록", description = "기업 리뷰를 등록합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "리뷰 등록 완료", content = @Content(schema = @Schema(implementation = CompanyReviewRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompanyReviewRequestDto.class)))
    })
    @PostMapping(value = "/reviews/company",consumes = "multipart/form-data")
    public ResponseEntity<?> create(@AuthenticationPrincipal UserPrincipal me,  @RequestPart (value = "files", required = false) List<MultipartFile> files, @RequestPart(name = "data") @Valid CompanyReviewRequestDto companyReviewRequestDto) {
            return ResponseEntity.ok().body( companyReviewService.create(companyReviewRequestDto, me,files));
    }

    @Operation(summary = "기업 리뷰 보기", description = "기업 리뷰를 조회합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 완료", content = @Content(schema = @Schema(implementation = YearAmtReview.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = YearAmtReview.class)))
    })
    @GetMapping("/reviews/companies")
    public ResponseEntity<ApiR<?>> companyReviewList(FindEstaRequestDto findEstaRequestDto,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC)
                    Pageable pageable
    ) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(companyReviewService.companyReviewList(findEstaRequestDto,pageable)));
        } catch (Exception e) {
            throw e;
        }
    }

}





/*
legacy code
 */

//    @Operation(summary = "선택 리뷰 불러오기", description = "리뷰 레코드를 불러옵니다..")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "불러오기 완료", content = @Content(schema = @Schema(implementation = CompanySearchRequest.class))),
//            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompanySearchRequest.class)))
//    })
//    @GetMapping("/reviews")
//    public Page<Company> list(@RequestBody CompanySearchRequest searchRequest) {
//        return companyService.list(searchRequest);
//    }
//
//    @Operation(summary = "리뷰 등록", description = "리뷰를 등록합니다..")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "리뷰 등록 완료", content = @Content(schema = @Schema(implementation = CompanyCreateRequest.class))),
//            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompanyCreateRequest.class)))
//    })
//    @PostMapping("/reviews")
//    public void create(@RequestBody CompanyCreateRequest createRequest) {
//        companyService.create(modelMapper.map(createRequest, Company.class));
//    }
//
//    @Operation(summary = "리뷰 수정", description = "리뷰를 수정합니다..")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "리뷰 수정 완료", content = @Content(schema = @Schema(implementation = CompanyUpdateRequest.class))),
//            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = CompanyUpdateRequest.class)))
//    })
//    @PatchMapping("/reviews/{id}")
//    public void update(@PathVariable Long id,
//                       @RequestBody CompanyUpdateRequest updateRequest) {
//        updateRequest.setId(id);
//        companyService.update(updateRequest);
//    }

//    @Operation(summary = "리뷰 단일 검색", description = "리뷰를 하나 검색합니다..")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "검색 완료", content = @Content(schema = @Schema(implementation = PathVariable.class))),
//            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = HttpServletResponse.class)))
//    })
//    @GetMapping("/reviews/{id}")
//    public Company findOne(@PathVariable Long id) {
//        return companyService.findOne(id);
//    }

//    @Operation(summary = "기업에 등록하기", description = "기업에 리뷰를 등록합니다..")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "등록 완료", content = @Content(schema = @Schema(implementation = ResponseEntity.class))),
//            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = ResponseEntity.class)))
//    })
//    @PostMapping("/reviews/{id}/add")
//    public ResponseEntity addAccount(@PathVariable Long id, Account account) {
////        Company one = companyService.findOne(id);
////        one.addAccount(account);
//        return ResponseEntity.ok().build();
//    }