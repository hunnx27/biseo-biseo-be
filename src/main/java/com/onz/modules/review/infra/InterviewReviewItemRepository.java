package com.onz.modules.review.infra;

import com.onz.modules.review.domain.InterviewReviewItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewReviewItemRepository extends JpaRepository<InterviewReviewItem, Long>{

}
