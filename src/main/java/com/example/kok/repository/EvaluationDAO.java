package com.example.kok.repository;

import com.example.kok.dto.EvaluationDTO;
import com.example.kok.mapper.EvaluationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
@RequiredArgsConstructor
public class EvaluationDAO {
    private final EvaluationMapper evaluationMapper;

    //    평가 추가
    public void insertEvaluation(EvaluationDTO evaluation) {
        evaluationMapper.insertEvaluation(evaluation);
    }

    //    평가 개수 세기
    public int selectCount(Long id) {
        return evaluationMapper.selectCount(id);
    }
}
