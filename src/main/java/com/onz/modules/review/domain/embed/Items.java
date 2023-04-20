package com.onz.modules.review.domain.embed;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Getter
@Setter
@Embeddable
public class Items {
    @Size(max=10)
    private String item_1;
    @Size(max=10)
    private String item_2;
    @Size(max=10)
    private String item_3;
    @Size(max=10)
    private String item_4;
    @Size(max=10)
    private String item_5;
    @Size(max=10)
    private String item_6;

    public Items() {
    }

    public Items(String item_1, String item_2, String item_3, String item_4, String item_5, String item_6) {
        this.item_1 = item_1;
        this.item_2 = item_2;
        this.item_3 = item_3;
        this.item_4 = item_4;
        this.item_5 = item_5;
        this.item_6 = item_6;
    }
}
