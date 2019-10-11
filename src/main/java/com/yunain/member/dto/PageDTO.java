package com.yunain.member.dto;

import lombok.Data;

@Data
public class PageDTO {
	private int page, maxPage, startPage, endPage, boardCnt;
	private int limit;
	private int startRow, endRow;
}	
