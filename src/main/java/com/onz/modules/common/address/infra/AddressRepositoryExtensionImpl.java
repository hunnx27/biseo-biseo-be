package com.onz.modules.common.address.infra;

import com.onz.modules.common.address.domain.Address;
import com.onz.modules.common.address.domain.QAddress;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class AddressRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        AddressRepositoryExtension {

    private final JPAQueryFactory qf;
    private final EntityManager em;

    public AddressRepositoryExtensionImpl(JPAQueryFactory qf, EntityManager em) {
        super(Address.class);
        this.qf = qf;
        this.em = em;
    }


    @Override
    public List<Address> findByAddressGroupBySidoCide() {
        // QueryDSL
        QAddress address = new QAddress("address");

        // 조건생성
        BooleanBuilder where = new BooleanBuilder();

        // 쿼리생성
        List<Address> rList = qf.selectFrom(address)
                                .where(where)
                                .groupBy(address.sidoCode)
                                .orderBy(address.sidoCode.asc())
                                .fetch();
        return rList;
    }


}
