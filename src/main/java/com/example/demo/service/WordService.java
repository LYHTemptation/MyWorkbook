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

}
