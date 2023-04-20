package com.onz.modules.review.infra;

import com.onz.modules.auth.web.dto.UserPrincipal;
import com.onz.modules.company.domain.Company;
import com.onz.modules.counsel.domain.Counsel;
import com.onz.modules.review.domain.dto.ReviewAllDto;
import com.onz.modules.review.web.dto.FindEstaRequestDto;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.onz.modules.review.infra.ReviewRepository.*;


//@Repository
//public class AccountRepositoryExtensionImpl extends QuerydslRepositorySupport implements
//        AccountRepositoryExtension {
//
//    private final JPAQueryFactory qf;
//    private final EntityManager em;
//
//    public AccountRepositoryExtensionImpl(JPAQueryFactory qf, EntityManager em) {
//        super(Account.class);
//        this.qf = qf;
//        this.em = em;
//    }

@Slf4j
public class ReviewRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        ReviewRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager em;

    public ReviewRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory, EntityManager em) {
        super(Company.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.em = em;
    }

    public List<ReviewAllDto> findByAllReview(FindEstaRequestDto findEstaRequestDto, Pageable pageable) {
        String zoneCode = findEstaRequestDto.getSido() + findEstaRequestDto.getGungu();
        //FIND_ALL_IDS
//        String query = FIND_ALL_IDS;
        // 기관리뷰
        String query1 = FIND_ONE_IDS;
        String where1 = " AND cr.state= 'A' AND cr.company_id != -1 AND cr.account_id != -1 ";
        Map<String, Object> params = new HashMap<>();
        if (findEstaRequestDto.getSido()!=null) {
            //전체검색 시도가 11,22 일떄
            if (findEstaRequestDto.getGungu() == null) {//군구가 널이라면 11  11000          }
                where1 += " AND c.zonecode LIKE concat(:data_zone, '%') " ;
                params.put("data_zone", findEstaRequestDto.getSido());

            }else{
                //만약 시도가 11인데 군구가 정확하면 11123
                where1 += " AND c.zonecode = :data_zone ";
                params.put("data_zone",zoneCode);
            }
        }else {//시도가 널이면
            if (findEstaRequestDto.getGungu() == null) {
                //시도가 99인데 군구도 널일떄->전체검색 99 999
            } else {
                //시도가 99인데 군구가 정확하다면? 99 123
            }
        }if(findEstaRequestDto.getEstablishmentType()!=null){
            if(findEstaRequestDto.getEstablishmentType().name().equals("all")){
                //전체검색
            }else{
                where1 += " AND c.establishment_type = :esta ";
                params.put("esta",findEstaRequestDto.getEstablishmentType().name());
            }
        }
        if(findEstaRequestDto.getInterestCompany()!=null){
            if(findEstaRequestDto.getInterestCompany().name().equals("all")){
                //전체
            }else{
                where1 += " AND c.interest_company = :inter ";
                params.put("inter",findEstaRequestDto.getInterestCompany().getCode());
            }
        }
        query1 += where1;
        // 인터뷰리뷰
        String query2 = FIND_TWO_IDS;
        String where2 = " AND ir.state= 'A' AND ir.company_id != -1 AND ir.account_id != -1 ";
        if (findEstaRequestDto.getSido()!=null) {
            //전체검색 시도가 11,22 일떄
            if (findEstaRequestDto.getGungu() == null) {//군구가 널이라면 11  11000          }
                where2 += " AND c.zonecode LIKE concat(:data_zone, '%') " ;
                params.put("data_zone", findEstaRequestDto.getSido());

            }else{
                //만약 시도가 11인데 군구가 정확하면 11123
                where2 += " AND c.zonecode = :data_zone ";
                params.put("data_zone",zoneCode);
            }
        }else {//시도가 널이면
            if (findEstaRequestDto.getGungu() == null) {
                //시도가 99인데 군구도 널일떄->전체검색 99 999
            } else {
                //시도가 99인데 군구가 정확하다면? 99 123
            }
        }if(findEstaRequestDto.getEstablishmentType()!=null){
            if(findEstaRequestDto.getEstablishmentType().name().equals("all")){
                //전체검색
            }else{
                where2 += " AND c.establishment_type = :esta ";
                params.put("esta",findEstaRequestDto.getEstablishmentType().name());
            }
        }
        if(findEstaRequestDto.getInterestCompany()!=null){
            if(findEstaRequestDto.getInterestCompany().name().equals("all")){
                //전체
            }else{
                where2 += " AND c.interest_company = :inter ";
                params.put("inter",findEstaRequestDto.getInterestCompany().getCode());
            }
        }
        query2 += where2;
        // 연봉리뷰
        String query3 = FIND_THR_IDS;
        String where3 = " AND yar.state = 'A' AND yar.company_id != -1 AND yar.account_id != -1  ";
        if (findEstaRequestDto.getSido()!=null) {
            //전체검색 시도가 11,22 일떄
            if (findEstaRequestDto.getGungu() == null) {//군구가 널이라면 11  11000          }
                where3 += " AND c.zonecode LIKE concat(:data_zone, '%') ";
                params.put("data_zone", findEstaRequestDto.getSido());

            }else{
                //만약 시도가 11인데 군구가 정확하면 11123
                where3 += " AND c.zonecode = :data_zone ";
                params.put("data_zone",zoneCode);
            }
        }else {//시도가 널이면
            if (findEstaRequestDto.getGungu() == null) {
                //시도가 99인데 군구도 널일떄->전체검색 99 999
            } else {
                //시도가 99인데 군구가 정확하다면? 99 123
            }
        }if(findEstaRequestDto.getEstablishmentType()!=null){
            if(findEstaRequestDto.getEstablishmentType().name().equals("all")){
                //전체검색
            }else{
                where3 += " AND c.establishment_type = :esta ";
                params.put("esta",findEstaRequestDto.getEstablishmentType().name());
            }
        }
        if(findEstaRequestDto.getInterestCompany()!=null){
            if(findEstaRequestDto.getInterestCompany().name().equals("all")){
                //전체
            }else{
                        where3 += " AND c.interest_company = :inter ";
                params.put("inter",findEstaRequestDto.getInterestCompany().getCode());
            }
        }
        query3 += where3;

        String query = query1 + " UNION ALL " + query2 + " UNION ALL " + query3 + " ORDER BY createdAt DESC ";
//        String query = query1;
        Query nativequery = em
                .createNativeQuery(query, "reviewUnion")
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize());


        for (Map.Entry<String, Object> entry : params.entrySet()) {
            nativequery.setParameter(entry.getKey(), entry.getValue());
        }

        List<ReviewAllDto> resultList = nativequery.getResultList();
//        resultList.stream().map(res -> {
//            if (res.getZonecode() != null) {
//                if (findEstaRequestDto.getSido() == null) {
//
//                }
//                //전체검색
//                if (findEstaRequestDto.getSido() != null) {
//
//                if (findEstaRequestDto.getGungu() == null) {
//                    //전체검색
//                    res.getZonecode().startsWith(findEstaRequestDto.getSido());
//                } else {
//                    res.getZonecode().equals(zoneCode);
//                }
//            }
//        }
//        if (res.getEstablishmentType() != null) {
//            if (findEstaRequestDto.getEstablishmentType().name().equals("all")) {
//                //all
//            } else {
//                res.getEstablishmentType().equals(findEstaRequestDto.getEstablishmentType());
//            }
//        }
//        if (QCompany.company.interestCompany != null) {
//            if (findEstaRequestDto.getInterestCompany().name().equals("all")) {
//
//            } else {
//                QCompany.company.interestCompany.eq(findEstaRequestDto.getInterestCompany());
//            }
//        }
//        return res;
//    }).
//
//    collect(Collectors.toList());

        return resultList;

//        Map<String, String> paramaterMap = new HashMap<String, String>();
//        StringBuilder queryBuilder = new StringBuilder();
//        queryBuilder.append(FIND_ALL_IDS);
////
//        Query jpaQuery = em.createNativeQuery(queryBuilder.toString());
////
//        for (String key : paramaterMap.keySet()) {
//            jpaQuery.setParameter(key, paramaterMap.get(key));
//        }

//        List<ReviewAllDto> result = new  ArrayList<ReviewAllDto>();
//        for(int i = 0; i < jpaQuery.getResultList().size(); i++){ // resultList
//            if(jpaQuery.getResultList().get(i) instanceof Object[]) {
//                Object[] objects = (Object[]) jpaQuery.getResultList().get(i);
//                List<ReviewAllDto> list = new ArrayList<>(); // 49개 짜리 담을 리스트
//                for (int j = 0; j < objects.length; j++) {
//                    ReviewAllDto d = (ReviewAllDto) objects[j]; // 0~49 해당 dto로 캐스팅
//                    list.add(d); // 리스트에 추가
//                }
//                result.add((ReviewAllDto) list); // 49개 짜리 리스트 resultList에 추가
//            }
//        }
//        List<ReviewAllDto> review = new ArrayList<>();
//        List b = jpaQuery.getResultList();
//        review.add((ReviewAllDto) b);
//        List<ReviewAll> list2 = list.stream().map(o -> {
//            return new ReviewAllDto(
//            );
////        }).collect(Collectors.toList());
//    List test = new ArrayList();
//    test.add(jpaQuery.getResultList());
//    List<ReviewAllDto> result= new ArrayList();
//    for(Object object : test){
//        result.add((ReviewAllDto) object);
//    }
//        return  resultList;
//    }

        //em.createQuery(query, RESPONSE_DTO.class)
//        for(Object o : r)
//        {
    }

    public List<ReviewAllDto> findByAllMyReview(UserPrincipal me, Pageable pageable) {
        //FIND_ALL_IDS
//        String query = FIND_ALL_IDS;
        // 기관리뷰
        String query1 = FIND_ONE_IDS;
        String where1 = " AND cr.state= 'A' AND cr.company_id != -1 AND cr.account_id != -1 ";
        Map<String, Object> params = new HashMap<>();
            if(me.getUserId()!=null){
                where1 += " AND cr.account_id = :inter ";
                params.put("inter",me.getId());
            }
        query1 += where1;
        // 인터뷰리뷰
        String query2 = FIND_TWO_IDS;
        String where2 = " AND ir.state= 'A' AND ir.company_id != -1 AND ir.account_id != -1 ";
        if(me.getUserId()!=null){
            where2 += " AND ir.account_id = :inter ";
            params.put("inter",me.getId());
        }
        query2 += where2;
        // 연봉리뷰
        String query3 = FIND_THR_IDS;
        String where3 = " AND yar.state = 'A' AND yar.company_id != -1 AND yar.account_id != -1  ";
        if(me.getUserId()!=null){
            where3 += " AND yar.account_id = :inter ";
            params.put("inter",me.getId());
        }
        query3 += where3;

        String query = query1 + " UNION ALL " + query2 + " UNION ALL " + query3 + " ORDER BY createdAt DESC ";
//        String query = query1;
        Query nativequery = em
                .createNativeQuery(query, "reviewUnion")
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize());


        for (Map.Entry<String, Object> entry : params.entrySet()) {
            nativequery.setParameter(entry.getKey(), entry.getValue());
        }

        List<ReviewAllDto> resultList = nativequery.getResultList();


        return resultList;
    }
}
