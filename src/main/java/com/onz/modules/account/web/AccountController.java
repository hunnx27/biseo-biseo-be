package com.onz.modules.account.web;

import com.onz.common.web.ApiR;
import com.onz.common.web.dto.response.enums.Role;
import com.onz.modules.account.application.AccountService;
import com.onz.modules.account.domain.Account;
import com.onz.modules.account.web.dto.request.AccountCreateRequest;
import com.onz.modules.account.web.dto.request.AccountMyinfoUpdateRequest;
import com.onz.modules.account.web.dto.request.AccountSearchRequest;
import com.onz.modules.account.web.dto.request.AccountUpdateRequest;
import com.onz.common.web.BaseApiController;
import com.onz.modules.account.web.dto.response.AccountResponse;
import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.common.pointHistory.web.dto.response.PointHistoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "계정제어", description = "계정 관련 api입니다.")
public class AccountController extends BaseApiController {

    private final AccountService accountService;
    private final ModelMapper modelMapper;

    @Operation(summary = "계정 생성", description = "변수를 이용하여 accounts 레코드를 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "계정 생성 완료", content = @Content(schema = @Schema(implementation = AccountCreateRequest.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = AccountCreateRequest.class)))
    })
    @PostMapping("/accounts")
    public ResponseEntity<?> create(@RequestBody @Validated AccountCreateRequest accountCreateRequest) {
        return ResponseEntity.ok().body(accountService.create(modelMapper.map(accountCreateRequest, Account.class)));
    }

    @Operation(summary = "계정 목록 조회", description = "변수를 이용하여 accounts 레코드를 리스트를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "계정 리스트 조회 완료", content = @Content(schema = @Schema(implementation = AccountSearchRequest.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = AccountSearchRequest.class)))
    })
    @GetMapping("/accounts")
    public ResponseEntity<ApiR<?>> list(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC)
                    Pageable pageable,
            AccountSearchRequest accountSearchRequest) {
        try {
            Page<Account> list = accountService.list(accountSearchRequest, pageable);
            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(list));
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "계정 단일 조회", description = "변수를 이용하여 account 단일 레코드를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "계정 단일 조회 완료", content = @Content(schema = @Schema(implementation = Account.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = Account.class)))
    })
    @GetMapping("/accounts/{id}")
    public ResponseEntity<ApiR<?>> findOne(@PathVariable Long id) {
        try {
            Account one = accountService.findOne(id);
            if(one.getRole().equals(Role.USER)) {
                accountService.updateGrade(one);
                return ResponseEntity.status(HttpStatus.OK).body((ApiR.createSuccess(one)));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((ApiR.createError(HttpStatus.UNAUTHORIZED.getReasonPhrase())));
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "계정 수정", description = "변수를 이용하여 account 레코드를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "계정 수정 완료", content = @Content(schema = @Schema(implementation = AccountUpdateRequest.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = AccountUpdateRequest.class)))
    })
    @PutMapping("/accounts/{id}")
    @Deprecated
    public void update(@AuthenticationPrincipal UserPrincipal up,
                       @RequestBody AccountUpdateRequest accountUpdateRequest) {
        try {
            Long update = accountService.update(up,accountUpdateRequest);
            ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccess(update));
        } catch (Exception e) {
            throw e;
        }
    }

//    @Operation(summary = "계정 삭제", description = "변수를 이용하여 account 레코드를 삭제합니다.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "계정 삭제 완료", content = @Content(schema = @Schema(implementation = SecurityContextHolder.class))),
//            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = SecurityContextHolder.class)))
//    })
//    @DeleteMapping("/accounts/{id}")
//    public ResponseEntity<ApiR<?>> delete(@PathVariable Long id) {
//        try {
//            boolean delete = accountService.delete(id);
//            if (delete) {
//                SecurityContextHolder.clearContext();
//            }
//            return ResponseEntity.status(HttpStatus.OK).body(ApiR.createSuccessWithNoContent());
//        } catch (Exception e) {
//            throw e;
//        }
//
//    }

    @Operation(summary = "현재 접속한 계정 조회", description = "변수를 이용하여 접속한 계정 레코드를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "접속된 계정 조회 완료", content = @Content(schema = @Schema(implementation = UserPrincipal.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = UserPrincipal.class)))
    })
    @GetMapping("/accounts/me")
//    public ResponseEntity<ApiR<?>> me(@CurrentPrincipal UserPrincipal principal) {
    public ResponseEntity<ApiR<?>> me(@AuthenticationPrincipal UserPrincipal up) {
        try {
            AccountResponse response = new AccountResponse(accountService.findOne(up.getId()));
            return ResponseEntity.ok(ApiR.createSuccess(response));
//        return ResponseEntity.ok(new Account());
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "현재 접속한 계정 삭제", description = "변수를 이용하여 접속한 계정을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "접속한 계정 삭제 완료", content = @Content(schema = @Schema(implementation = AccountResponse.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = AccountResponse.class)))
    })
    @DeleteMapping("/accounts/me")
    public ResponseEntity<ApiR<?>> deleteMe(@AuthenticationPrincipal UserPrincipal up) {
        try {
            Account account = accountService.deleteMeSoft(up.getId());
            AccountResponse response = new AccountResponse();
            return ResponseEntity.ok(ApiR.createSuccessWithNoContent());
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "현재 접속한 계정 정보수정", description = "변수를 이용하여 접속한 계정의 레코드를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "접속한 계정의 정보 조회 완료", content = @Content(schema = @Schema(implementation = AccountMyinfoUpdateRequest.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = AccountMyinfoUpdateRequest.class)))
    })
    @PutMapping("/accounts/me/myinfo")
    public ResponseEntity<ApiR<?>> getMyinfo(@AuthenticationPrincipal UserPrincipal up, @RequestBody AccountMyinfoUpdateRequest request) {
        try {
            Account account = accountService.updateMyItem(up.getId(), request);
            return ResponseEntity.ok(ApiR.createSuccess(account));
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "현재 접속한 계정 포인트 조회", description = "변수를 이용하여 접속한 계정의 포인트 레코드를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "접속한 계정의 포인트 조회 완료", content = @Content(schema = @Schema(implementation = PointHistoryResponse.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = PointHistoryResponse.class)))
    })
    @GetMapping("/accounts/me/pointHistories")
    public ResponseEntity<ApiR<?>> getMyPointHistories(@AuthenticationPrincipal UserPrincipal up) {
        // FIXME Pageable추가 필수
        try {
            Page<PointHistoryResponse> list = accountService.getMyPointHistories(up.getId());
            return ResponseEntity.ok(ApiR.createSuccess(list));
        } catch (Exception e) {
            throw e;
        }
    }

}
