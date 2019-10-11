package com.yunain.member.dto;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardDTO {
	private int boardNum, boardHit;
	private Date boardDate;
	private String boardWriter, boardSubject, boardContents, boardPassword;
	private MultipartFile boardFile;
	private String boardFileName;
	
}