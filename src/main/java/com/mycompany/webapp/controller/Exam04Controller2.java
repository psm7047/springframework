package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	@GetMapping("/createFormWithAttach")
	public String createFormWithAttach() {
		return "exam04/createFormWithAttach";
	}
	@PostMapping("/createWithAttach")
	public String createWithAttach(Board board) {
		MultipartFile battach = board.getBattach();
		if(!battach.isEmpty()) {
			board.setBattachoname(battach.getOriginalFilename());
			board.setBattachtype(battach.getContentType());
			String saveName = new Date().getTime() + "-" + board.getBattachoname();
			board.setBattachsname(saveName);
			File file = new File("D:/Team4Projects/uploadfiles/" + board.getBattachsname());
			try {
				battach.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/*logger.info(board.getBtitle());
		logger.info(board.getBcontent());
		logger.info(board.getBattachoname());
		logger.info(board.getBattachtype());
		logger.info(board.getBattachsname());*/
		
		board.setBwriter("user1");
		boardsService2.saveBoard(board);
		
		return "redirect:/exam04/list";
	}
	@GetMapping("/downloadAttach")
	   public void downloadAttach(int bno, HttpServletResponse response) {
	      try {
	         Board board = boardsService2.getBoard(bno);
	         //응답 HTTP 헤더에 저장될 바디의 타입
	         response.setContentType(board.getBattachtype());
	         
	         //응답 HTTP 헤더에 다운로드할 수 있도록 파일 이름을 지정
	         String originalName = board.getBattachoname();
	         //한글 파일일 경우, 깨짐 현상을 방지
	         originalName = new String(originalName.getBytes("UTF-8"),"ISO-8859-1");
	         response.setHeader("Content-Disposition", "attachment; filename=\"" + originalName +"\";");
	         
	         //응답 HTTP 바디로 이미지 데이터를 출력
	         InputStream is = new FileInputStream("D:/Team4Projects/uploadfiles/" + board.getBattachsname());
	         OutputStream os = response.getOutputStream();
	         FileCopyUtils.copy(is, os);
	         os.flush();
	         is.close();
	         os.close();
	         
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	   }

}
