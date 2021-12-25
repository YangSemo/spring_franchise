package com.franchise.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.franchise.domain.CommentDTO;

//DB와 통신하는 인터페이스임을 선언
@Mapper
public interface CommentMapper {

	// 댓글을 삽입하는 insert 쿼리 호출
	public int insertComment(CommentDTO params);
	// 파라미터로 전달받은 댓글 번호(idx)에 해당하는 댓글의 상세 내용을 조회
	public CommentDTO selectCommentDetail(Long idx);
	// 댓글 수정하는 update 쿼리 호출
	public int updateComment(CommentDTO params);
	// 댓글을 삭제하는 메서드
	// delete 쿼리 X, update 쿼리 호출하여 delete_yn 컬럼의 상태를 'Y'로 지정
	public int deleteComment(Long idx);
	// 특정 게시글에 포함된 댓글 목록 조회 select
	public List<CommentDTO> selectCommentList(CommentDTO params);
	// 특정 게시글에 포함된 댓글 개수를 조회하는 select
	public int selectCommentTotalCount(CommentDTO params);
	
}

