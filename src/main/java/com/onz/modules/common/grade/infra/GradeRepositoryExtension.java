package com.onz.modules.common.grade.infra;

import com.onz.modules.account.domain.Account;
import com.onz.modules.common.grade.domain.Grade;
import com.onz.modules.common.pointHistory.domain.PointHistory;
import com.onz.modules.common.pointHistory.web.dto.request.PointHistorySearchRequest;
import org.springframework.data.domain.PageImpl;

public interface GradeRepositoryExtension {
   Grade pointCheck(Account account);
}
