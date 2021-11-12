package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.WordDAO;
import com.example.demo.vo.WordVO;

@Service
public class WordService {
	
	@Autowired
	WordDAO WordDAO;
	
	public void insertWord(WordVO WordVO) throws Exception{
				WordDAO.insertWord(WordVO);
	}

	public List<WordVO> selectWord(WordVO wordVO) throws Exception{
		List<WordVO> wordList = WordDAO.selectWord(wordVO);
		return wordList;
	}
	
	public List<WordVO> selectWordList(WordVO wordVO) throws Exception{
		List<WordVO> wordList = WordDAO.selectWordList(wordVO);
		return wordList;		
	}
	
	public void insertSetWord(String id) throws Exception{
		WordVO wordVO = WordDAO.selectSetWord(id);
		
		if(wordVO!=null) {
			wordVO.setSet_number(wordVO.getSet_number()+1);
			wordVO.setId(wordVO.getId());
			System.out.println(wordVO.getSet_number());
			
			WordDAO.upeateSetWord(wordVO);
		}else {
			WordDAO.insertSetWord(id);
		}
	}

}
