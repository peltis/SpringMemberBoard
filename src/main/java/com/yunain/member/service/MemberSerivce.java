package com.yunain.member.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.yunain.member.dao.CommentDAO;
import com.yunain.member.dao.MemberDAO;
import com.yunain.member.dto.BoardDTO;
import com.yunain.member.dto.CommentDTO;
import com.yunain.member.dto.MemberDTO;
import com.yunain.member.dto.PageDTO;

@Service
public class MemberSerivce {
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private CommentDAO commentDAO;
	@Autowired
	private HttpSession session;
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	private ModelAndView mav;
	
	public ModelAndView memberJoin(MemberDTO memberDTO) {
		mav = new ModelAndView();
		int joinResult = memberDAO.memberJoin(memberDTO);
		//회원가입 성공시 LoginForm.jsp 이동, 실패시 JoinForm.jsp 띄워주기
		if(joinResult > 0){
			mav.setViewName("./members/LoginForm");
		} else {
			mav.setViewName("./members/JoinForm");
		}
		return mav;
	}
/*
	public ModelAndView memberLogin(MemberDTO memberDTO, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		mav = new ModelAndView();
		MemberDTO loginMember = memberDAO.memberLogin(memberDTO);
		
		//로그인 성공 시 :LoginMain.jsp 이동 & 세션에 id 저장 & 해당 회원 전체정보 저장
		if(loginMember != null) {
			//세션에 아이디 담기
			session.setAttribute("loginId", loginMember.getId() );
			//리퀘스트 영역에 로그인 화면의 전체 정보 저장
			mav.addObject("loginMember", loginMember );
			mav.setViewName("LoginMain");
		} else {
			out.println("<script>");
			out.println("alert('아이디 또는 비밀번호가 틀렸습니다')");
			out.println("history.back(); </script>");
			out.close();
		}
		
		return mav;
	}
*/
		//로긍ㄴ ㅣㅅ큐리티
	public ModelAndView memberLogin(MemberDTO memberDTO, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		mav = new ModelAndView();
		MemberDTO loginMember = memberDAO.memberLogin(memberDTO);
		
		boolean passMatch = passEncoder.matches( memberDTO.getPassword() , loginMember.getPassword() );
		
		//로그인 성공 시 :LoginMain.jsp 이동 & 세션에 id 저장 & 해당 회원 전체정보 저장
		if(passMatch) {
			//세션에 아이디 담기
			session.setAttribute("loginId", loginMember.getId() );
			//리퀘스트 영역에 로그인 화면의 전체 정보 저장
			mav.addObject("loginMember", loginMember );
			mav.setViewName("./members/LoginMain");
		} else {
			out.println("<script>");
			out.println("alert('아이디 또는 비밀번호가 틀렸습니다')");
			out.println("history.back(); </script>");
			out.close();
		}
		
		return mav;
	}
	
	public ModelAndView memberList(MemberDTO memberDTO, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		mav = new ModelAndView();
		
		List<MemberDTO> memberList = memberDAO.memberList();
		
		if(memberList != null) {
			//세션에 아이디 담기
			//session.setAttribute("memberList", memberList );
			//리퀘스트 영역에 로그인 화면의 전체 정보 저장
			mav.addObject("memberList", memberList );
			mav.setViewName("./members/MemberList");
		} else {
			out.println("<script>");
			out.println("alert('멤버목록 조회에 실패하였습니다.')");
			out.println("history.back(); </script>");
			out.close();
		}
		
		return mav;
	}

	public ModelAndView memberDetail(MemberDTO memberDTO, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		mav = new ModelAndView();
		
		MemberDTO memberDetail = memberDAO.memberDetail(memberDTO);
		
		if(memberDetail != null) {
			//세션에 아이디 담기
			//session.setAttribute("memberList", memberList );
			//리퀘스트 영역에 로그인 화면의 전체 정보 저장
			mav.addObject("memberDetail", memberDetail);
			mav.setViewName("./members/MemberDetail");
		} else {
			out.println("<script>");
			out.println("alert('멤버상세보기 조회에 실패하였습니다.')");
			out.println("history.back(); </script>");
			out.close();
		}
		
		return mav;
	}

	public ModelAndView memberModifyView(String id) {
		mav = new ModelAndView();
		MemberDTO modifyView = memberDAO.memberView(id);
		mav.addObject("modifyView", modifyView);
		mav.setViewName("./members/ModifyView");
		
		return mav;
	}

	public ModelAndView memberModify(MemberDTO memberDTO) {
		mav = new ModelAndView();
		int result = memberDAO.memberModify(memberDTO);
		
		if( result > 0) {
			mav.addObject("loginMember", memberDTO);
			mav.setViewName("./members/LoginMain");
		} else {
			mav.setViewName("./members/LoginForm");
		}
		return mav;
	}

	public String idOverlap(String id) {
		String overlapId = memberDAO.idOverlap(id);
		String resultMsg;
		if(overlapId == null) {
			resultMsg = "OK";
		} else {
			resultMsg = "NO";
		}
		return resultMsg;
	}

	public List<MemberDTO> memberListAjax() {
		List<MemberDTO> memberList = memberDAO.memberList();
		return memberList;
	}
	public ModelAndView boardList(HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		mav = new ModelAndView();
		
		List<BoardDTO> boardList = memberDAO.boardList();
		
		if(boardList != null) {
			mav.addObject("boardList", boardList);
			mav.setViewName("./board/boardPageMain");
			BoardDTO dto = new BoardDTO();
		} else {
			out.println("<script>");
			out.println("alert('게시판 목록 출력에 실패하였습니다.')");
			out.println("history.back(); </script>");
			out.close();
		}
		
		return mav;
	}
	
	public ModelAndView boardWrite(BoardDTO boardDTO, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		mav = new ModelAndView();
		int boardWriteResult = memberDAO.boardWrite(boardDTO);
		
		if(boardWriteResult > 0){
			mav.setViewName("LoginMain");
		} else {
			out.println("<script>");
			out.println("alert('글쓰기 실패')");
			out.println("history.back(); </script>");
			out.close();
		}
		return mav;
	}
	/*public ModelAndView boardDetail(int boardNum) {
		mav = new ModelAndView();
		BoardDTO boardDetail = memberDAO.boardDetail(boardNum);
		
		if(boardDetail != null){
			mav.addObject("boardDetail", boardDetail);
			mav.setViewName("boardDatail");
		} else {
			mav.setViewName("LoginMain");
		}
		return mav;
	}*/
	public ModelAndView boardHit(int boardNum, int page) {
		mav = new ModelAndView();
		BoardDTO boardDetail;
		memberDAO.boardHit(boardNum);
		boardDetail = memberDAO.boardDetail(boardNum);
		
		mav.addObject("boardDetail", boardDetail);
		mav.addObject("page", page);
		mav.setViewName("./board/boardDatail");
		
		List<CommentDTO> commentList = commentDAO.commentList(boardNum);
		mav.addObject("commentList", commentList);
		
		return mav;
	}
	
	public ModelAndView boardModifyView(int boardNum) {
		BoardDTO boardDetail = memberDAO.boardDetail(boardNum);
		if(boardDetail != null) {
			mav.addObject("boardDetail", boardDetail);
			mav.setViewName("./board/BoardModifyView");
		}
		else {
			mav.setViewName("LoginMain");
		}
		return mav;
	}
	
	
	public ModelAndView boardModify(BoardDTO boardDTO, HttpServletResponse response) throws IOException {
		mav = new ModelAndView();
		System.out.println( "수정게시판넘버::::"+boardDTO.getBoardNum() );
		int result = memberDAO.boardModify(boardDTO);
		System.out.println("게시판 수정::::"+result);
		if( result > 0) {
			mav.setViewName("redirect:boardPageMain.do?page=1");
		} else {
			mav.setViewName("redirect:/goLoginMain");
		}
		return mav;
	}
	
	public ModelAndView boardPageList(int page) {
		mav = new ModelAndView();
		
		PageDTO pagedto = new PageDTO();
		int limit = 5;
		pagedto.setLimit(limit);
		pagedto.setPage(page);
		
		int startRow = (page-1)*limit+1;
		int endRow = page*limit;
		pagedto.setStartRow(startRow);
		pagedto.setEndRow(endRow);
		
		//현재 페이지에 출력할 글
		List<BoardDTO> boardList = memberDAO.boardPageList(pagedto);
		
		//전체 글 갯수
		int boardCnt = memberDAO.boardCnt();
		System.out.println("boardcnt::::"+boardCnt);
		
		int maxPage = (int)( (double)boardCnt/limit + 0.9 );
		int startPage = ( ( (int) ((double)page/10 + 0.9) ) -1 ) *10 +1;
		int endPage = startPage + 10 -1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		pagedto.setStartPage(startPage);
		pagedto.setEndPage(endPage);
		pagedto.setMaxPage(maxPage);
		
		mav.addObject("boardList", boardList);
		mav.addObject("pagedto", pagedto);
		mav.setViewName("./board/boardPageMain");
		
		return mav;
	}
	public String boardDeleteAjax(int boardNum) {
		int result = memberDAO.boardDeleteAjax(boardNum);
		String deleteResult = null;
		System.out.println("service::::"+result);
		if(result > 0) {
			deleteResult = "OK";
		} else {
			deleteResult = "NO";
		}
		return deleteResult;
	}
	public ModelAndView boardWriteFile(BoardDTO boardDTO) {
		mav = new ModelAndView();
		int boardWriteResult = memberDAO.boardWriteFile(boardDTO);
		
		if(boardWriteResult > 0){
			mav.setViewName("redirect:boardPageMain.do?page=1");
		} else {
			mav.setViewName("LoginMain");
		}
		return mav;
	}

}
