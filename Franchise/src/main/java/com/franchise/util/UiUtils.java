package com.franchise.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.franchise.constant.Method;
import com.franchise.paging.Criteria;

public class UiUtils {

	// message: 사용자에게 전달할 메시지
	// redirectUri: 게시글 작성 -> 게시글 등록 완료 -> 게시글 리스트로 리다이렉트
	// method: HTTP 요청 메서드
	// params: 화면(view)으로 전달할 파라미터 => Map 이용(Key, Value 형태)
	// model: 화면으로 파라미터 전달
	public String showMessageWithRedirect(@RequestParam(value = "message", required = false) String message,
										@RequestParam(value = "redirectUri", required = false) String redirectUri,
										@RequestParam(value = "method", required = false) Method method,
										@RequestParam(value = "params", required = false) Map<String, Object> params, Model model) {
		
		model.addAttribute("message", message);
		model.addAttribute("redirectUri", redirectUri);
		model.addAttribute("method", method);
		model.addAttribute("params", params);
		
		return "utils/message-redirect";
	}
	
	// Criteria 클래스의 모든 멤버 변수(이전 페이지 정보)를 Map에 Key, Value 형태 리턴
	// GET 방식이 아닌 POST 방식의 처리에서만 사용
	public Map<String, Object> getPagingParams(Criteria criteria) {

		Map<String, Object> params = new LinkedHashMap<>();
		params.put("currentPageNo", criteria.getCurrentPageNo());
		params.put("recordsPerPage", criteria.getRecordsPerPage());
		params.put("pageSize", criteria.getPageSize());
		params.put("searchType", criteria.getSearchType());
		params.put("searchKeyword", criteria.getSearchKeyword());

		return params;
	}
	
}
