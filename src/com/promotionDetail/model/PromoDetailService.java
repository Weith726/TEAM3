package com.promotionDetail.model;

import java.util.List;

public class PromoDetailService {
	
	private PromoDetailDAO_IN dao;
	
	public PromoDetailService() {
		dao = new PromoDetailDAO();
	}
	public void addpromotionDetail(List<PromoDetailVO> list) {
		dao.addpromotion(list);
	}
	public List<PromoDetailVO> getOne(Integer id){
		return dao.getOne(id);
	}
	public List<PromoDetailVO> getPromotionprice(){
		return dao.getPmotionprice();
	}
	public void update(PromoDetailVO PromoDetailVO) {
		PromoDetailVO vo = new PromoDetailVO();
		vo.setProductid(PromoDetailVO.getProductid());
		vo.setPromotionno(PromoDetailVO.getPromotionno());
		vo.setPromotionprice(PromoDetailVO.getPromotionprice());
		dao.updatepromodetail(vo);
	}
}
