package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.WordService;
import com.example.demo.vo.WordVO;
import com.example.demo.vo.MemberVO;

@Controller
public class WordController {
	
	@Autowired
	WordService WordService;
	
	@RequestMapping("insertBasket")
	@ResponseBody
	public String insertWord(HttpSession session, WordVO wordVO) {
		System.out.println(wordVO);
		
		MemberVO memberVO=(MemberVO) session.getAttribute("memberVO");
		if(memberVO==null) {
			return "로그인부터 하세요";
		}
		
		String id=memberVO.getId();
		wordVO.setId(id);
		
		System.out.println("!!!!"+wordVO.getId());
		try {
			WordService.insertWord(wordVO);
		} catch (Exception e) {			
		e.printStackTrace();
		return "장바구니 넣기 fail";
		}		
		
		return "장바구니 넣기 ok";
		
	}
}
