package com.promotionDetail.Controler;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.promotionDetail.model.PromoDetailService;
import com.promotionDetail.model.PromoDetailVO;

public class PromotionDetailControler extends HttpServlet
{
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		
		if("updatepromoDetail".equals(action)) {
			String url = req.getParameter("requestURI");
			PromoDetailVO vo = new PromoDetailVO();
			Integer productid = new Integer(req.getParameter("productid"));
			Integer promotionno = new Integer(req.getParameter("promotionno"));
			Integer promotionprice = new Integer(req.getParameter("promotionprice"));
			vo.setProductid(productid);
			vo.setPromotionno(promotionno);
			vo.setPromotionprice(promotionprice);
			PromoDetailService svc = new PromoDetailService();
			svc.update(vo);
			List<PromoDetailVO> list =  svc.getOne(promotionno);
			req.setAttribute("listpromoDetail",list);
			RequestDispatcher forw = req.getRequestDispatcher(url);
			forw.forward(req,res);
		}
	}
}
