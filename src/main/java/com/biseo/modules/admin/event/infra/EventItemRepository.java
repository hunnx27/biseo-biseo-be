package com.biseo.modules.admin.event.infra;

import com.biseo.modules.admin.event.domain.EventItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventItemRepository  extends JpaRepository<EventItem, Long>, EventItemRepositoryExtension{
        EventItem findByEventIdAndAccountId(Long id, Long accountId);
        List<EventItem> findAllByEventId(Long id);
}