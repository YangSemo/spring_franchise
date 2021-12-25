package com.franchise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.franchise.constant.Method;
import com.franchise.domain.InfoDTO;
import com.franchise.service.InfoService;
import com.franchise.util.UiUtils;



@Controller
public class InfoController extends UiUtils {

	@Autowired
	private InfoService infoService;
	
		// Get 방식 HTTP 요청 메서드
		// 브랜드 리스트 보기
		@GetMapping(value="/franchise/infoList.do")
		public String openinfoList(@ModelAttribute("params") InfoDTO params, Model model) {
			List<InfoDTO> infoList = infoService.getInfoList(params);
				
			// model에 브랜드 infoList 등록
			model.addAttribute("infoList",infoList);
						
			return "board/infoList";
		}
		
		// 브랜드 세부 게시글 보기
		@GetMapping(value="/franchise/infoDetail.do")
		public String openinfoDetail(@ModelAttribute("params") InfoDTO params, @RequestParam(value = "idx", required=false) Long idx, Model model) {
			if(idx==null) {
				// 올바르지 않은 접근이라는 메시지를 전달하고, 브랜드 정보 리스트로 리다이렉트
				return showMessageWithRedirect("올바르지 않은 접근입니다.", "/franchise/infoList.do", Method.GET, null, model);
			}
			
			InfoDTO info = infoService.getInfoDetail(idx);
			
			// model에 브랜드 info 등록
			model.addAttribute("info",info);
			
			return "board/infoDetail";
		}
}
