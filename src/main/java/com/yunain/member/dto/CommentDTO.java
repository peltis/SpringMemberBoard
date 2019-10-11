package com.yunain.member.dto;

import lombok.Data;

@Data
public class CommentDTO {
	
	private int commentNumber, commentBoardNumber;
	private String commentContents, commentWriter;
}
