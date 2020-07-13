package com.promotion.model;

import java.io.Serializable;
import java.sql.Date;

public class PromotionVO implements Serializable{
	private Integer promotionno;
	private String  promotionname;
	private  Date   startday;
	private  Date   endday;
	
	public PromotionVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PromotionVO(Integer promotionno, String promotionname, Date startday, Date endday) {
		super();
		this.promotionno = promotionno;
		this.promotionname = promotionname;
		this.startday = startday;
		this.endday = endday;
	}

	public Integer getPromotionno() {
		return promotionno;
	}

	public void setPromotionno(Integer promotionno) {
		this.promotionno = promotionno;
	}

	public String getPromotionname() {
		return promotionname;
	}

	public void setPromotionname(String promotionname) {
		this.promotionname = promotionname;
	}

	public Date getStartday() {
		return startday;
	}

	public void setStartday(Date startday) {
		this.startday = startday;
	}

	public Date getEndday() {
		return endday;
	}

	public void setEndday(Date endday) {
		this.endday = endday;
	}
	
	
}
