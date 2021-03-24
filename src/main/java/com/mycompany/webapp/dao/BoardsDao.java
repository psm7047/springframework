package com.mycompany.webapp.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycompany.webapp.dto.Board;
import com.mycompany.webapp.dto.Pager;

@Mapper
public interface BoardsDao {
	public List<Board> selectAll();
	public List<Board> selectByPage(Pager pager);
	public int insert(Board board);
	public Board selectByBno(int bno);
	public int update(Board board);
	public int deleteByBno(int bno);
	public int updateBhitcount(int bno);
	public int count();
	
}

/*@Repository
public class BoardsDao {
	
	@Autowired
	private SqlSessionTemplate sst;
	
	public List<Board> selectAll() {
		List<Board> list = sst.selectList("boards.selectAll");
		return list;
	}
	
	public List<Board> selectByPage(Pager pager) {
		List<Board> list = sst.selectList("boards.selectByPage", pager);
		return list;
	}
	
	public int insert(Board board) {
		int rows = sst.insert("boards.insert", board);
		return rows;
	}

	public Board selectByBno(int bno) {
		Board board = sst.selectOne("boards.selectByBno", bno);
		return board;
	}

	public int update(Board board) {
		int rows = sst.update("boards.update",board);
		return rows;
	}
	
	public int deleteByBno(int bno) {
		int rows = sst.delete("boards.deleteByBno", bno);
		return rows;
	}
	public int updateBhitcount(int bno) {
		int rows = sst.update("boards.updateBhitcount",bno);
		return rows;
	}

	public int count() {
		int rows = sst.selectOne("boards.count");
		return rows;
	}
	
}
*/