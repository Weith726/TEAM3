package com.porderDetail.model;

import java.util.List;

public class PorderDetailService {
	private PorderDetailDAO_IN dao;
	
	public PorderDetailService() {
		dao = new PorderDetailDAO();
	}
	 
	public void addporderDetail(List<PorderDetailVO> list) {
		dao.addporderDetail(list);
	}
	
	public List<PorderDetailVO> getdetailbyid(Integer id){
		return dao.getOne(id);
	}
}
