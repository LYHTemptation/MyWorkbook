package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.WordVO;

@Mapper
@Repository
public interface WordDAO {
	
	public void insertWord(WordVO WordVO) throws DataAccessException;
	public List<WordVO> selectWord(WordVO WordVO) throws DataAccessException;
	public void insertSetWord(String id) throws DataAccessException;
	public WordVO selectSetWord(String id) throws DataAccessException;
	public void upeateSetWord(WordVO WordVO) throws DataAccessException;

	public int selectQuantityByPcode(WordVO WordVO) throws DataAccessException;	
	public void updateQuantityByPcode(WordVO WordVO) throws DataAccessException;

	
}

