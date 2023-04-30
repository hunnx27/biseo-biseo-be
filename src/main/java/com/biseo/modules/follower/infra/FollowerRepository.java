package com.biseo.modules.follower.infra;

import com.biseo.modules.follower.domain.Follower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowerRepository extends JpaRepository<Follower, Long>,
        FollowerRepositoryExtension {
    Follower findByCompanyIdAndAccount_Id(Long id,Long accountId);
    List<Follower> findByAccount_Id(Long id);
    List<Follower> findByCompany_Id(Long id);
}