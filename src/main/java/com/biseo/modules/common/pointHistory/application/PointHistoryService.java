package com.biseo.modules.common.pointHistory.application;

import com.biseo.modules.account.infra.AccountRepository;
import com.biseo.modules.common.pointHistory.domain.PointHistory;
import com.biseo.modules.common.pointHistory.infra.PointHistoryRepository;
import com.biseo.modules.common.pointHistory.web.dto.request.PointHistorySearchRequest;
import com.biseo.modules.common.pointHistory.web.dto.response.PointHistoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PointHistoryService {

    private final PointHistoryRepository pointRepository;
    private final AccountRepository accountRepository;

    // 이력 조회
    public Page<PointHistoryResponse> list(PointHistorySearchRequest poSearchRequest) {
        Page<PointHistory> pageList = pointRepository.list(poSearchRequest);
        List<PointHistoryResponse> rs = pageList.get().map(PointHistoryResponse::new).collect(Collectors.toList());
        return new PageImpl<>(rs);
    }

//    // 이력 생성
//    public PointHistory create(Account account, PointTable pointTable) {
//
//
//        PointHistory rs = pointRepository.save(new PointHistory(account, pointTable));
//        return rs;
//    }
}
