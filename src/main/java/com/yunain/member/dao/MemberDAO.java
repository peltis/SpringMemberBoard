package com.yunain.member.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
//java와 db를 연결해주는 mybatis
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yunain.member.dto.BoardDTO;
import com.yunain.member.dto.MemberDTO;
import com.yunain.member.dto.PageDTO;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSessionTemplate sql;

	public int memberJoin(MemberDTO memberDTO) {
		return sql.insert("Member.memberJoin", memberDTO);
	}

	public MemberDTO memberLogin(MemberDTO memberDTO) {
		return sql.selectOne("Member.memberLogin", memberDTO);
	}

	/*public List<MemberDTO> memberList(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		return sql.selectList("Member.memberList", memberDTO);
	}*/

	public MemberDTO memberDetail(MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		return sql.selectOne("Member.memberDetail", memberDTO);
	}
	
	public MemberDTO memberView(String id) {
		return sql.selectOne("Member.memberView", id);
	}

	public int memberModify(MemberDTO memberDTO) {
		return sql.update("Member.memberModify", memberDTO);
	}

	public String idOverlap(String id) {
		return sql.selectOne("Member.idOverlap", id);
	}
	
	public List<MemberDTO> memberList() {
		return sql.selectList("Member.memberList");
	}

	public List<BoardDTO> boardList() {
		return sql.selectList("Member.boardList");
	}

	public int boardWrite(BoardDTO boardDTO) {
		return sql.insert("Member.boardWrite", boardDTO);
	}

	public BoardDTO boardDetail(int boardNum) {
		return sql.selectOne("Member.boardDetail", boardNum);
	}

	public int boardHit(int boardNum) {
		return sql.update("Member.boardHit", boardNum);
	}

	public int boardModify(BoardDTO boardDTO) {
		return sql.update("Member.boardModify", boardDTO);
	}

	public int boardCnt() {
		return sql.selectOne("Member.boardCnt");
	}

	public List<BoardDTO> boardPageList(PageDTO pagedto) {
		return sql.selectList("Member.boardPageList", pagedto);
	}

	public int boardDeleteAjax(int boardNum) {
		return sql.delete("Member.boardDeleteAjax", boardNum);
	}

	public int boardWriteFile(BoardDTO boardDTO) {
		return sql.insert("Member.boardWriteFile", boardDTO);
	}

}
