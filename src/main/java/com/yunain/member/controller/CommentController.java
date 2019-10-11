package com.yunain.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunain.member.dto.CommentDTO;
import com.yunain.member.service.CommentService;

@Controller
@RequestMapping("/comment/*")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value="commentWrite", method=RequestMethod.POST)
	public @ResponseBody List<CommentDTO> commentWrite(@ModelAttribute CommentDTO commentDTO){
		//댓글 DB에 쓰기
		System.out.println("comment writer::::::"+commentDTO.getCommentWriter() );
		commentService.commentWrite(commentDTO);
		//DB에서 댓글 가져오기
		List<CommentDTO> commentList = commentService.commentList( commentDTO.getCommentBoardNumber() );
		return commentList;
	}
}
