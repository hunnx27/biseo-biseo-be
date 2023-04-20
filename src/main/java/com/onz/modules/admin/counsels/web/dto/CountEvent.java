package com.onz.modules.admin.counsels.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CountEvent {
    private Long all;
    private Integer QS01;
    private Integer QS02;
    private Integer QS03;
    private Integer QS04;
    private Integer QS05;
    private Integer QS06;
    private Integer QS07;
    private Integer QI01;
    private Integer QI02;
    private Integer QI03;
    private Integer QI04;
    private Integer QI05;
    private Integer QI06;

    public CountEvent(Long all, Integer QS01, Integer QS02, Integer QS03, Integer QS04, Integer QS05, Integer QS06, Integer QS07, Integer QI01, Integer QI02, Integer QI03, Integer QI04, Integer QI05, Integer QI06) {
        this.all = all;
        this.QS01 = QS01;
        this.QS02 = QS02;
        this.QS03 = QS03;
        this.QS04 = QS04;
        this.QS05 = QS05;
        this.QS06 = QS06;
        this.QS07 = QS07;
        this.QI01 = QI01;
        this.QI02 = QI02;
        this.QI03 = QI03;
        this.QI04 = QI04;
        this.QI05 = QI05;
        this.QI06 = QI06;
    }


//    public Long getAll() {
//        return all != null ? this.all : 0;
//    }
//
//    public Long getQS01() {
//        return QS01 != null ? this.QS01 : 0;
//    }
//
//    public Long getQS02() {
//        return QS02 != null ? this.QS02 : 0;
//    }
//
//    public Long getQS03() {
//        return QS03 != null ? this.QS03 : 0;
//    }
//
//    public Long getQS04() {
//        return QS04 != null ? this.QS04 : 0;
//    }
//
//    public Long getQS05() {
//        return QS05 != null ? this.QS05 : 0;
//    }
//
//    public Long getQS06() {
//        return QS06 != null ? this.QS06 : 0;
//    }
//
//    public Long getQS07() {
//        return QS07 != null ? this.QS07 : 0;
//    }
//
//    public Long getQI01() {
//        return QI01 != null ? this.QI01 : 0;
//    }
//
//    public Long getQI02() {
//        return QI02 != null ? this.QI02 : 0;
//    }
//
//    public Long getQI03() {
//        return QI03 != null ? this.QI03 : 0;
//    }
//
//    public Long getQI04() {
//        return QI04 != null ? this.QI04 : 0;
//    }
//
//    public Long getQI05() {
//        return QI05 != null ? this.QI05 : 0;
//    }
//
//    public Long getQI06() {
//        return QI06 != null ? this.QI06 : 0;
//    }
//
//
}
