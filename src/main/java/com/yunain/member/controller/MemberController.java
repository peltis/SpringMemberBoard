package com.yunain.member.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
import com.yunain.member.dto.PageDTO;
import com.yunain.member.service.MemberSerivce;

@Controller
@RequestMapping("/members/*")
public class MemberController {
	
	@Autowired
	private MemberSerivce memberService;
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	private ModelAndView mav;
	
	//로그인 메인으로 가기
	@RequestMapping(value="/goLoginMain", method= {RequestMethod.GET, RequestMethod.POST})
	public String goLoginMain(@ModelAttribute MemberDTO memberDTO) {
		return "LoginMain";
	}
	
	//회원가입 버튼 눌렀을때
	@RequestMapping(value="/JoinForm.do", method=RequestMethod.GET)
	public String goJoinForm(@ModelAttribute MemberDTO memberDTO) {
		return "./members/JoinForm";
	}
	
	//로그인 버튼 눌렀을 때
	@RequestMapping(value="/LoginForm.do", method=RequestMethod.GET)
	public String goLoginForm(@ModelAttribute MemberDTO memberDTO) {
		return "./members/LoginForm";
	}
	
	//회원가입처리
	/*@RequestMapping(value="/memberJoin", method=RequestMethod.POST)
	public ModelAndView memberJoin(@ModelAttribute MemberDTO memberDTO) {
		//ModelAndView 객체생성
		mav = new ModelAndView();
		
		//서비스 클래스의 memberJoin 메소드 호출
		mav = memberService.memberJoin(memberDTO);
		
		return mav;
	}*/
	
	//회원가입처리(new)
	@RequestMapping(value="/memberJoin", method=RequestMethod.POST)
	public ModelAndView memberJoin(@ModelAttribute MemberDTO memberDTO) {
		//ModelAndView 객체생성
		mav = new ModelAndView();
		
		String encPassword = passEncoder.encode( memberDTO.getPassword() ); 
		memberDTO.setPassword(encPassword);
		
		mav = memberService.memberJoin(memberDTO);
		
		return mav;
	}
	
	//로그인처리
	@RequestMapping(value="/memberLogin", method=RequestMethod.POST)
	public ModelAndView memberLogin(@ModelAttribute MemberDTO memberDTO, HttpServletResponse response) throws IOException {
		mav = new ModelAndView();
		
		mav = memberService.memberLogin(memberDTO, response);
		
		return mav;
	}

	
	//회원목록
	@RequestMapping(value="/MemberList.do", method=RequestMethod.GET)
	public ModelAndView MemberList(@ModelAttribute MemberDTO memberDTO, HttpServletResponse response) throws IOException {
		mav = new ModelAndView();
		
		mav = memberService.memberList(memberDTO, response);
		
		return mav;
	}
	
	//회원상세보기
	@RequestMapping(value="/memberDetail.do", method=RequestMethod.GET)
	public ModelAndView Memberdetail(@ModelAttribute MemberDTO memberDTO, HttpServletResponse response) throws IOException {
		mav = new ModelAndView();
		
		mav = memberService.memberDetail(memberDTO, response);
		
		return mav;
	}
	
	//회원정보 수정페이지 출력
	@RequestMapping(value="/memberModifyView.do", method=RequestMethod.GET)
	public ModelAndView MemberView(@RequestParam("id") String id) {
		mav = new ModelAndView();
		
		mav = memberService.memberModifyView(id);
		
		return mav;
	}
	
	//회원정보 수정처리
	@RequestMapping(value="/memberModify.do", method=RequestMethod.POST)
	public ModelAndView MemberModify(@ModelAttribute MemberDTO memberDTO) {
		mav = new ModelAndView();
		
		mav = memberService.memberModify(memberDTO);
		
		return mav;
	}
	
	//아이디 중복처리확인
	@RequestMapping(value="/idOverlap", method=RequestMethod.POST)
	public @ResponseBody String idOverlap(@RequestParam("id") String id) {
		
		String resultMsg = memberService.idOverlap(id);
		return resultMsg;
	}
	
	//아이디 중복처리확인
	@RequestMapping(value="/memberListAjax.do", method=RequestMethod.GET)
	public @ResponseBody List<MemberDTO> memberListAjax() {
		mav = new ModelAndView();
		List<MemberDTO> memberList = memberService.memberListAjax();
		return memberList;
	}
	
	
}
