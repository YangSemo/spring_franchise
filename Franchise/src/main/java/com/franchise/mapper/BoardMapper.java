package com.franchise.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.franchise.domain.BoardDTO;

@Mapper // DB와 통신하여 SQL 실행
public interface BoardMapper {
	
	// int 타입=> sql 실행 정상작동하는지 확인하는 작업
	public int insertBoard(BoardDTO params); // 게시글 생성
	public BoardDTO selectBoardDetail(Long idx); // 게시글 조회
	public int updateViewCnt(Long viewCnt); // 게시글 조회수 증가
	public int updateBoard(BoardDTO params); // 게시글 수정
	public int deleteBoard(Long idx); // 게시글 삭제
	public List<BoardDTO> selectBoardList(BoardDTO params); // 게시글 목록 조회
	public List<BoardDTO> selectBestBoard(BoardDTO params); // 게시글 목록 조회
	public int selectBoardTotalCount(BoardDTO params); // 게시글 개수 조회
	
}