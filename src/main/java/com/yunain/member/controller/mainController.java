package com.yunain.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class mainController {
	//프로젝트 실행 시 기본으로 출력할 페이지 설정
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		return "index";
	}
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String goIndex() {
		return "index";
	}
}
