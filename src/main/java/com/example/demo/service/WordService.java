package com.example.demo.service;

import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.WordDAO;
import com.example.demo.vo.WordVO;

@Service
public class WordService {
	
	@Autowired
	WordDAO WordDAO;
	
	public void insertWord(WordVO WordVO) throws Exception{
		try {
			int i=WordDAO.selectQuantityByPcode(WordVO);
			if(i==0) {
				WordDAO.insertWord(WordVO);
			}else {
				WordVO.setQuantity(++i);
				WordDAO.updateQuantityByPcode(WordVO);
			}
		}catch(BindingException e) {
			System.out.println(e.getMessage());
			WordDAO.insertWord(WordVO);
		}
		
	}


}
