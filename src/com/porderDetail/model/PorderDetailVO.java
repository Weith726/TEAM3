package com.porderDetail.model;

import java.io.Serializable;

public class PorderDetailVO implements Serializable{
	private Integer orderid;
	private Integer proid;
	private Integer quantity;
	private Integer unitprice;
	
	public PorderDetailVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PorderDetailVO(Integer orderid, Integer proid, Integer quantity, Integer unitprice) {
		super();
		this.orderid = orderid;
		this.proid = proid;
		this.quantity = quantity;
		this.unitprice = unitprice;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public Integer getProid() {
		return proid;
	}

	public void setProid(Integer proid) {
		this.proid = proid;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(Integer unitprice) {
		this.unitprice = unitprice;
	}
	
	
}
