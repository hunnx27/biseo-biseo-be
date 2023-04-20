package com.onz.modules.admin.notice.infra;

import com.onz.modules.admin.notice.domain.Notice;
import com.onz.modules.admin.reviews.infra.ReviewMRepositoryExtension;
import com.onz.modules.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository  extends JpaRepository<Notice, Long>, NoticeRepositoryExtension {
}
