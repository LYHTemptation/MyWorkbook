package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.WordDAO;
import com.example.demo.vo.PageVO;
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
	
	public List<WordVO> selectWordList(PageVO pageVO) throws Exception{
		List<WordVO> wordList = WordDAO.selectWordList(pageVO);
		return wordList;		
	}
	
	public int countBoard() {
		return WordDAO.countBoard();
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
	
	public List<WordVO> getDetailWordList(String dtype) throws Exception{
		List<WordVO> detailWord = WordDAO.getDetailWordList(dtype);
		return detailWord;
	}
	
	public List<WordVO> userResult(String id) throws Exception{
		List<WordVO> userResult = WordDAO.userResult(id);
		return userResult;
	}
	
	public WordVO getDetailWord(WordVO wordVO) throws Exception{
		WordVO getDetailWord = WordDAO.getDetailWord(wordVO);
		return getDetailWord;
	}
	
	public WordVO getDetailWord2(WordVO wordVO) throws Exception{
		WordVO getDetailWord = WordDAO.getDetailWord2(wordVO);
		return getDetailWord;
	}

	public WordVO getDetailWord3(WordVO wordVO) throws Exception{
		WordVO getDetailWord = WordDAO.getDetailWord3(wordVO);
		return getDetailWord;
	}
}
