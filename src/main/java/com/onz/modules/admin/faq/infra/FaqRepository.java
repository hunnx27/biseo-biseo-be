package com.onz.modules.admin.faq.infra;

import com.onz.modules.admin.event.domain.Event;
import com.onz.modules.admin.event.infra.EventRepositoryExtension;
import com.onz.modules.admin.faq.domian.Faq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqRepository  extends JpaRepository<Faq, Long>, FaqRepositoryExtension {
}
