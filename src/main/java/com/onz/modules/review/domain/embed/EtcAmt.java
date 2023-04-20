package com.onz.modules.review.domain.embed;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Getter
@Setter
@Embeddable
public class EtcAmt {
    @Size(max=50)
    private String etcAmt;
    @Size(max=1)
    private int etcYn;
    @Size(max=100)
    private String etcItems;
    @Size(max=1)
    private String endAmtYn;

    public EtcAmt() {
    }

    public EtcAmt(String etcAmt, int etcYn, String etcItems, String endAmtYn) {
        this.etcAmt = etcAmt;
        this.etcYn = etcYn;
        this.etcItems = etcItems;
        this.endAmtYn = endAmtYn;
    }
}
