package com.product.Controler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemberService;
import com.mem.model.MemberVO;
import com.porderDetail.model.PorderDetailService;
import com.porderDetail.model.PorderDetailVO;
import com.product.model.ProVO;
import com.productorder.Controler.Notice;
import com.productorder.model.PorderService;
import com.promotionDetail.model.PromoDetailService;
import com.promotionDetail.model.PromoDetailVO;

public class Shopcart extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		HttpSession session = req.getSession();
		PrintWriter out = res.getWriter();
		List<ProVO> buylist = (ArrayList<ProVO>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");
		if (!action.equals("check")) {

			// 從購物車頁面送來的action
			if ("delete".equals(action)) {
				ProVO pro = getProduct(req);
				int num = buylist.indexOf(pro);
				buylist.remove(num);

				// 從商城首頁和商城分頁送來的action
			} else if ("addcart".equals(action)) {
				ProVO pro = getProduct(req);
				if (buylist == null) {
					buylist = new ArrayList<ProVO>();
					buylist.add(pro);
				} else {
					if (!buylist.contains(pro)) {
						buylist.add(pro);
					}
				}
				// 從購物車頁面送來的action
			} else if ("addcartfromcart".equals(action)) {
				ProVO pro = getProduct(req);
				int index = buylist.indexOf(pro);
				ProVO innerpro = buylist.get(index);
				innerpro.setQuantity(innerpro.getQuantity() + pro.getQuantity());
				// 從購物車頁面送來的action
			} else if ("minuscartfromcart".equals(action)) {
				ProVO pro = getProduct(req);
				int index = buylist.indexOf(pro);
				ProVO innerpro = buylist.get(index);
				if (innerpro.getQuantity() >= 1) {
					innerpro.setQuantity(innerpro.getQuantity() - pro.getQuantity());
					if (innerpro.getQuantity() <= 0) {
						buylist.remove(index);
					}
				}
			}
			int total = getTotal(buylist);
			session.setAttribute("shoppingcart", buylist);
			session.setAttribute("total", total);
			String url = req.getContextPath()+"/front-end/product/shopcart.jsp";
			res.sendRedirect(url);
		}
		if("check".equals(action)) {
			String memno = (String)session.getAttribute("memNO");
			String country = req.getParameter("country");
			String city = req.getParameter("city");
			String road = req.getParameter("road");
			PorderService pordersvc = new PorderService();
			PorderDetailService odetailsvc = new PorderDetailService();
			List<PorderDetailVO> list = new ArrayList<PorderDetailVO>();
			//拿到消費總金額
			Integer total = getTotal(buylist);
			int orderid = pordersvc.addporder(memno, total,country+city+road,0);
			//拿到明細List
			for(ProVO pro:buylist) {
				PorderDetailVO orderdetailvo = new PorderDetailVO();
				orderdetailvo.setOrderid(orderid);
				orderdetailvo.setProid(pro.getProductid());
				orderdetailvo.setQuantity(pro.getQuantity());
				orderdetailvo.setUnitprice(pro.getProductprice());
				list.add(orderdetailvo);
			}
			//寫入資料庫 消費明細表格
			odetailsvc.addporderDetail(list);
			session.removeAttribute("shoppingcart");
			
			//推播
			Socket.sendmessage(String.valueOf(orderid));
			Notice.saveChatMessage(String.valueOf(orderid), "新增一筆訂單");
			res.sendRedirect(req.getContextPath()+"/front-end/product/myorder.jsp");
		}
	}

	private Integer getTotal(List<ProVO> list) {
		int total = list.stream()
		.mapToInt(p ->p.getProductprice()*p.getQuantity())
		.sum();
		return total;
	}

	private ProVO getProduct(HttpServletRequest req) {

		String productid = req.getParameter("productid");
		String productname = req.getParameter("productname");
		String productprice = req.getParameter("productprice");
		ProVO pro = new ProVO();

		pro.setQuantity(1);
		pro.setProductid(new Integer(productid));
		pro.setProductname(productname);
		pro.setProductprice(new Integer(productprice));
		return pro;
	}
}
