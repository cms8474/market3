package kr.co.team3.admin_service;

import kr.co.team3.admin_dto.CouponIssueDTO;
import kr.co.team3.admin_dto.PageRequestDTO;
import kr.co.team3.admin_dto.PageResponseDTO;
import kr.co.team3.admin_mapper.CouponIssueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CouponIssueService {
    private final CouponIssueMapper couponIssueMapper;

    public PageResponseDTO<CouponIssueDTO> getIssuePage(PageRequestDTO req) {
        int total = couponIssueMapper.countIssueList(req);
        List<CouponIssueDTO> list = couponIssueMapper.selectIssueList(req);
        return new PageResponseDTO<>(req, list, total);
    }

    public CouponIssueDTO findByPoNo(String poNo) {
        return couponIssueMapper.selectIssueByPoNo(poNo); // Mapper/XML에 SELECT 하나 추가
    }

}