package com.example.kok.mapper;

import com.example.kok.dto.RequestInternDTO;
import com.example.kok.dto.RequestInternFileDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RequestInternFileMapper {
//    지원서 파일 insert
    public void insertRequestFile(RequestInternFileDTO RequestInternFileDTO);
}
