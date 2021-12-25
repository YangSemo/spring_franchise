package com.franchise.service;

import java.util.List;

import com.franchise.domain.CommentDTO;

public interface CommentService {

	public boolean registerComment(CommentDTO params); // 댓글 등록
	public boolean deleteComment(Long idx); // 댓글 삭제
	public List<CommentDTO> getCommentList(CommentDTO params); // 댓글 리스트 조회

}

