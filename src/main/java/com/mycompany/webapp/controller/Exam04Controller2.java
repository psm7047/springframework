package com.mycompany.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.webapp.dto.Board;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.service.BoardsService2;

@Controller
@RequestMapping("/exam04_homework")
public class Exam04Controller2 {
	
	@Autowired
	private BoardsService2 boardsService2;
	
	@GetMapping("/content")
	public String content() {
		return "exam04_homework/content";
	}
	
	@GetMapping("/list")
	public String getList(@RequestParam(defaultValue="1") int pageNo, Model model) {
		int totalRows=boardsService2.getTotalRows();
		Pager pager = new Pager(10,5,totalRows,pageNo);
		List<Board> list = boardsService2.getBoardList(pager);
		model.addAttribute("list",list);
		model.addAttribute("pager",pager);
		return "exam04_homework/boardlist";
	}
	@GetMapping("/createForm")
	public String createForm() {
		return "exam04_homework/createForm";
	}
	@PostMapping("/create")
	public String create(Board board) {
		board.setBwriter("user1");
		boardsService2.saveBoard(board);
		return "redirect:/exam04_homework/list";
	}
	
	@GetMapping("/read")
	public String read(int bno,Model model) {
		boardsService2.addHitcount(bno);
		Board board = boardsService2.getBoard(bno);
		model.addAttribute("board",board);
		return "exam04_homework/read";
	}
	@GetMapping("/updateForm")
	public String updateForm(int bno, Model model) {
		Board board = boardsService2.getBoard(bno);
		model.addAttribute("board",board);
		return "exam04_homework/updateForm";
	}
	@PostMapping("/update")
	public String update(Board board) {
		boardsService2.updateBoard(board);
		return "redirect:/exam04_homework/list";
	}
	@GetMapping("/delete")
	public String delete(int bno) {
		boardsService2.deleteBoards(bno);
		return "redirect:/exam04_homework/list";
	}

}
