package com.opt.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;

import javax.servlet.http.*;

import com.google.gson.*;

import com.opt.model.*;




/**
 * Servlet implementation class OptServlet
 */

public class OptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String sessionNo = req.getParameter("sessionNo");
				if (sessionNo == null || (sessionNo.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/hospital/opt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				sessionNo = null;
				try {
					sessionNo = req.getParameter("sessionNo");
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/hospital/opt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				OptService optSvc = new OptService();
				OptVO optVO = optSvc.getOneOptSession(sessionNo);
				if (optVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/hospital/opt/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("optVO", optVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/hospital/opt/listOneOpt.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/hospital/opt/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String docNo = req.getParameter("docNo");
				
				String optSession = req.getParameter("optSession");
				if (optSession == null || optSession.trim().length() == 0) {
					errorMsgs.add("請選擇一個時段");

				}

				java.sql.Date optDate = null;
				try {
					// 將前端日期字串轉成JAVA Date物件
					optDate = java.sql.Date.valueOf(req.getParameter("optDate").trim());
				} catch (IllegalArgumentException e) {
					// 例外把日期設為null
					optDate = null;
					errorMsgs.add("請選擇門診日期");
				}
				
				Integer currentCount = 0;
				

				

				Integer maximum = null;
				try {
					maximum = new Integer(req.getParameter("maximum").trim());
					
					
				} catch (NumberFormatException e) {		
					errorMsgs.add("請輸入限制數量");
				}
					
				
			
				

				OptVO optVO = new OptVO();
				optVO.setDocNo(docNo);
				optVO.setOptDate(optDate);
				optVO.setOptSession(optSession);
				optVO.setMaximum(maximum);
				System.out.println(docNo);
				System.out.println(optDate);
				System.out.println(optSession);
				
				
				//以下為重複資料處理
				OptService optSvcCheck = new OptService();
				OptVO optVO_PK = optSvcCheck.findSession(docNo, optDate, optSession);
				
				if(optVO_PK != null) {
					
					errorMsgs.add("此時段已新增過");
					
					
				}
				
				
	
				


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("optVO", optVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/hospital/opt/addOptSession.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				OptService optSvc = new OptService();
				optVO = optSvc.addOptSession(docNo, optDate
						,optSession,currentCount, maximum);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				

				String url = "/back-end/hospital/opt/select_page.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage() + "其他的錯誤");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/hospital/opt/addOptSession.jsp");
				failureView.forward(req, res);
			}
		}
		


//		if ("testJson".equals(action)) {	 // 來自addEmp.jsp的請求
//			
//			
//			OptService optSvc = new OptService();
//		
//				List<OptVO> list = optSvc.getAll();
//				String docno = new String();
//				for(OptVO optVO:list) {
//					docno = optVO.getDocNo();
//					System.out.println(optVO.getDocNo());
//					System.out.println(optVO.getOptSession());
//					System.out.println(optVO.getOptDate());
//					System.out.println(optVO.getMaximum());
//					
//				}
//				System.out.println("醫生:"+docno);
//			
//			
//			
//				Gson gson = new Gson();
//			
//			String strvalue = "[{\"title\":\"大師\",\"start\":\"2020-07-02\"}]";
//			
//			
//			
//			
//			 res.setCharacterEncoding("UTF-8");
//		        System.out.println("strvalue="+strvalue);
//		        try {
//		            res.getWriter().print(strvalue);
//		        } catch (IOException e) {
//		            e.printStackTrace();
//		        }
//
//
//
//	}
		
		
//		public String getData(String title, String start) throws JSONException {
//		    JSONObject jsonObject = new JSONObject("{  title : VARIABLE1,  start : VARIABLE2}");
//		   
//		    jsonObject.put("title", name);
//		    jsonObject.put("start", age);
//		    return jsonObject.toString();
//		}

}

//	 public String testJson(HttpServletRequest request, HttpServletResponse response) {
//	        String strvalue = "[{\"id\":111,\"title\":\"Event1\",\"start\":\"2012-03-10\",\"url\":\"http:\\/\\/yahoo.com\\/\"},{\"id\":222,\"title\":\"Event2\",\"start\":\"2012-03-20\",\"end\":\"2012-03-22\",\"url\":\"http:\\/\\/yahoo.com\\/\"}]";
//	       
//	        return null;
//	    }

}


