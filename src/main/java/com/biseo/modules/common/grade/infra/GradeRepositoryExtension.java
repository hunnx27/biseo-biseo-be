package com.biseo.modules.common.grade.infra;

import com.biseo.modules.account.domain.Account;
import com.biseo.modules.common.grade.domain.Grade;

public interface GradeRepositoryExtension {
   Grade pointCheck(Account account);
}
