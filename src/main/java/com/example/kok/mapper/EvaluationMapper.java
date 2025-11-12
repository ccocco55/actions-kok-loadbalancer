package com.example.kok.mapper;

import com.example.kok.dto.EvaluationDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EvaluationMapper {
    //    insert
    public void insertEvaluation(EvaluationDTO evaluation);
    //    개수 select
    public int selectCount(Long id);
}
