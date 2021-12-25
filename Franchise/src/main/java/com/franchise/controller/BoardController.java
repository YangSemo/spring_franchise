package com.franchise.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.franchise.constant.Method;
import com.franchise.domain.BoardDTO;
import com.franchise.service.BoardService;
import com.franchise.util.UiUtils;

//사용자의 요청/응답을 처리하는 컨트롤러
@Controller
public class BoardController extends UiUtils {

	@Autowired
	private BoardService boardService;
	
	// Get 방식을 사용하여 value 속성에 URI 값 매핑
	// 절대 경로를 설정하였기 때문에 write.do를 해야됨
	// return과 연결
	@GetMapping(value = "/board/write.do")
	// Model 인터페이스틑 데이터를 뷰로 전달
	public String openBoardWrite(@ModelAttribute("params") BoardDTO params, @RequestParam(value = "idx", required = false) Long idx, Model model) {
		if (idx == null) {
			model.addAttribute("board", new BoardDTO());
		} else {
			BoardDTO board = boardService.getBoardDetail(idx);
			if (board == null || "Y".equals(board.getDeleteYn())) {
				return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글입니다.", "/board/list.do", Method.GET, null, model);
			}
			model.addAttribute("board", board);
		}
		// .html로 자동 연결
		return "board/write";
	}
	
	// write.html에 저장하기 버튼 클릭시 수행
	@PostMapping(value="/board/register.do")
	public String registerBoard(@ModelAttribute("params") final BoardDTO params, Model model) {
		Map<String, Object> pagingParams = getPagingParams(params);
		try {
			boolean isRegisterd = boardService.registerBoard(params);
			if(isRegisterd == false) {
				// 게시글 등록 실패 메시지글 전달
				return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/board/list.do", Method.GET, pagingParams, model);
			}
		} catch(DataAccessException e) {
			// 데이터베이스 처리 과정에 문제가 발생했다는 메시지 전달
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/board/list.do", Method.GET, pagingParams, model);
		} catch (Exception e) {
			// 시스템에 문제가 발생하였다는 메시지 전달
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/board/list.do", Method.GET, pagingParams, model);

		}
		
		System.out.println("BoardDTO params: "+ params);
		
		return 	showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/board/list.do", Method.GET, pagingParams, model);

	}
	
	// 게시글 리스트 조회
	// Get 방식 HTTP 요청 메서드
	@GetMapping(value="/board/list.do")
	// ModelAttribute: 파라미터로 전달받은 객체를 자동으로 뷰까지 전달
	public String openBoardList(@ModelAttribute("params") BoardDTO params,Model model) {
		List<BoardDTO> boardList = boardService.getBoardList(params);
		
		// Model: 컨트롤러에서 view로 데이터 전달
		model.addAttribute("boardList",boardList);
		
		return "board/list";
	}
	
	// 베스트 게시글(조회수 기준) 리스트 조회 => 상위 10개
	// Get 방식 HTTP 요청 메서드
	@GetMapping(value="/board/list/best.do")
	// ModelAttribute: 파라미터로 전달받은 객체를 자동으로 뷰까지 전달
	public String openBestBoardList(@ModelAttribute("params") BoardDTO params,Model model) {
		List<BoardDTO> boardList = boardService.getBestBoardList(params);
		
		// 베스트 글 리스트 조회 시 pagination 조정
		String best = "best";
		
		// Model: 컨트롤러에서 view로 데이터 전달
		model.addAttribute("boardList",boardList);
		model.addAttribute("best",best); // 베스트 글 호출 시 pagination 조정
		
		return "board/list";
	}
	
	// 세부 게시글 보기
	@GetMapping(value="/board/view.do")
	// required=false => 없는 idx 조회 시 오류나는 부분 직접 코딩함
	public String openBoardDetail(@ModelAttribute("params") BoardDTO params, @RequestParam(value = "idx", required=false) Long idx, Model model) {
		if(idx==null) {
			// 올바르지 않은 접근이라는 메시지를 전달하고, 게시글 리시트로 리다이렉트
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/board/list.do", Method.GET, null, model);
		}
		
		BoardDTO board = boardService.getBoardDetail(idx);
		if (board==null || "Y".equals(board.getDeleteYn())) {
			// 없는 게시글이거나, 이미 삭제된 게시글이라는 메시지 전달하고, 게시글 리스트로 리다이렉트
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/board/list.do", Method.GET, null, model);
		}
		model.addAttribute("board",board);
		
		return "board/view";
	}
	
	// 게시글 삭제
	// ModelAttribute: 이전 페이지 정보가 담겨있는 Criteria 클랫를 상속받는 BoardDTO 커맨드 객체를 화면(View)으로 전달
	   @PostMapping(value="/board/delete.do")
	   public String deleteBoard(@ModelAttribute("params") BoardDTO params, @RequestParam(value = "idx", required = false) Long idx, Model model) {
	      if (idx==null) {
	         // 올바르지 않은 접근이라는 메시지 전달하고, 게시글 리스트로 리다이렉트
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/board/list.do", Method.GET, null, model);

	      }
	      
	      // UiUtils 클래스에 추가한 getPagingParams 호출
	      // pagingParams에 담긴 이전 페이지 정보를 showMessageRedirect 메서드의 인자로 전달
	      Map<String,Object> pagingParams = getPagingParams(params);
	      
	      try {
	         boolean isDeleted = boardService.deleteBoard(idx);
	         if (isDeleted == false) {
	            // 게시글 삭제에 실패하였다는 메시지를 전달
//	            System.out.println(idx+"번 게시글 삭제 실패");
				return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/board/list.do", Method.GET, pagingParams, model);

	         }
	      } catch(DataAccessException e) {
				// 데이터베이스 처리 과정에 문제가 발생했다는 메시지 전달
//				System.out.println("데이터베이스 처리 실패");
				return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/board/list.do", Method.GET, pagingParams, model);
			} catch (Exception e) {
				// 시스템에 문제가 발생하였다는 메시지 전달
//				System.out.println("시스템 에러");
				return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/board/list.do", Method.GET, pagingParams, model);
			}
	      
			return showMessageWithRedirect("게시글 삭제가 완료되었습니다.", "/board/list.do", Method.GET, pagingParams, model);
	   }
	
}
