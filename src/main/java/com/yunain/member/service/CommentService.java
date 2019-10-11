package com.yunain.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunain.member.dao.CommentDAO;
import com.yunain.member.dto.CommentDTO;

@Service
public class CommentService {
	@Autowired
	private CommentDAO commentDAO;

	public void commentWrite(CommentDTO commentDTO) {
		commentDAO.commentWrite(commentDTO);
	}

	public List<CommentDTO> commentList(int commentBoardNumber) {
		return commentDAO.commentList(commentBoardNumber);
	}

}
