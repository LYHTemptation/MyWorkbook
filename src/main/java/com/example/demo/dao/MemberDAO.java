package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.MemberVO;

@Mapper
@Repository
public interface MemberDAO {
	public MemberVO login(MemberVO memberVO) throws DataAccessException;
	public List<MemberVO> selectAllMember() throws DataAccessException;
	public void insertMember(MemberVO memberVO) throws DataAccessException;
	public void updateMemberPw(MemberVO memberVO) throws DataAccessException;
	public void deleteMember(String id) throws DataAccessException;
}
