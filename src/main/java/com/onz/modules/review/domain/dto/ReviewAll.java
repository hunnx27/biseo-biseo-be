package com.onz.modules.review.domain.dto;

import com.onz.common.web.dto.response.enums.YN;
import com.onz.modules.review.domain.enums.ItemCode;

public interface ReviewAll {
    String getType();
    Long getId();
//    String getState();
    YN getWorkExpOpenYn();
    String getItem_1();
    YN getItem_2();
    YN getItem_3();
    String getItem_4();
    String getItem_5();
    YN getItem_6();
    String getTopQ1();
    Long getCompanyId();
    String getCompanyName();
    String getEstablishmentTypeValue();
    Long getAccountId();
//    String getTxtAdmin();
    Long getWorkExp();
    String getZonecode();
    Long getAmt();
//    Long getAmtOld();
//    String getApprDt();
//    String getApprTxt();
//    String getApprId();
//    String getEndAtmYn();
    String getEtcAmt();
    String getEtcItems();
//    String getEtcYn();
    String getMapsidogunguName();
//    String getTopchoiceDt();
//    YN getTopchoiceYn();
//    YN getAnnYn();
//    String getImage1();
//    String getImage2();
//    String getImage3();
//    String getImage4();
//    String getImage5();
    ItemCode getItemB1();
    ItemCode getItemB2();
    ItemCode getItemB3();
    ItemCode getItemC1();
    ItemCode getItemC2();
    ItemCode getItemC3();
    ItemCode getItemD1();
    ItemCode getItemD2();
    ItemCode getLikeCode();
    String getImpCost();
//    String getWorkCost();
//    String getAddCost();
//    String getEtcCost();
//    Long getTotalCost();
    String getTxt();

}