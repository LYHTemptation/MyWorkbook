package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.service.BoardService;
import com.example.demo.vo.BoardVO;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping("register")
	public String register(HttpServletRequest request, BoardVO boardVO,ModelMap model) {
		System.out.println(boardVO);
		if(boardVO.getIdx()==null) {
			System.out.println("insert");
			Long IDX = boardService.selectIdx();
			boardVO.setIdx(IDX+1);
			boardService.registerBoard(boardVO);
			return "redirect:/";
		}else if(request.getParameter("sign").equals("update")) {
			System.out.println("update() 출력");
			boardService.registerBoard(boardVO);
		}else if(request.getParameter("sign").equals("delete")) {
			System.out.println("delete() 출력");
			long idx = Long.parseLong(request.getParameter("idx"));
			boardService.deleteBoard(idx);
		}else if(request.getParameter("sign").equals("boardDetail")) {
			try {
				long idx = Long.parseLong(request.getParameter("idx"));
				boardVO = boardService.getBoardDetail(idx);
				System.out.println(boardVO);
				model.addAttribute("boardVO", boardVO);
				return "my_basic_elements";
			}catch(Exception e){
				return "redirect:/";
			}
		}
		return "redirect:/";
		}
	
}
	
