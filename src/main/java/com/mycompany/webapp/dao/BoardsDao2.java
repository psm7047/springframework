package com.mycompany.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycompany.webapp.dto.Board;
import com.mycompany.webapp.dto.Pager;

@Mapper
public interface BoardsDao2 {
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
public class BoardsDao2 {
	
	@Autowired
	private SqlSessionTemplate sst;
	
	public List<Board> selectAll() {
		List<Board> list = sst.selectList("boards2.selectAll");
		return list;
	}
	
	public List<Board> selectByPage(Pager pager) {
		List<Board> list = sst.selectList("boards2.selectByPage", pager);
		return list;
	}
	
	public Board selectByBno(int bno) {
		Board board = sst.selectOne("boards2.selectByBno", bno);
		return board;
	}
	public int update(Board board) {
		int row = sst.update("boards2.update", board);
		return row;
	}
	public int insert(Board board) {
		int rows = sst.insert("boards2.insert", board);
		return rows;
	}
	public int deleteByBno(int bno) {
		int rows = sst.delete("boards2.deleteByBno", bno);
		return rows;
	}
	public int updateBhitcount(int bno) {
		int row = sst.update("boards2.updateBhitcount", bno);
		return row;
	}
	public int count() {
		int rows = sst.selectOne("boards2.count");
		return rows;
	}
	
}*/
