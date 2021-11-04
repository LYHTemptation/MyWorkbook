package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.domain.vo.PageBoardVO;
import com.example.demo.service.BoardService;
import com.example.demo.service.MemberService;
import com.example.demo.service.WordService;
import com.example.demo.vo.BoardVO;
import com.example.demo.vo.MemberVO;
import com.example.demo.vo.WordVO;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	WordService wordService;
	
	@RequestMapping("/")
	public String jspCheck(HttpServletRequest request, HttpSession session, ModelMap model, 
			MemberVO memberVO, BoardVO boardVO, @ModelAttribute("pageBoardVO") WordVO wordVO) throws Exception {
		session = request.getSession();
		memberVO = (MemberVO) session.getAttribute("memberVO");
		
		List<BoardVO> articlesList = boardService.getBoardList(boardVO);
		List<WordVO> wordList[] = new List[3];
		for(int i=0;i<3;i++) {
			wordList[i] = wordService.selectWord(wordVO); 
			System.out.println("wordList"+i+wordList[i]);
		}
		
		if(memberVO== null ) {
			model.addAttribute("loginSign", "N");
		}else {
			model.addAttribute("loginSign", "Y");
			model.addAttribute("session", memberVO.getId());
		}
		System.out.println("articlesList"+articlesList);
		model.addAttribute("wordList0", wordList[0]);
		model.addAttribute("wordList1", wordList[1]);
		model.addAttribute("wordList2", wordList[2]);
		model.addAttribute("articlesList", articlesList); 
		return "index";
	}

	@RequestMapping(value="login")
	public String login(HttpServletRequest request, HttpSession session,MemberVO memberVO ) {
		System.out.println(memberVO);
		session = request.getSession();
		JSONObject jo=new JSONObject();
		
		if(memberVO.getId() ==null || memberVO.getId().equals("") || 
				memberVO.getPw()==null || memberVO.getPw().equals("")) {
			jo.put("msg", "id와 pw는 필수입니다");
			return jo.toJSONString();
		}
		
		try {
			MemberVO vo=memberService.login(memberVO);
			if(vo!=null) {
				session.setAttribute("memberVO", memberVO);
				jo.put("id", vo.getId());
				jo.put("pw", vo.getPwd());
				if(jo.size()>0) {
					return "redirect:/";
				}
			}else {
				jo.put("msg", "id와 pw를 확인하세요");
			}
		}catch(DataAccessException e) {
			jo.put("msg", e.getMessage());
		}		
		
		return jo.toJSONString();
	}


	@PostMapping("logout")
	@ResponseBody
	public String logout(HttpSession session) {
		JSONObject jo=new JSONObject();
		
		try {
			session.invalidate();
			
			jo.put("msg", "ok");
		}catch(Exception e) {
			jo.put("msg", e.getMessage());
		}
		
		return jo.toJSONString();
	}
	
	@RequestMapping("memberInsert")
	public String memberInsert(MemberVO memberVO) {
		System.out.println(memberVO);
		
		/*
		 * if(memberVO.getId() ==null || memberVO.getId().equals("") ||
		 * memberVO.getPwd()==null || memberVO.getPwd().equals("") ||
		 * memberVO.getName()==null || memberVO.getName().equals("")) { return "error";
		 * }
		 */
		
		
		try {
			memberService.memberInsert(memberVO);
			return "redirect:/";
		}catch(DataAccessException e) {
			System.out.println(e.getMessage());
			return "error";
		}
	}

		
		

}
