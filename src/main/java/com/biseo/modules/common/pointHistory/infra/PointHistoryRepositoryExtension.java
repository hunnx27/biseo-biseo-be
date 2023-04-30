package com.biseo.modules.common.pointHistory.infra;


import com.biseo.modules.common.pointHistory.domain.PointHistory;
import com.biseo.modules.common.pointHistory.web.dto.request.PointHistorySearchRequest;
import org.springframework.data.domain.PageImpl;

public interface PointHistoryRepositoryExtension {

    PageImpl<PointHistory> list(PointHistorySearchRequest pointSearchRequest);

}
