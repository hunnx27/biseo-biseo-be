package com.biseo.modules.follower.web;

import com.biseo.common.web.ApiR;
import com.biseo.common.web.BaseApiController;
import com.biseo.modules.auth.web.dto.UserPrincipal;
import com.biseo.modules.follower.application.FollowerService;
import com.biseo.modules.review.web.dto.AmtRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "팔로우 제어", description = "팔로우 제어하는 api.")
public class FollowerController extends BaseApiController {
    private final FollowerService followerService;
    @Operation(summary = "팔로우 등록", description = "팔로우를 등록합니다..")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "팔로우 등록 완료", content = @Content(schema = @Schema(implementation = AmtRequestDto.class))),
            @ApiResponse(responseCode = "400", description = "존재하지 않는 리소스 접근", content = @Content(schema = @Schema(implementation = AmtRequestDto.class)))
    })
    @PostMapping("/companies/{id}")
    public ApiR<?> create(@AuthenticationPrincipal UserPrincipal me, @PathVariable Long id) {
        try {
            return followerService.create(me, id);
        } catch (Exception e) {
            throw e;
        }
    }



}
