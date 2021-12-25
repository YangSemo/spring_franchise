package com.franchise.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franchise.domain.InfoDTO;
import com.franchise.mapper.InfoMapper;
import com.franchise.paging.PaginationInfo;

@Service
public class InfoServiceImpl implements InfoService {
	
	@Autowired
	private InfoMapper infoMapper;
	
	// 브랜드 상세 조회
	@Override
	public InfoDTO getInfoDetail(long id) {
		return infoMapper.selectInfoDetail(id);
	}
	
	// 업종 별 브랜드 리스트 조회
	@Override
	public List<InfoDTO> getInfoList(InfoDTO params){
		List<InfoDTO> infoList = Collections.emptyList();
		
		// 파라미터 확인
		System.out.println("infoDTO params: " +params);
		
		// 브랜드 전체 row 수
		int infoTotalCount = infoMapper.selectInfoTotalCount(params);
				
		PaginationInfo paginationInfo = new PaginationInfo(params);
		paginationInfo.setTotalRecordCount(infoTotalCount);
		
		// 페이징 정보 params에 등록
		params.setPaginationInfo(paginationInfo);
		
		
		if (infoTotalCount > 0) {
			infoList = infoMapper.selectInfoList(params);
		}
		
		return infoList;
	}
}
