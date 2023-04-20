package com.onz.modules.admin.event.infra;

import com.onz.modules.admin.event.domain.EventItem;
import com.onz.modules.admin.event.web.dto.EventItemListResponseDto;
import com.onz.modules.follower.domain.Follower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventItemRepository  extends JpaRepository<EventItem, Long>, EventItemRepositoryExtension{
        EventItem findByEventIdAndAccountId(Long id, Long accountId);
        List<EventItem> findAllByEventId(Long id);
}