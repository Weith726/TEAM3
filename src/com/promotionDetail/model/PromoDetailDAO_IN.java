package com.promotionDetail.model;

import java.util.List;

public interface PromoDetailDAO_IN {
	public void addpromotion(List<PromoDetailVO> list);
	public List<PromoDetailVO> getOne(Integer id);
	public List<PromoDetailVO> getPmotionprice();
	public void updatepromodetail(PromoDetailVO PromoDetailVO);
}
