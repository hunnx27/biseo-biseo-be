package com.onz.modules.follower.infra;

import com.onz.modules.common.address.infra.AddressRepository;
import com.onz.modules.company.domain.Company;
import com.onz.modules.company.infra.CompanyRepositoryExtension;
import com.onz.modules.follower.domain.Follower;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.annotations.Fetch;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class FollowerRepositoryExtensionImpl extends QuerydslRepositorySupport implements
        FollowerRepositoryExtension {

    private final JPAQueryFactory jpaQueryFactory;
    private AddressRepository addressRepository;

    public FollowerRepositoryExtensionImpl(JPAQueryFactory jpaQueryFactory) {
        super(Follower.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

}
