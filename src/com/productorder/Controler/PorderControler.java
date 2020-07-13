package com.productorder.Controler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.porderDetail.model.PorderDetailService;
import com.porderDetail.model.PorderDetailVO;
import com.product.Controler.Socket;
import com.productorder.model.PorderService;
import com.productorder.model.PorderVO;

public class PorderControler extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		PrintWriter out = res.getWriter();

		if ("updateorder".equals(action)) {
			String str = null;
			Integer orderstatus = Integer.parseInt(req.getParameter("orderstatus"));
			Integer orderid = Integer.parseInt(req.getParameter("orderid"));
			PorderService ordersvc = new PorderService();
			ordersvc.update(orderstatus, orderid);
			PorderVO vo = ordersvc.getOne(orderid);
			switch (orderstatus) {
			case 1:
				str = "您的訂單"+orderid+"已出貨";
				break;
			case 2:
				str = "您的訂單"+orderid+"已完成";
				break;
			case 3:
				str = "您的訂單"+orderid+"已取消";
				break;
			case 4:
				str = "您的訂單"+orderid+"審核中";
				break;
			}
			
			if(Socketformember.check(vo.getMemno())) {
				Socketformember.sendmessage(vo.getMemno(),str);
			}
			return;
		}

		if ("getdetail".equals(action)) {
			String url = req.getParameter("URI");
			Integer orderid = new Integer(req.getParameter("orderid"));
			PorderDetailService svc = new PorderDetailService();
			List<PorderDetailVO> list = svc.getdetailbyid(orderid);
			req.setAttribute("detail", list);
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("cancelorder".equals(action)) {
			String str = req.getParameter("textarea");
			Integer orderid = new Integer(req.getParameter("orderid"));
			PorderService ordersvc = new PorderService();
			ordersvc.update(4, orderid);
			Notice.saveChatMessage(String.valueOf(orderid),"取消訂單原因:"+str);
			CancelSocket.sendmessage(String.valueOf(orderid));
			String url = req.getParameter("url");
			res.sendRedirect(req.getContextPath()+url);
		}
		
		if("getcancelmsg".equals(action)) {
			out.print(Notice.getmessage());
		}
	}
}
