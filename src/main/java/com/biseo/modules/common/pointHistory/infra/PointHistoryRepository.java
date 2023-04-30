package com.biseo.modules.common.pointHistory.infra;


import com.biseo.modules.common.pointHistory.domain.PointHistory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long>,
        PointHistoryRepositoryExtension {

    PageImpl<PointHistory> findByAccountId(Long accountId, Pageable pageable);

}
