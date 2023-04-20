package com.onz.modules.review.domain.embed;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Getter
@Setter
@Embeddable
public class Images {
    @Size(max=200)
    private String image1;
    @Size(max=200)
    private String image2;
    @Size(max=200)
    private String image3;
    @Size(max=200)
    private String image4;
    @Size(max=200)
    private String image5;

    public Images() {
    }

    public Images(String image1, String image2, String image3, String image4, String image5) {
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.image5 = image5;
    }
}
