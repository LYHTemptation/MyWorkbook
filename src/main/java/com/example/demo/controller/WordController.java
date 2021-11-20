package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@RequestMapping("test")
	public String goWord(HttpServletRequest request,Model model,WordVO wordVO) throws Exception {
		WordVO vo = new WordVO();
		String dtype = request.getParameter("dType");
		String w_code = request.getParameter("w_code");
		System.out.println("dtype: "+dtype);
		System.out.println("w_code: "+w_code);
		vo.setdType(dtype);
		List<WordVO> detailWord = wordService.getDetailWordList(dtype);
		WordVO setCode = detailWord.get(0);
		System.out.println("setCode "+setCode);
		if(request.getParameter("type") != null){
			vo.setW_code(Integer.parseInt(w_code));
			vo = wordService.getDetailWord2(vo);
		}else if(w_code!=null) {
			vo.setW_code(Integer.parseInt(w_code));
			vo = wordService.getDetailWord3(vo);
		}else {
			vo.setW_code(setCode.getW_code());
			vo = wordService.getDetailWord(vo);			
		}
		if(vo==null) {
			vo.setW_code(setCode.getW_code());
			vo = wordService.getDetailWord(vo);
		}
		model.addAttribute("detailWord",detailWord);
		model.addAttribute("oneWord",vo);
		return "test";
	}
}
