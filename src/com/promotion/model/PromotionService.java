package com.promotion.model;

import java.sql.Date;
import java.util.List;

public class PromotionService {
	
	private PromotionDAO_IN dao;
	
	public PromotionService() {
		dao= new PromotionDAO();
	}
	
	public List<PromotionVO> getAll(){
		return dao.getAll();
	}
	
	public Integer addpromotion(String name,Date startday,Date endday) {
		PromotionVO promotion= new PromotionVO();
		promotion.setPromotionname(name);
		promotion.setStartday(startday);
		promotion.setEndday(endday);
		Integer seq = dao.addPromotion(promotion);
		return seq;
	}
	
	public PromotionVO getOne(Integer promotionno) {
		return dao.getOnepromotion(promotionno);
	}
	
	public void update(String name,Date startday,Date endday,Integer promotionno) {
		PromotionVO promotion= new PromotionVO();
		promotion.setPromotionno(promotionno);
		promotion.setPromotionname(name);
		promotion.setStartday(startday);
		promotion.setEndday(endday);
		dao.updatePromotion(promotion);
	}
}
