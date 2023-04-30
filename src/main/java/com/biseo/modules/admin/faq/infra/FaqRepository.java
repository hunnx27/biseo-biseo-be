package com.biseo.modules.admin.faq.infra;

import com.biseo.modules.admin.faq.domian.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository  extends JpaRepository<Faq, Long>, FaqRepositoryExtension {
}
