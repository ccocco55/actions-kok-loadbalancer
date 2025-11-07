package com.example.kok.repository;

import com.example.kok.dto.RequestInternFileDTO;
import com.example.kok.mapper.RequestInternFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RequestInternFileDAO {
    private final RequestInternFileMapper requestInternFileMapper;

    public void saveRequestFile(RequestInternFileDTO requestInternFileDTO){
        requestInternFileMapper.insertRequestFile(requestInternFileDTO);
    }
}
