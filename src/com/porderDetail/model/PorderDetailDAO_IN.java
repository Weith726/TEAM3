package com.porderDetail.model;

import java.util.List;

public interface PorderDetailDAO_IN {
	public void addporderDetail(List<PorderDetailVO> list);
	public List<PorderDetailVO> getOne(Integer orderid);
}
