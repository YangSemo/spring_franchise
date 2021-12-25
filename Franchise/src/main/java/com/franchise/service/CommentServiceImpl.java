package com.franchise.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.franchise.domain.CommentDTO;
import com.franchise.mapper.CommentMapper;

//비즈니스 로직 수행 클래스
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentMapper;
	
	// 댓글 번호(idx)가 파라미터에 포함되어 있지 않으면 insert 실행
	// 포함되어 있지 않으면 update 수행
	@Override
	public boolean registerComment(CommentDTO params) {
		int queryResult=0;
		
		if(params.getIdx()==null) {
			System.out.println("insertComment 함수 실행 전");
			queryResult = commentMapper.insertComment(params);
			System.out.println("insertComment 함수 실행 후");
		} else {
			queryResult=commentMapper.updateComment(params);
		}
		
		return (queryResult==1) ? true : false;
	}
	
	// 댓글의 상세 정보를 조회해서 정상적으로 사용 중인 댓글의 경우에만 삭제
	@Override
	public boolean deleteComment(Long idx) {
		int queryResult = 0;
		
		CommentDTO comment = commentMapper.selectCommentDetail(idx);
		
		if(comment != null && "N".equals(comment.getDeleteYn())) {
			queryResult = commentMapper.deleteComment(idx);
		}
		
		return (queryResult==1) ? true : false;
	}
	
	// 특정 게시글에 포함된 댓글이 1개 이상이면 댓글 목록 반환
	@Override
	public List<CommentDTO> getCommentList(CommentDTO params) {
		List<CommentDTO> commentList = Collections.emptyList();
		
		int commentTotalCount = commentMapper.selectCommentTotalCount(params);
		if (commentTotalCount > 0) {
			commentList=commentMapper.selectCommentList(params);
			System.out.println(commentList);
		}
		
		return commentList;
	}
	
}
