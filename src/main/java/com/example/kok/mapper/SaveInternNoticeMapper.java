package com.example.kok.mapper;

import com.example.kok.dto.SaveInternNoticeDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SaveInternNoticeMapper {
//    공고 저장
    public void insertSaveInt(SaveInternNoticeDTO saveInternNoticeDTO);
//    공고 저장 취소
    public void deleteSaveInt(SaveInternNoticeDTO saveInternNoticeDTO);
//    공고 저장 여부 판별
    public int selectCountSavedIntByIntId(SaveInternNoticeDTO saveInternNoticeDTO);
}
