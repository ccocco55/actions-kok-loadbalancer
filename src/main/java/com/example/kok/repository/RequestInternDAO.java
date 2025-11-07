package com.example.kok.repository;

import com.example.kok.dto.RequestInternDTO;
import com.example.kok.mapper.RequestInternMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RequestInternDAO {
    private final RequestInternMapper requestInternMapper;

    //    지원서 추가
    public void applyForIntern(RequestInternDTO requestInternDTO){
        requestInternMapper.insertRequest(requestInternDTO);
    }

    //    회원별 지원서 조회
    public List<RequestInternDTO> selectAllInternById(Long id){
        return requestInternMapper.selectRequestById(id);
    }

    //
    public int selectRequestCountById(Long id){
        return requestInternMapper.selectRequestCountById(id);
    }

    //    인턴 지원 내역 조회
    public List<RequestInternDTO> selectAllInternByUserId(Long id,Long internId){
        return  requestInternMapper.selectRequestInternByUserId(id,internId);
    }

    //    지원 여부 판별
    public boolean isRequested(RequestInternDTO requestInternDTO){
        if(requestInternMapper.countRequest(requestInternDTO)>0){
            return true;
        }
//        System.out.println(requestInternMapper.countRequest(requestInternDTO));
        return false;
    }
}
