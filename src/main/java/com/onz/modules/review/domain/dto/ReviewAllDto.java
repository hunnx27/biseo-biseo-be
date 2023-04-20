package com.onz.modules.review.domain.dto;

import com.onz.common.web.dto.response.enums.State;
import com.onz.common.web.dto.response.enums.YN;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;


@Slf4j
@Getter
@Setter
public class ReviewAllDto {
    private String type;
    private Long id;
    private String itemB1;
    private String itemB2;
    private String itemB3;
    private String itemC1;
    private String itemC2;
    private String itemC3;
    private String itemD1;
    private String itemD2;
    private String likeCode;
    private String createdAt;
    private ZonedDateTime modifiedAt;

    private Long accountId;
    private YN isDelete;
    private State state;
    private YN workExpOpenYn;
    private String item_1;
    private YN item_2;
    private YN item_3;
    private String item_4;
    private String item_5;
    private YN item_6;
    private String topQ1;
    private Long companyId;
    private String companyName;
    private String establishmentTypeValue;
    private Long workExp;
    private String zonecode;
    private Long amt;
    private String etcAmt;
    private String etcItems;
    private String mapsidogunguName;
    private String impCost;
    private String txt;
    private String txtAdmin;
    private Long amtOld;
    private String apprId;
    private String apprTxt;
    private String apprDt;
    private String ectAmt;
    private YN endAtmYn;
    private YN etcYn;
    private YN topchoiceYn;
    private YN annYn;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;

    public ReviewAllDto(String type, Long id, Long accountId, String establishmentTypeValue,
                        String isDelete,
                        String state, String createdAt,String workExpOpenYn, Long companyId, String companyName,
                        String txt,
                        String item_1, String item_2, String item_3, String item_4, String item_5,String item_6,
                        String topQ1, String txtAdmin, Long workExp, String zonecode,
                        Long amt,String apprId,String apprTxt,String apprDt, String etcAmt,String etcItems, String mapsidogunguName,
                        String image1, String image2, String image3, String image4, String image5,
                        String itemB1, String itemB2, String itemB3, String itemC1, String itemC2, String itemC3, String itemD1, String itemD2, String likeCode
    ) {
        this.type = type;
        this.id = id;
        this.accountId = accountId;
        this.establishmentTypeValue= establishmentTypeValue;
        this.isDelete = isDelete != null ? YN.valueOf(isDelete):YN.Y;
        this.state = state !=null ? State.valueOf(state):State.W;
        this.createdAt =  createdAt;
        this.workExpOpenYn = workExpOpenYn != null ? YN.valueOf(workExpOpenYn) : YN.Y;
        this.companyId = companyId;
        this.companyName = companyName;
        this.txt = txt;
        this.item_1 = item_1;
        this.item_2 = item_2 != null ? YN.valueOf(item_2) : YN.Y;
        this.item_3 = item_3 != null ? YN.valueOf(item_3) : YN.Y;
        this.item_4 = item_4;
        this.item_5 = item_5;
        this.item_6 = item_6 != null ? YN.valueOf(item_6) : YN.Y;
        this.topQ1 = topQ1;
        this.txtAdmin = txtAdmin;
        this.workExp = workExp;
        this.zonecode = zonecode;
        this.amt = amt;
//        this.amtOld = amtOld;
        this.apprId = apprId;
        this.apprTxt = apprTxt;
        this.apprDt=apprDt;
        this.endAtmYn = endAtmYn!=null? YN.valueOf(String.valueOf(endAtmYn)):YN.Y;;
        this.etcAmt = etcAmt;
        this.etcItems = etcItems;
//        this.etcYn = etcYn!=null? YN.valueOf(etcYn):YN.Y;;
        this.mapsidogunguName = mapsidogunguName;
//        this.topchoiceYn = topchoiceYn!=null? YN.valueOf(String.valueOf(topchoiceYn)):YN.Y;;
//        this.annYn = annYn!=null? YN.valueOf(annYn):YN.Y;;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.image5 = image5;
        this.itemB1 = itemB1;
        this.itemB2 = itemB2;
        this.itemB3 = itemB3;
        this.itemC1 = itemC1;
        this.itemC2 = itemC2;
        this.itemC3 = itemC3;
        this.itemD1 = itemD1;
        this.itemD2 = itemD2;
        this.likeCode = likeCode;

    }

//
//        public ReviewAllDto(String type, Long id, Long accountId, String establishmentTypeValue) {
//            this.type=type;
//            this.id=id;
//            this.accountId=accountId;
//            this.establishmentTypeValue=establishmentTypeValue;
//
//        }


//       this.itemB1 = ItemCode.valueOf(itemB1);
//        this.itemB2 = ItemCode.valueOf(itemB2);
//        this.itemB3 = ItemCode.valueOf(itemB3);
//        this.itemC1 = ItemCode.valueOf(itemC1);
//        this.itemC2 = ItemCode.valueOf(itemC2);
//        this.itemC3 = ItemCode.valueOf(itemC3);
//        this.itemD1 = ItemCode.valueOf(itemD1);
//        this.itemD2 = ItemCode.valueOf(itemD2);
//        this.likeCode = ItemCode.valueOf(likeCode);
    //
    //    String getTxtAdmin();
    //    Long getAmtOld();
//    String getApprDt();
//    String getApprTxt();
//    String getApprId();
//    String getEndAtmYn();
    //    String getEtcYn();
    //    String getTopchoiceDt();
//    YN getTopchoiceYn();
//    YN getAnnYn();
//    String getImage1();
//    String getImage2();
//    String getImage3();
//    String getImage4();
//    String getImage5();
//    String getWorkCost();
//    String getAddCost();
//    String getEtcCost();
//    Long getTotalCost();
}
