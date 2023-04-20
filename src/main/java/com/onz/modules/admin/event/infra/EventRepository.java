package com.onz.modules.admin.event.infra;

import com.onz.modules.admin.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository  extends JpaRepository<Event, Long>, EventRepositoryExtension{
}
