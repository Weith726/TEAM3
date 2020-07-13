package com.promotion.model;

import java.util.List;

public interface PromotionDAO_IN {
	public List<PromotionVO> getAll();
	public PromotionVO getOnepromotion(Integer promotionno);
	public Integer addPromotion(PromotionVO promotion);
	public void updatePromotion(PromotionVO promotion);
}
