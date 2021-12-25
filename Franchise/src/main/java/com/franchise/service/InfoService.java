package com.franchise.service;

import java.util.List;

import com.franchise.domain.InfoDTO;


public interface InfoService {

	public InfoDTO getInfoDetail(long id); // 브랜드 세부 정보 조회
	public List<InfoDTO> getInfoList(InfoDTO params); // 브랜드 리스트 조회

}
