package com.onz.modules.counsel.infra.counselComment;


import com.onz.modules.counsel.domain.CounselComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounselCommentRepository extends JpaRepository<CounselComment, Long>,
        CounselCommentRepositoryExtension {

//    List<Counsel> findByParentCounselId(Long parentCounselId);
//    @Query("select c from Counsel c where c.qnaGubn='A' and c.parentCounsel.id = :parentCounselId")
//    List<Counsel> findByParentCounselId2(@Param("parentCounselId") Long parentCounselId);
}
