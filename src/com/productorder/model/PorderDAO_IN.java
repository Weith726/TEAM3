package com.productorder.model;

import java.util.List;

import com.product.model.ProVO;

public interface PorderDAO_IN {
	public List<PorderVO> getAll();
	public PorderVO findByPrimaryKey(Integer orderid);
	public Integer insert(PorderVO pordervo);
    public void update(Integer orderid,Integer orderstatus);
    public List<PorderVO> getorderbymem(String memno);
}
