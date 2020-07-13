package com.productorder.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class PorderVO implements Serializable{
	private Integer orderid;
	private String memno;
	private String orderdate;
	private Integer ordertotal;
	private String deliveryaddress;
	private Integer orderstatus;
	
	public PorderVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PorderVO(Integer orderid, String memno, String orderdate, Integer ordertotal, String deliveryaddress,
			Integer orderstatus) {
		super();
		this.orderid = orderid;
		this.memno = memno;
		this.orderdate = orderdate;
		this.ordertotal = ordertotal;
		this.deliveryaddress = deliveryaddress;
		this.orderstatus = orderstatus;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public String getMemno() {
		return memno;
	}

	public void setMemno(String memno) {
		this.memno = memno;
	}

	public String getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}

	public Integer getOrdertotal() {
		return ordertotal;
	}

	public void setOrdertotal(Integer ordertotal) {
		this.ordertotal = ordertotal;
	}

	public String getDeliveryaddress() {
		return deliveryaddress;
	}

	public void setDeliveryaddress(String deliveryaddress) {
		this.deliveryaddress = deliveryaddress;
	}

	public Integer getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(Integer orderstatus) {
		this.orderstatus = orderstatus;
	}
	
	
	
	
}
