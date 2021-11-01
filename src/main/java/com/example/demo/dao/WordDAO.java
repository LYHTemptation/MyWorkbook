package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.WordVO;

@Mapper
@Repository
public interface WordDAO {
	
	public void insertWord(WordVO WordVO) throws DataAccessException;
	public int selectQuantityByPcode(WordVO WordVO) throws DataAccessException;	
	public void updateQuantityByPcode(WordVO WordVO) throws DataAccessException;

}
