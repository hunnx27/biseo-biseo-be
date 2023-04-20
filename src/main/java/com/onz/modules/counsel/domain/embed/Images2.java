package com.onz.modules.counsel.domain.embed;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Getter
@Setter
@Embeddable
public class Images2 {
    @Size(max=200)
    private String imgUrl;

    public Images2() {
    }

    public Images2(String imgUrl) {
        this.imgUrl = imgUrl;

    }
}
