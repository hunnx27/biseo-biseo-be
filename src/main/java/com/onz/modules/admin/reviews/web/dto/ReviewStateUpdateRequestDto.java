package com.onz.modules.admin.reviews.web.dto;

import com.onz.common.web.dto.response.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewStateUpdateRequestDto {
    private State state;
    private String adminTxt;
}
