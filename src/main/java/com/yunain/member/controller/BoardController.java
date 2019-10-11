package com.yunain.member.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.yunain.member.dto.BoardDTO;
import com.yunain.member.dto.MemberDTO;
import com.yunain.member.service.MemberSerivce;

@Controller
@RequestMapping("/boards/*")
public class BoardController {
	@Autowired
	private MemberSerivce memberService;

	private ModelAndView mav;

	// 게시판 리스트 출력
	@RequestMapping(value = "/BoardMain.do", method = RequestMethod.GET)
	public ModelAndView boardList(HttpServletResponse response) throws IOException {
		mav = new ModelAndView();

		mav = memberService.boardList(response);

		return mav;
	}

	// 글쓰기 버튼 눌렀을때
	@RequestMapping(value = "/boardWrite", method = RequestMethod.GET)
	public String goBoardWrite(@ModelAttribute MemberDTO memberDTO) {
		return "BoardWrite";
	}

	@RequestMapping(value = "/boardWriteFile.do", method = RequestMethod.GET)
	public String goboardWriteFile(@ModelAttribute MemberDTO memberDTO) {
		return "./board/BoardWriteFiles";
	}

	// 글쓰기
	@RequestMapping(value = "/boardWrite", method = RequestMethod.POST)
	public ModelAndView boardWrite(@ModelAttribute BoardDTO boardDTO, HttpServletResponse response) throws IOException {
		mav = new ModelAndView();

		// mav.addObject("page", page);
		mav = memberService.boardWrite(boardDTO, response);

		return mav;
	}

	// 게시글 상세조회
	@RequestMapping(value = "/boardContents.do", method = RequestMethod.GET)
	public ModelAndView boardDetail(@RequestParam("boardNum") int boardNum, @RequestParam("page") int page) {
		mav = new ModelAndView();

		mav = memberService.boardHit(boardNum, page);
		// mav = memberService.boardDetail(boardNum);

		return mav;
	}

	// 게시글 수정페이지 출력
	@RequestMapping(value = "/boardModifyView.do", method = RequestMethod.GET)
	public ModelAndView boardModifyView(@RequestParam("boardNum") int boardNum) {
		mav = new ModelAndView();

		mav = memberService.boardModifyView(boardNum);

		return mav;
	}

	// 게시글 수정처리
	@RequestMapping(value = "/boardModify.do", method = RequestMethod.POST)
	public ModelAndView boardModify(@ModelAttribute BoardDTO boardDTO, HttpServletResponse response)
			throws IOException {
		mav = new ModelAndView();

		mav = memberService.boardModify(boardDTO, response);

		return mav;
	}

	// 게시글 삭제
	@RequestMapping(value = "/boardDeleteAjax.do", method = RequestMethod.GET)
	public @ResponseBody String boardDeleteAjax(@RequestParam("boardNumber") int boardNum, HttpServletResponse response)
			throws IOException {
		mav = new ModelAndView();

		String deleteResult = memberService.boardDeleteAjax(boardNum);

		return deleteResult;
	}

	// 게시판 리스트(페이징) 출력
	@RequestMapping(value = "/boardPageMain.do", method = RequestMethod.GET)
	public ModelAndView boardPageList(@RequestParam("page") int page, HttpServletResponse response) throws IOException {
		mav = new ModelAndView();

		if (page == 0)
			page = 1;

		mav = memberService.boardPageList(page);

		return mav;
	}

	// 글쓰기(파일)
	@RequestMapping(value = "/boardWriteFile.do", method = RequestMethod.POST)
	public ModelAndView boardWriteFile(@ModelAttribute BoardDTO boardDTO) throws IOException {
		mav = new ModelAndView();
		MultipartFile boardFile = boardDTO.getBoardFile();
		String fileName = boardFile.getOriginalFilename();
		String savePath = "D:\\dev\\spring\\MemberProject\\src\\main\\webapp\\resources\\uploadFile\\" + fileName;

		if (!boardFile.isEmpty()) {
			boardFile.transferTo(new File(savePath));
		}
		boardDTO.setBoardFileName(fileName);
		mav = memberService.boardWriteFile(boardDTO);

		return mav;
	}

}
