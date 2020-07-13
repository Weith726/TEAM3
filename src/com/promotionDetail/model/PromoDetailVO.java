package com.promotionDetail.model;

import java.io.Serializable;

public class PromoDetailVO implements Serializable{
	private Integer promotionno;
	private Integer productid;
	private Integer promotionprice;
	
	public PromoDetailVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PromoDetailVO(Integer promotionno, Integer productid, Integer promotionprice) {
		super();
		this.promotionno = promotionno;
		this.productid = productid;
		this.promotionprice = promotionprice;
	}

	public Integer getPromotionno() {
		return promotionno;
	}
	
	public void setPromotionno(Integer promotionno) {
		this.promotionno = promotionno;
	}

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public Integer getPromotionprice() {
		return promotionprice;
	}

	public void setPromotionprice(Integer promotionprice) {
		this.promotionprice = promotionprice;
	}
	
	
	
}
