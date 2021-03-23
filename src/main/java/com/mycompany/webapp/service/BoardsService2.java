package com.mycompany.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.BoardsDao2;
import com.mycompany.webapp.dto.Board;
import com.mycompany.webapp.dto.Pager;

@Service
public class BoardsService2 {
	
	@Autowired
	private BoardsDao2 boardsDao2;
	
	public List<Board> getBoardList() {
		List<Board> list = boardsDao2.selectAll();
		return list;
	}
	
	public List<Board> getBoardList(Pager pager) {
		List<Board> list = boardsDao2.selectByPage(pager);
		return list;
	}
	
	public Board getBoard(int bno) {
		Board board = boardsDao2.selectByBno(bno);
		return board;
	}
	public void updateBoard(Board board) {
		boardsDao2.update(board);
	}
	public void saveBoard(Board board) {
		boardsDao2.insert(board);
	}
	public void deleteBoards(int bno) {
		boardsDao2.deleteByBno(bno);
	}
	public void addHitcount(int bno) {
		boardsDao2.updateBhitcount(bno);
	}
	public int getTotalRows() {
		int rows = boardsDao2.count();
		return rows;
	}

}
