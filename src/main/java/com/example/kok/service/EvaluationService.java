package com.example.kok.service;

import com.example.kok.dto.EvaluationDTO;

public interface EvaluationService {

    public void review(EvaluationDTO evaluation);

    public boolean isReviewed(Long id);

    public boolean isReviewedThis(Long memberId, Long requestExperienceId);
}
