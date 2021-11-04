package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.WordService;
import com.example.demo.vo.WordVO;

@Controller
public class WordController {
	
	@Autowired
	WordService WordService;
	
	/*
	 * @RequestMapping("insertBasket")
	 * 
	 * @ResponseBody public String insertWord(HttpSession session, WordVO wordVO) {
	 * System.out.println(wordVO);
	 * 
	 * MemberVO memberVO=(MemberVO) session.getAttribute("memberVO");
	 * if(memberVO==null) { return "로그인부터 하세요"; }
	 * 
	 * String id=memberVO.getId(); wordVO.setId(id);
	 * 
	 * System.out.println("!!!!"+wordVO.getId()); try {
	 * WordService.insertWord(wordVO); } catch (Exception e) { e.printStackTrace();
	 * return "장바구니 넣기 fail"; }
	 * 
	 * return "장바구니 넣기 ok"; }
	 */
	
	@RequestMapping("insertWord")
	@ResponseBody
	public String insertWord(HttpServletRequest request,WordVO wordVO) throws Exception {
		System.out.println(request.getParameter("searchWord"));
		System.out.println(request.getParameter("resultWord"));
		if(request.getParameter("searchWord")==null||request.getParameter("searchWord").equals("")
				||request.getParameter("resultWord")==null||request.getParameter("resultWord").equals("")) {
			return "저장 할 단어를 채워주세요";
		}
			WordService.insertWord(wordVO);
			
			return "저장 완료";
		
	}
}
