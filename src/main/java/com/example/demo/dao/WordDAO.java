package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.PageVO;
import com.example.demo.vo.WordVO;

@Mapper
@Repository
public interface WordDAO {
	
	public void insertWord(WordVO WordVO) throws DataAccessException;
	public List<WordVO> selectWord(WordVO WordVO) throws DataAccessException;
	public List<WordVO> getDetailWordList(String dtype) throws DataAccessException;
	public List<WordVO> userResult(String id) throws DataAccessException;
	public WordVO getDetailWord(WordVO wordVO) throws DataAccessException;
	public WordVO getDetailWord2(WordVO wordVO) throws DataAccessException;
	public WordVO getDetailWord3(WordVO wordVO) throws DataAccessException;
	
	// 단어장 총 갯수
	public int countBoard();

	// 페이징 처리 단어장 조회
	public List<WordVO> selectWordList(PageVO pageVO) throws DataAccessException;;
	
	public void insertSetWord(String id) throws DataAccessException;
	public WordVO selectSetWord(String id) throws DataAccessException;
	public void upeateSetWord(WordVO WordVO) throws DataAccessException;

	public int selectQuantityByPcode(WordVO WordVO) throws DataAccessException;	
	public void updateQuantityByPcode(WordVO WordVO) throws DataAccessException;

	
}

