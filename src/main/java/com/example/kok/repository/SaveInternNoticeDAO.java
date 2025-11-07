package com.example.kok.repository;

import com.example.kok.dto.SaveInternNoticeDTO;
import com.example.kok.mapper.SaveInternNoticeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SaveInternNoticeDAO {
    private final SaveInternNoticeMapper saveInternNoticeMapper;

//    공고 저장
    public void saveInt(SaveInternNoticeDTO saveInternNoticeDTO) {
        saveInternNoticeMapper.insertSaveInt(saveInternNoticeDTO);
    }
//    공고 저장 취소
    public void deleteInt(SaveInternNoticeDTO saveInternNoticeDTO) {
        saveInternNoticeMapper.deleteSaveInt(saveInternNoticeDTO);
    }

//    공고 저장 여부 판별
    public boolean idSavedInt(SaveInternNoticeDTO saveInternNoticeDTO) {
        int bool=saveInternNoticeMapper.selectCountSavedIntByIntId(saveInternNoticeDTO);
        if(bool>0){
            return true;
        } else {
            return false;
        }
    }
}
