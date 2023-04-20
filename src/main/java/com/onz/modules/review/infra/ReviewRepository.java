package com.onz.modules.review.infra;


import com.onz.modules.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Company, Long>,
        ReviewRepositoryExtension {
    //String FIND_DISTINCT_IDS = "SELECT DISTINCT a.sigungu_code as sigunguCode , a.sido_name as sidoName , a.sigungu_name as sigunguName from address a where a.sigungu_code = :sigungu_code";

    String FIND_ONE_IDS ="SELECT " +
            "'COMPANY' AS type, " +
            "cr.id AS id, " +
            "cr.is_delete AS isDelete , " +
            "cr.modified_at AS modifiedAt , " +
            "cr.state AS state , " +
            "cr.created_at AS createdAt,  " +
            "cr.work_exp_open_yn AS workExpOpenYn , " +
            "c.id AS companyId, " +
            "c.office_name AS companyName, " +
            "c.establishment_type AS establishmentTypeValue, " +
            "cr.account_id  As accountId, " +
            "cr.txt AS txt , " +
            "NULL AS item_1 , " +
            "NULL AS item_2 , " +
            "NULL AS item_3 , " +
            "NULL AS item_4 , " +
            "NULL AS item_5 , " +
            "NULL AS item_6 , " +
            "NULL AS topQ1 , " +
            "NULL AS txtAdmin , " +
            "cr.work_exp AS workExp , " +
            "c.zonecode AS zonecode , " +
            "NULL AS amt , " +
            "NULL AS amtOld , " +
            "NULL AS apprDt , " +
            "NULL AS apprId , " +
            "NULL AS apprTxt , " +
            "NULL AS endAtmYn , " +
            "NULL AS etcAmt , " +
            "NULL AS etcItems , " +
            "NULL AS etcYn , " +
            "NULL AS mapsidogunguName , " +
            "NULL AS topchoiceDt , " +
            "NULL AS topchoiceYn , " +
            "NULL AS annYn , " +
            "cr.image1 AS image1 , " +
            "cr.image2 AS image2 , " +
            "cr.image3 AS image3 , " +
            "cr.image4 AS image4 , " +
            "cr.image5 AS image5 , " +
            "cr.item_b1 AS itemB1 , " +
            "cr.item_b2 AS itemB2 , " +
            "cr.item_b3 AS itemB3 ,  " +
            "cr.item_c1 AS itemC1 , " +
            "cr.item_c2 AS itemC2 , " +
            "cr.item_c3 AS itemC3 , " +
            "cr.item_d1 AS itemD1 , " +
            "cr.item_d2 AS itemD2 , " +
            "cr.like_code AS likeCode " +
            "FROM company_review cr INNER JOIN company c ON cr.company_id = c.id WHERE 1=1  ";
    String FIND_TWO_IDS =
            "SELECT " +
                    "'INTERVIEW' AS type, " +
                    "ir.id AS id, " +
                    "ir.is_delete AS is_delete , " +
                    "ir.modified_at AS modified_at , " +
                    "ir.state AS state , " +
                    "ir.created_at AS createdAt , " +
                    "ir.work_exp_open_yn AS work_exp_open_yn , " +
                    "c.id AS companyId, " +
                    "c.office_name AS companyName, " +
                    "c.establishment_type AS establishmentTypeValue, " +
                    "ir.account_id  As account_id, " +
                    "NULL AS txt, " +
                    "ir.item_1 AS item_1 , " +
                    "ir.item_2 AS item_2 , " +
                    "ir.item_3 AS item_3 , " +
                    "ir.item_4 AS item_4 , " +
                    "ir.item_5 AS item_5 , " +
                    "ir.item_6 AS item_6 , " +
                    "ir.topq1 AS topq1 , " +
                    "ir.txt_admin  AS txt_admin , " +
                    "ir.work_exp AS work_exp , " +
                    "c.zonecode AS zonecode , " +
                    "NULL AS amt , " +
                    "NULL AS amt_old , " +
                    "NULL AS appr_dt , " +
                    "NULL AS appr_id , " +
                    "NULL AS appr_txt , " +
                    "NULL AS end_atm_yn , " +
                    "NULL AS etc_amt , " +
                    "NULL AS etc_items , " +
                    "NULL AS etc_yn , " +
                    "NULL AS mapsidogungu_name , " +
                    "NULL AS topchoice_dt , " +
                    "NULL AS topchoice_yn , " +
                    "NULL AS ann_yn , " +
                    "NULL AS image1 , " +
                    "NULL AS image2 , " +
                    "NULL AS image3 , " +
                    "NULL AS image4 , " +
                    "NULL AS image5 , " +
                    "NULL AS item_b1 , " +
                    "NULL AS item_b2 , " +
                    "NULL AS item_b3 ,  " +
                    "NULL AS item_c1 , " +
                    "NULL AS item_c2 , " +
                    "NULL AS item_c3 , " +
                    "NULL AS item_d1 , " +
                    "NULL AS item_d2 , " +
                    "NULL AS like_code " +
                    "FROM interview_review ir INNER JOIN company c ON ir.company_id = c.id  WHERE 1=1 " ;
    String FIND_THR_IDS =
                    "SELECT " +
                    "'AMT' AS type, " +
                    "yar.id AS id, " +
                    "yar.is_delete AS is_delete , " +
                    "yar.modified_at AS modified_at , " +
                    "yar.state AS state , " +
                    "yar.created_at AS createdAt , " +
                    "yar.work_exp_open_yn AS work_exp_open_yn , " +
                    "c.id AS companyId, " +
                    "c.office_name AS companyName, " +
                    "c.establishment_type AS establishmentTypeValue, " +
                    "yar.account_id  As account_id , " +
                    "NULL AS txt, " +
                    "NULL AS item_1 , " +
                    "NULL AS item_2 , " +
                    "NULL AS item_3 , " +
                    "NULL AS item_4 , " +
                    "NULL AS item_5 , " +
                    "NULL AS item_6 , " +
                    "NULL AS topq1 , " +
                    "NULL AS txt_admin , " +
                    "yar.work_exp AS work_exp , " +
                    "c.zonecode AS zonecode , " +
                    "yar.amt AS amt , " +
                    "yar.amt_old AS amt_old , " +
                    "yar.appr_dt AS appr_dt , " +
                    "yar.appr_id AS appr_id , " +
                    "yar.appr_txt AS appr_txt , " +
                    "yar.end_atm_yn AS end_atm_yn , " +
                    "yar.etc_amt AS etc_amt , " +
                    "yar.etc_items AS etc_items , " +
                    "yar.etc_yn AS etc_yn , " +
                    "yar.mapsidogungu_name AS mapsidogungu_name , " +
                    "yar.topchoice_dt AS topchoice_dt , " +
                    "yar.topchoice_yn AS topchoice_yn , " +
                    "yar.ann_yn AS ann_yn , " +
                    "NULL AS image1 , " +
                    "NULL AS image2 , " +
                    "NULL AS image3 , " +
                    "NULL AS image4 , " +
                    "NULL AS image5 , " +
                    "NULL AS item_b1 , " +
                    "NULL AS item_b2 , " +
                    "NULL AS item_b3 ,  " +
                    "NULL AS item_c1 , " +
                    "NULL AS item_c2 , " +
                    "NULL AS item_c3 , " +
                    "NULL AS item_d1 , " +
                    "NULL AS item_d2 , " +
                    "NULL AS like_code " +
                    "FROM year_amt_review yar INNER JOIN company c ON yar.company_id = c.id  WHERE 1=1 ";


//            "SELECT "
//            + "id AS id, is_delete AS is_delete , modified_at AS modified_at , state AS state , created_at AS created_at , " +
//            "work_exp_open_yn AS work_exp_open_yn , company_id AS company_id, account_id  As account_id,  item_1 AS item_1, " +
//            "  item_2 AS item_2,  item_3 AS item_3,  item_4 AS item_4,  item_5 AS item_5,  item_6 AS item_6,  topq1 AS topq1 " +
//            "FROM interview_review ir " +
//            "UNION ALL " +
//            "SELECT " +
//            "id AS id,  is_delete AS is_delete , modified_at AS modified_at , state AS state , created_at AS created_at , " +
//            "work_exp_open_yn AS work_exp_open_yn , company_id AS company_id  , account_id  As account_id, NULL AS item_1 , NULL AS item_2 , " +
//            "NULL AS item_3 , NULL AS item_4 , NULL AS item_5 , NULL AS item_6, NULL AS topq1 " +
//            "FROM year_amt_review yar " +
//            "UNION ALL " +
//            "SELECT " +
//            "id AS id, is_delete AS is_delete , modified_at AS modified_at , state AS state , created_at AS created_at , " +
//            "work_exp_open_yn AS work_exp_open_yn , company_id AS company_id , account_id  As account_id, NULL AS item_1 , " +
//            "NULL AS item_2 , NULL AS item_3 , NULL AS item_4 , NULL AS item_5 , NULL AS item_6, NULL AS topq1 " +
//            "FROM company_review cr";

//    @Query(value = FIND_ALL_IDS, nativeQuery = true)
//    List<ReviewAll> findByAllReview(FindEstaRequestDto findEstaRequestDto,Pageable pageable);

}
