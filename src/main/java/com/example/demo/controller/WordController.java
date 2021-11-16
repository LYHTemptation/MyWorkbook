package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.WordService;
import com.example.demo.vo.MemberVO;
import com.example.demo.vo.PageVO;
import com.example.demo.vo.WordVO;

@Controller
public class WordController {
	
	@Autowired
	WordService wordService;
	
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
		wordService.insertWord(wordVO);
			
			return "저장 완료";
	}
	
	@RequestMapping("setWord")
	@ResponseBody
	public String setWord(HttpSession session, WordVO wordVO) throws Exception {
		MemberVO memberVO=(MemberVO) session.getAttribute("memberVO");
		wordService.insertSetWord(memberVO.getId()); 
		return "";
	}
	
	@RequestMapping("wordListLoad")
	public String wordListLoad( Model model,PageVO pageVO
			, @RequestParam(value="nowPage", required=false)String nowPage
			, @RequestParam(value="cntPerPage", required=false)String cntPerPage) throws Exception {
		System.out.println(nowPage);
		System.out.println(cntPerPage);
		
		int total = wordService.countBoard();
		if (nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "5";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) { 
			cntPerPage = "5";
		}
		pageVO = new PageVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		model.addAttribute("paging", pageVO);
		List<WordVO> wordLista = wordService.selectWordList(pageVO);
		System.out.println(wordLista);
		model.addAttribute("wordList", wordService.selectWordList(pageVO));
		return "";
	}
}
