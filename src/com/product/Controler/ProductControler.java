package com.product.Controler;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.json.JSONObject;

import com.product.model.ProService;
import com.product.model.ProVO;

@MultipartConfig
public class ProductControler extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			Integer proid = Integer.parseInt(req.getParameter("proid"));
			ProService prosvc = new ProService();
			ProVO provo = prosvc.getOneproduct(proid);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("productVO", provo); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/product/updatePro.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);

		}

		if ("update_check".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String url = req.getParameter("requestURL");
			try {
				Integer productid = Integer.parseInt(req.getParameter("proid").trim());
				// 商品名稱***************************************
				String productname = req.getParameter("productname");
				String Reg = "^[(\u4e00-\u9fa5)]{2,10}$";
				if (productname == null || productname.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} else if (!productname.trim().matches(Reg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名稱: 只能是中文, 且長度必需在2到10之間");
				}

				// 商品價錢***************************************
				Integer productprice = null;

				try {
					productprice = new Integer(req.getParameter("productprice"));
				} catch (NumberFormatException e) {
					productprice = 0;
					errorMsgs.add("產品單價請填數字");
				}

				// 商品上架日期***************************************
				java.sql.Date producton = null;
				try {
					producton = java.sql.Date.valueOf(req.getParameter("producton").trim());
				} catch (IllegalArgumentException e) {
					producton = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				// 商品庫存**************************************

				Integer productstock = null;
				try {
					productstock = new Integer(req.getParameter("productstock"));
				} catch (NumberFormatException e) {
					productstock = 0;
					errorMsgs.add("產品庫存請填數字");
				}

				// 商品安全庫存***************************************
				Integer productsafe = null;

				try {
					productsafe = new Integer(req.getParameter("productsafe"));
				} catch (NumberFormatException e) {
					productsafe = 0;
					errorMsgs.add("產品安全庫存請填數字");
				}

				// 商品圖片***************************************
				byte[] productpic = null;
				Part part = req.getPart("productpic");
				InputStream in = part.getInputStream();
				//先判斷Part裡面有沒有資料，Part裡沒有資料代表沒有上或修改新照片
				if (in.available() > 0) {
					productpic = new byte[in.available()];
					in.read(productpic);
					in.close();
				} else {
					//Part裡沒有資料,NEW 一個Service去拿原本VO裡的byte[]
					ProService svc = new ProService();
					ProVO pp = svc.getOneproduct(productid);
					productpic = pp.getProductpic();
				}

				// 商品介紹***************************************
				String productintro = req.getParameter("productintro");

				// 商品狀態***************************************
				Integer productstatus = Integer.parseInt(req.getParameter("productstatus"));
				// 商品種類***************************************
				Integer kindno = new Integer(req.getParameter("kindno"));

				ProVO provo = new ProVO();
				provo.setProductid(productid);
				provo.setProductname(productname);
				provo.setProductprice(productprice);
				provo.setProducton(producton);
				provo.setProductstock(productstock);
				provo.setProductsafe(productsafe);
				provo.setProductpic(productpic);
				provo.setProductintro(productintro);
				provo.setProductstatus(productstatus);
				provo.setKindno(kindno);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", provo); // 含有輸入格式錯誤的物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/updatePro.jsp");
					failureView.forward(req, res);
					return;
				}
				ProService prosvc = new ProService();
				provo = prosvc.updatePro(kindno, productname, productprice, producton, productstock, productsafe,
						productpic, productintro, productstatus, productid);
//				req.setAttribute("success", "修改成功");
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/listAllproduct.jsp");
				failureView.forward(req, res);
			}
		}

		if ("addproduct".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {

				// 商品名稱***************************************
				String productname = req.getParameter("productname");
				String Reg = "^[(\u4e00-\u9fa5)]{2,10}$";
				if (productname == null || productname.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				} else if (!productname.trim().matches(Reg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("商品名稱: 只能是中文, 且長度必需在2到10之間");
				}

				// 商品價錢***************************************
				Integer productprice = null;

				try {
					productprice = new Integer(req.getParameter("productprice"));
				} catch (NumberFormatException e) {
					productprice = 0;
					errorMsgs.add("產品單價請填數字");
				}

				// 商品上架日期***************************************
				java.sql.Date producton = null;
				try {
					producton = java.sql.Date.valueOf(req.getParameter("producton").trim());
				} catch (IllegalArgumentException e) {
					producton = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				// 商品庫存**************************************

				Integer productstock = null;
				try {
					productstock = new Integer(req.getParameter("productstock"));
				} catch (NumberFormatException e) {
					productstock = 0;
					errorMsgs.add("產品庫存請填數字");
				}

				// 商品安全庫存***************************************
				Integer productsafe = null;

				try {
					productsafe = new Integer(req.getParameter("productsafe"));
				} catch (NumberFormatException e) {
					productsafe = 0;
					errorMsgs.add("產品安全庫存請填數字");
				}

				// 商品圖片***************************************

				Part part = req.getPart("productpic");
				InputStream in = part.getInputStream();
				if (in.available() == 0) {
					errorMsgs.add("商品 圖片: 請勿空白");
				}
				byte[] productpic = new byte[in.available()];
				in.read(productpic);
				in.close();

				// 商品介紹***************************************
				String productintro = req.getParameter("productintro");

				// 商品狀態***************************************
				Integer productstatus = Integer.parseInt(req.getParameter("productstatus"));
				// 商品種類***************************************
				Integer kindno = new Integer(req.getParameter("kindno"));

				ProVO provo = new ProVO();
				provo.setProductname(productname);
				provo.setProductprice(productprice);
				provo.setProducton(producton);
				provo.setProductstock(productstock);
				provo.setProductsafe(productsafe);
				provo.setProductpic(productpic);
				provo.setProductintro(productintro);
				provo.setProductstatus(productstatus);
				provo.setKindno(kindno);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", provo); // 含有輸入格式錯誤的VO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/addProduct.jsp");
					failureView.forward(req, res);
					return;
				}
				ProService prosvc = new ProService();
				int seq = prosvc.addProduct(kindno, productname, productprice, producton, productstock, productsafe,
						productpic, productintro, productstatus);
				
//				req.setAttribute("update", "新增成功");
				ProVO newprovo = prosvc.getOneproduct(seq);
				JSONArray jObj = new JSONArray();
				jObj.put(String.valueOf(seq));
				jObj.put(newprovo.getProductname());
				jObj.put(newprovo.getProductprice());
				SocketforClient.sendmessage(jObj.toString());
				String url = "/back-end/product/listAllproduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交addProduct.jsp
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/addProduct.jsp");
				failureView.forward(req, res);
			}
		}

		if ("select".equals(action)) {
			String name = req.getParameter("productname").trim();
			if(name == null || name.trim().length() == 0) {
				req.setAttribute("noProduct", "請輸入商品名稱!");
				String url = "/back-end/product/listAllproduct.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
				dispatcher.forward(req, res);
				return;
			} 
			ProService svc = new ProService();
			List<ProVO> list = svc.getSelect(name);
			if (list.size() == 0) {
				req.setAttribute("noProduct", "查無資料!");
				String url = "/back-end/product/listAllproduct.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
				dispatcher.forward(req, res);
				return;
			} else {
				String url ="/back-end/product/listAllproduct.jsp";
				req.setAttribute("select",list.size());
				req.setAttribute("product", list);
				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
				dispatcher.forward(req, res);
			}
		}
//		***********************************搜尋狗狗****改用ajax所以註解，註解為forward回去jsp
		if ("selectdog".equals(action)) {
			String name = req.getParameter("productname").trim();
			if(name == null || name.trim().length() == 0) {
				out.print("[]");
//				out.print("請輸入商品名稱!");
////				req.setAttribute("noProduct", "請輸入商品名稱!");
////				String url = "/product/dogindex.jsp";
////				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
////				dispatcher.forward(req, res);
				return;
			}
			ProService svc = new ProService();
			List<ProVO> list = svc.getSelectdog(name);
//			if (list.size() == 0) {
//				out.print("查無資料!");
//				req.setAttribute("noProduct", "查無資料!");
////				String url = "/product/dogindex.jsp";
////				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
////				dispatcher.forward(req, res);
//				return;
//			} else {
				String jsonStr = new JSONArray(list).toString();
				out.print(jsonStr);
//				String url = "/product/dogindex.jsp";
//				req.setAttribute("selectdog",list.size());
//				req.setAttribute("dogproduct", list);
//				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
//				dispatcher.forward(req, res);
			}
//		}
//		***********************************搜尋貓咪
		if ("selectcat".equals(action)) {
			String name = req.getParameter("productname").trim();
			if(name == null || name.trim().length() == 0) {
				out.print("[]");
//				out.print("請輸入商品名稱!");
////				req.setAttribute("noProduct", "請輸入商品名稱!");
////				String url = "/product/dogindex.jsp";
////				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
////				dispatcher.forward(req, res);
				return;
			}
			ProService svc = new ProService();
			List<ProVO> list = svc.getSelectcat(name);
//			if (list.size() == 0) {
//				out.print("查無資料!");
//				req.setAttribute("noProduct", "查無資料!");
////				String url = "/product/dogindex.jsp";
////				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
////				dispatcher.forward(req, res);
//				return;
//			} else {
				String jsonStr = new JSONArray(list).toString();
				out.print(jsonStr);
//				String url = "/product/dogindex.jsp";
//				req.setAttribute("selectdog",list.size());
//				req.setAttribute("dogproduct", list);
//				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
//				dispatcher.forward(req, res);
			}
//		}
		
//		***********************************搜尋其他
		if ("selectanother".equals(action)) {
			String name = req.getParameter("productname").trim();
			if(name == null || name.trim().length() == 0) {
				out.print("[]");
//				out.print("請輸入商品名稱!");
////				req.setAttribute("noProduct", "請輸入商品名稱!");
////				String url = "/product/dogindex.jsp";
////				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
////				dispatcher.forward(req, res);
				return;
			}
			ProService svc = new ProService();
			List<ProVO> list = svc.getSelectanother(name);
//			if (list.size() == 0) {
//				out.print("查無資料!");
//				req.setAttribute("noProduct", "查無資料!");
////				String url = "/product/dogindex.jsp";
////				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
////				dispatcher.forward(req, res);
//				return;
//			} else {
				String jsonStr = new JSONArray(list).toString();
				out.print(jsonStr);
//				String url = "/product/dogindex.jsp";
//				req.setAttribute("selectdog",list.size());
//				req.setAttribute("dogproduct", list);
//				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
//				dispatcher.forward(req, res);
			}
		if ("selectforindex".equals(action)) {
			String name = req.getParameter("productname").trim();
			if(name == null || name.trim().length() == 0) {
				out.print("[]");
//				out.print("請輸入商品名稱!");
////				req.setAttribute("noProduct", "請輸入商品名稱!");
////				String url = "/product/dogindex.jsp";
////				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
////				dispatcher.forward(req, res);
				return;
			}
			ProService svc = new ProService();
			List<ProVO> list = svc.getSelectForIndex(name);
//			if (list.size() == 0) {
//				out.print("查無資料!");
//				req.setAttribute("noProduct", "查無資料!");
////				String url = "/product/dogindex.jsp";
////				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
////				dispatcher.forward(req, res);
//				return;
//			} else {
			String jsonStr = new JSONArray(list).toString();
			out.print(jsonStr);
//				String url = "/product/dogindex.jsp";
//				req.setAttribute("selectdog",list.size());
//				req.setAttribute("dogproduct", list);
//				RequestDispatcher dispatcher = req.getRequestDispatcher(url);
//				dispatcher.forward(req, res);
		}
		}
	}
