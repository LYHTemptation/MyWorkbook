package com.example.demo.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.board.domain.vo.PageBoardVO;
import com.example.demo.dao.BoardDAO;
import com.example.demo.vo.BoardVO;
import com.example.demo.vo.PageVO;
import com.example.demo.vo.PaginationInfo;

@Service
public class BoardService {
	
	@Autowired
	BoardDAO boardDAO;
	
	public long selectIdx() {
		Long idx = boardDAO.selectIdx();
		return idx;
	}
	
	public void registerBoard(BoardVO boardVO) {
		long maxIdx = boardDAO.selectIdx();
		if (boardVO.getIdx() > maxIdx) {
			 boardDAO.insertBoard(boardVO);
		} else {
			 boardDAO.updateBoard(boardVO);
		}

	}

	public BoardVO getBoardDetail(Long idx) {
		return boardDAO.selectBoardDetail(idx);
	}

	public void deleteBoard(Long idx) {

		BoardVO board = boardDAO.selectBoardDetail(idx);

		if (board != null /* && "N".equals(board.getDeleteYn()) */) {
			boardDAO.deleteBoard(idx);
		}

	}

	public List<BoardVO> getBoardList(BoardVO boardVO) {
		List<BoardVO> boardList = Collections.emptyList();

		int boardTotalCount = boardDAO.selectBoardTotalCount(boardVO);

		if (boardTotalCount > 0) {
			boardList = boardDAO.selectBoardList(boardVO);
		}

		return boardList;
	}
}
