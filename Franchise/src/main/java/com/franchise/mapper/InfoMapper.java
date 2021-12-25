package com.franchise.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.franchise.domain.InfoDTO;

@Mapper
public interface InfoMapper {
	
	public List<InfoDTO> selectInfoList(InfoDTO params); // 업종 별 간략 브랜드 정보 조회
	public InfoDTO selectInfoDetail(Long idx); // 브랜드 상세 정보 조회
	public int selectInfoTotalCount(InfoDTO params); // 브랜드 개수 조회
}
