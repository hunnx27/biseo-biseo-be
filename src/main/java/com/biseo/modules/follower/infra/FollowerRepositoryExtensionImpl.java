package com.biseo.modules.follower.infra;

import com.biseo.modules.common.address.infra.AddressRepository;
import com.biseo.modules.follower.domain.Follower;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
