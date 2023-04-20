package com.onz.modules.review.domain.embed;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Getter
@Setter
@Embeddable
public class Appr {
    @Size(max=1000)
    private String apprTxt;
    @Size(max=50)
    private String apprId;
    private ZonedDateTime apprDt;

    public Appr() {
    }

    public Appr(String apprTxt, String apprId, ZonedDateTime apprDt) {
        this.apprTxt = apprTxt;
        this.apprId = apprId;
        this.apprDt = apprDt;
    }
}
