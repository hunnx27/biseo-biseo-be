package com.onz.modules.review.domain.embed;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Getter
@Setter
@Embeddable
public class Topchoice {
    @Size(max=1)
    private String topchoiceYn;
    private ZonedDateTime topchoiceDt;

    public Topchoice() {
    }

    public Topchoice(String topchoiceYn, ZonedDateTime topchoiceDt) {
        this.topchoiceYn = topchoiceYn;
        this.topchoiceDt = topchoiceDt;
    }
}
