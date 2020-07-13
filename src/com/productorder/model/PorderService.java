package com.productorder.model;

import java.sql.Timestamp;
import java.util.List;

public class PorderService {
	private PorderDAO_IN dao;
	
	public PorderService() {
		dao = new PorderDAO();
	}
	
	public Integer addporder(String memno,Integer ordertotal, String deliveryaddress,
			Integer orderstatus) {
		PorderVO order = new PorderVO();

		order.setMemno(memno);
		order.setOrdertotal(ordertotal);
		order.setDeliveryaddress(deliveryaddress);
		order.setOrderstatus(orderstatus);
		Integer seq = dao.insert(order);
		return seq;
	}
	
	public List<PorderVO> getAll(){
		return dao.getAll();
	}
	
	public PorderVO getOne(Integer orderid) {
		return dao.findByPrimaryKey(orderid);
	}
	
	public void update(Integer orderstatus,Integer orderid) {
		dao.update(orderstatus,orderid);
	}
	public List<PorderVO> getbymemno(String memno){
		return dao.getorderbymem(memno);
	}
	
}
