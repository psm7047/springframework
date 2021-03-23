package com.mycompany.webapp.controller;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.webapp.dto.Board;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.service.BoardsService;

@Controller
@RequestMapping("/exam04")
public class Exam04Controller {
	
	@Autowired
	private BoardsService boardsService;
	
	@Autowired
	private DataSource dataSource;
	
	@RequestMapping("/content")
	public String content(Model model) {
		Connection conn = null;
		try {
			//커넥션 풀에서 커텍션 객체를 대여해오기
			conn = dataSource.getConnection();
			model.addAttribute("connStatus", "성공");
		}catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("connStatus", "실패");
		}finally {
			try{
				//커넥션 풀로 커넥션 객체를 반납하기
				conn.close();
				} catch(Exception e) {}
		}
		return "exam04/content";
	}
	
	/*@GetMapping("/list")
	public String getBoardList(Model model) {
		List<Board> list = boardsService.getBoardList();
		model.addAttribute("list", list);
		return "exam04/boardlist";
	}*/
	
	@GetMapping("/list")
	public String getBoardList(@RequestParam(defaultValue="1") int pageNo, Model model) {
		int totalRows = boardsService.getTotalRows();
		Pager pager = new Pager(10,5, totalRows, pageNo);
		List<Board> list = boardsService.getBoardList(pager);
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
		return "exam04/boardlist";
	}
	
	@GetMapping("/createForm")
	public String createForm(Model model) {
		return "exam04/createForm";
	}
	
	/*@PostMapping("/create")
	public String saveBoard(String btitle, String bcontent) {
		Board board = new Board();
		board.setBtitle(btitle);
		board.setBcontent(bcontent);
		board.setBwriter("user1");
		
		boardsService.saveBoard(board);
		
		return "redirect:/exam04/list";
	}*/
	
	@PostMapping("/create")
	public String create(Board board) {
		board.setBwriter("user1");
		boardsService.saveBoard(board);
		return "redirect:/exam04/list";
	}
	
	@GetMapping("/read")
	public String read(int bno, Model model) {
		boardsService.addHitcount(bno);
		Board board = boardsService.getBoard(bno);
		model.addAttribute("board", board);
		return "exam04/read";
	}
	
	@GetMapping("/updateForm")
	public String updateForm(int bno, Model model) {
		Board board = boardsService.getBoard(bno);
		model.addAttribute("board", board);
		return "exam04/updateForm";
	}
	
	@PostMapping("/update")
	public String update(Board board) {
		boardsService.updateBoard(board);
		return "redirect:/exam04/list";
	}
	
	@GetMapping("/delete") 
	public String delete(int bno) {
		boardsService.deleteBoards(bno);
		return "redirect:/exam04/list";
	}

}
