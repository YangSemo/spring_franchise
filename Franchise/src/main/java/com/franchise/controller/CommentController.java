package com.franchise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.franchise.domain.CommentDTO;
import com.franchise.service.CommentService;
import com.google.gson.JsonObject;

//화면이 아닌 리터 타입에 해당하는 데이터 자체를 리턴
@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	// 새로운 댓글 등록, 댓글 테이블의 PK 인 댓글 번호 idx 에 해당하는 댓글 수정
	@RequestMapping(value = { "/comments", "/comments/{idx}" }, method = { RequestMethod.POST, RequestMethod.PATCH })
	public JsonObject registerComment(@PathVariable(value = "idx", required = false) Long idx, @RequestBody final CommentDTO params) {
		JsonObject jsonObj = new JsonObject();

		try {
			boolean isRegistered = commentService.registerComment(params);
			jsonObj.addProperty("result", isRegistered);

		} catch (DataAccessException e) {
			jsonObj.addProperty("message", "데이터베이스 처리 과정에 문제가 발생하였습니다.");

		} catch (Exception e) {
			jsonObj.addProperty("message", "시스템에 문제가 발생하였습니다.");
		}

		return jsonObj;
	}
	
	// PathVariable: Rest 방식에서 리소스를 표현할 때 사용
	// ModelAttribute: 파라미터로 전달받은 객체를 자동으로 화면(뷰)로 전달, 특정 게시글에 등록된 댓글 조회, 추후 댓글 목록의 페이징 처리에 활용
	@GetMapping(value="/comments/{boardIdx}")
	public List<CommentDTO> getCommentList(@PathVariable("boardIdx") Long boardIdx, @ModelAttribute("params") CommentDTO params) {
	
		List<CommentDTO> commentList = commentService.getCommentList(params);
		return commentList;
		
	}
	
	// 댓글 삭제
   // 실제 댓글 삭제하지 않지만, URI 구분을 위해 DeleteMapping 사용
   @DeleteMapping(value="comments/{idx}")
   public JsonObject deleteComment(@PathVariable("idx") final Long idx) {
      JsonObject jsonObj = new JsonObject();
      
      try {
         boolean isDeleted = commentService.deleteComment(idx);
         jsonObj.addProperty("result", isDeleted);
         
      } catch (DataAccessException e) {
         jsonObj.addProperty("message", "데이터베이스 처리 과정에 문제가 발생하였습니다.");

      } catch (Exception e) {
         jsonObj.addProperty("message", "시스템에 문제가 발생하였습니다.");
      }

      return jsonObj;
      
   }
}
