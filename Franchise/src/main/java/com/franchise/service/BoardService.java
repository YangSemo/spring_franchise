package com.franchise.service;

import java.util.List;

import com.franchise.domain.BoardDTO;

public interface BoardService {

	public boolean registerBoard(BoardDTO params); // 게시글 등록
	public BoardDTO getBoardDetail(Long idx); // 세부게시글 조회
	public boolean deleteBoard(Long idx); // 게시글 삭제
	public List<BoardDTO> getBoardList(BoardDTO params); // 게시글 리스트 조회
	public List<BoardDTO> getBestBoardList(BoardDTO params); // top 10 게시글 조회

}
