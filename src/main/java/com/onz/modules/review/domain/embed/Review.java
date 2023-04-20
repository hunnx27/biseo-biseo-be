package com.onz.modules.review.domain.embed;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@Embeddable
public class Review {
//    @Size(max=50)
//    private String review_id;
    @Size(max=11)
    private int code;
    @Size(max=1)
    private String state;
    @Embedded
    private Topchoice topchoice;
    @Embedded
    private Appr appr;

    public Review() {
    }

    public Review(int code, String state, Topchoice topchoice, Appr appr) {
        this.code = code;
        this.state = state;
        this.topchoice = topchoice;
        this.appr = appr;
    }
}
