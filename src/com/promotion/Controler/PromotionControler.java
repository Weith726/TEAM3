package com.promotion.Controler;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.Controler.Socket;
import com.promotion.model.PromotionService;
import com.promotionDetail.model.PromoDetailService;
import com.promotionDetail.model.PromoDetailVO;


public class PromotionControler extends HttpServlet {
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		
		if ("addpromotion".equals(action)) {
			PromoDetailVO promodetail = null;
			List<PromoDetailVO> list = new ArrayList<PromoDetailVO>();
			try {
				String promotionname = req.getParameter("promotionname");
				Date startday = java.sql.Date.valueOf(req.getParameter("startday").trim());
				Date endday = java.sql.Date.valueOf(req.getParameter("endday").trim());
				String[] productid = req.getParameterValues("productid");
				String[] promotionprice = req.getParameterValues("promotionprice");				
				int len = productid.length;
				
				PromotionService promoSvc = new PromotionService();
				PromoDetailService promoDtsvc = new PromoDetailService();
				int seq = promoSvc.addpromotion(promotionname, startday, endday);
				for(int i =0;i<len;i++) {
					promodetail = new PromoDetailVO();
					promodetail.setPromotionno(seq);
					promodetail.setProductid(new Integer(productid[i]));
					promodetail.setPromotionprice(new Integer(promotionprice[i]));
					list.add(promodetail);
				}
				promoDtsvc.addpromotionDetail(list);
				Socket socket = new Socket();
				socket.sendmessage("有新的優惠摟!");
				req.setAttribute("success","新增成功");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/promotion/addPromotion.jsp");
				failureView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/promotion/addPromotion.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("getonepromotiondetail".equals(action)) {
			Integer promotionno = new Integer(req.getParameter("promotionno"));
			PromoDetailService svc = new PromoDetailService();
			List<PromoDetailVO> list =  svc.getOne(promotionno);
			req.setAttribute("listpromoDetail", list);
			RequestDispatcher forw = req.getRequestDispatcher("/back-end/promotion/listOnepromotion.jsp");
			forw.forward(req,res);
		}

	}
}
