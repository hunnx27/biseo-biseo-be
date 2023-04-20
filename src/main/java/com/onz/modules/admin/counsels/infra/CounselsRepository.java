package com.onz.modules.admin.counsels.infra;

import com.onz.modules.admin.counsels.web.dto.CounselsDetailResponseDto;
import com.onz.modules.counsel.domain.Counsel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CounselsRepository extends JpaRepository<Counsel, Long>, CounselsRepositoryExtension {
    Optional<Counsel> findById(Long id);
}
