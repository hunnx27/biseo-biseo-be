package com.onz.modules.review.domain.embed;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Getter
@Setter
@Embeddable
public class AdminTxt {
    @Size(max=1000)
    private String adminTxt;
    private ZonedDateTime adminTxtDt;
    @Size(max=50)
    private String adminTxtId;

    public AdminTxt() {
    }

    public AdminTxt(String adminTxt, ZonedDateTime adminTxtDt, String adminTxtId) {
        this.adminTxt = adminTxt;
        this.adminTxtDt = adminTxtDt;
        this.adminTxtId = adminTxtId;
    }
}
