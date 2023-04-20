package com.onz.modules.common.pointHistory.infra;


import com.onz.modules.common.pointHistory.domain.PointHistory;
import com.onz.modules.common.pointHistory.web.dto.request.PointHistorySearchRequest;
import com.onz.modules.common.pointHistory.web.dto.request.PointHistoryUpdateRequest;
import org.springframework.data.domain.PageImpl;

public interface PointHistoryRepositoryExtension {

    PageImpl<PointHistory> list(PointHistorySearchRequest pointSearchRequest);

}
