package com.example.kok.service;

import com.example.kok.dto.EvaluationDTO;
import com.example.kok.repository.EvaluationDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationServiceImpl implements EvaluationService {
    private final EvaluationDAO evaluationDAO;
    @Override
    public void review(EvaluationDTO evaluation) {
        evaluationDAO.insertEvaluation(evaluation);
    }

    @Override
    public boolean isReviewed(Long id) {
        return (evaluationDAO.selectCount(id)>0);
    }

    @Override
    public boolean isReviewedThis(Long memberId, Long requestExperienceId) {
        return (evaluationDAO.selectCountThis(memberId, requestExperienceId)>0);
    }
}
