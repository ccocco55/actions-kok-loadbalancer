package com.example.kok.mapper;

import com.example.kok.dto.SaveExperienceNoticeDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SaveExperienceNoticeMapper {
//    공고 저장
    public void insertSaveExp(SaveExperienceNoticeDTO saveExperienceNoticeDTO);
//    공고 저장 취소
    public void deleteSaveExp(SaveExperienceNoticeDTO saveExperienceNoticeDTO);
//    공고 저장 여부 판별
    public int selectCountSavedExpByExpId(SaveExperienceNoticeDTO saveExperienceNoticeDTO);
}
