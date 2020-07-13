package com.memlatestinfo.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MemlatestinfoService {

	private Memlatestinfo_interface dao;
	
	public MemlatestinfoService() {
		dao = new MemlatestinfoDAO();
	}
	
	public MemlatestinfoVO addMemli(String memNo,String infoContent) {
		MemlatestinfoVO memlatestinfoVO = new MemlatestinfoVO();
		
		memlatestinfoVO.setMemNo(memNo);
		memlatestinfoVO.setInfoContent(infoContent);
		
		dao.insert(memlatestinfoVO);
		return memlatestinfoVO;
	}
	
	public MemlatestinfoVO updateMemli(Integer memLatestInfoNo,String memNo,String infoContent) {
		MemlatestinfoVO memlatestinfoVO = new MemlatestinfoVO();
		
		memlatestinfoVO.setMemLatestInfoNo(memLatestInfoNo);
		memlatestinfoVO.setMemNo(memNo);
		memlatestinfoVO.setInfoContent(infoContent);
		dao.update(memlatestinfoVO);
		
		return memlatestinfoVO;
	}
	
	
	public void deleteMemlatestinfo(Integer memLatestInfoNo) {		
		dao.delete(memLatestInfoNo);		
	}
	
	public MemlatestinfoVO getOneMemli(Integer memLatestInfoNo) {
		return dao.findByPrimaryKey(memLatestInfoNo);
	}

	public List<MemlatestinfoVO> getAll() {
		return dao.getAll();
	}
	
}
