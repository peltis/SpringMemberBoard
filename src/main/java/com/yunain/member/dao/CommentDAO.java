package com.yunain.member.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunain.member.dto.CommentDTO;

@Repository
public class CommentDAO {
	@Autowired
	private SqlSessionTemplate sql;
	
	public void commentWrite(CommentDTO commentDTO) {
		sql.insert("Comment.commentWrite", commentDTO);
	}

	public List<CommentDTO> commentList(int commentBoardNumber) {
		return sql.selectList("Comment.commentList", commentBoardNumber);
	}

}
