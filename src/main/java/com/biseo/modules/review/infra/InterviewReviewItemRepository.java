package com.biseo.modules.review.infra;

import com.biseo.modules.review.domain.InterviewReviewItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewReviewItemRepository extends JpaRepository<InterviewReviewItem, Long>{

}
