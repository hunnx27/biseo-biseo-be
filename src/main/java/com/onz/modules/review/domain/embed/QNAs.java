package com.onz.modules.review.domain.embed;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Getter
@Setter
@Embeddable
public class QNAs {
    @Size(max=1000)
    private String q_1;
    @Size(max=1000)
    private String q_2;
    @Size(max=1000)
    private String q_3;
    @Size(max=1000)
    private String a_1;
    @Size(max=1000)
    private String a_2;
    @Size(max=1000)
    private String a_3;
    @Size(max=1000)
    private String q_1_admin;
    @Size(max=1000)
    private String q_2_admin;
    @Size(max=1000)
    private String q_3_admin;
    @Size(max=1000)
    private String a_1_admin;
    @Size(max=1000)
    private String a_2_admin;
    @Size(max=1000)
    private String a_3_admin;
    private ZonedDateTime adminTxtDt1;
    private ZonedDateTime adminTxtDt2;
    private ZonedDateTime adminTxtDt3;

    public QNAs() {
    }

    public QNAs(String q_1, String q_2, String q_3, String a_1, String a_2, String a_3, String q_1_admin, String q_2_admin, String q_3_admin, String a_1_admin, String a_2_admin, String a_3_admin, ZonedDateTime adminTxtDt1, ZonedDateTime adminTxtDt2, ZonedDateTime adminTxtDt3) {
        this.q_1 = q_1;
        this.q_2 = q_2;
        this.q_3 = q_3;
        this.a_1 = a_1;
        this.a_2 = a_2;
        this.a_3 = a_3;
        this.q_1_admin = q_1_admin;
        this.q_2_admin = q_2_admin;
        this.q_3_admin = q_3_admin;
        this.a_1_admin = a_1_admin;
        this.a_2_admin = a_2_admin;
        this.a_3_admin = a_3_admin;
        this.adminTxtDt1 = adminTxtDt1;
        this.adminTxtDt2 = adminTxtDt2;
        this.adminTxtDt3 = adminTxtDt3;
    }
}
