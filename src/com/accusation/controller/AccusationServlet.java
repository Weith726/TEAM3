package com.accusation.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.accusation.model.*;
import com.mem.model.MemberService;
import com.mem.model.MemberVO;

public class AccusationServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		
		if("getOne_For_Display".equals(action)) {
			

			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("accusationNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入客訴編號");
				}
				
				if(!str.matches("[M]{1}\\d\\d\\d\\d")) {
					errorMsgs.add("客訴編號格式不正確");
				}	
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/accusation/acc_select.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				
				
				AccusationService aSvc = new AccusationService();
				AccusationVO accVO = aSvc.getOneA(str);
				if (accVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/accusation/acc_select.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("accVO", accVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/member/accusation/listone_acc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); //  成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/accusation/acc_select.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		
		if("insert".contentEquals(action)) {
			
		   List<String> errorMsgs = new LinkedList<String>();
		   
		   req.setAttribute("errorMsgs",errorMsgs);
		   Integer accusationStatue = null;
		   
		   try {
		   String accusationType = req.getParameter("accusationType");
		   String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (accusationType == null || accusationType.trim().length() == 0) {
				errorMsgs.add("客訴類型: 請勿空白");
			} else if(!accusationType.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("客訴類型: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
           }
			
			String accusationContent = req.getParameter("accusationContent");
			if (accusationContent== null || accusationContent.trim().length() == 0) {
				errorMsgs.add("客訴內容請勿空白");
			}
			
			String Statue = req.getParameter("accusationStatue");
			if (Statue== null || Statue.trim().length() == 0) {
				errorMsgs.add("客訴狀態請勿空白");
			}
			//避免轉成integer錯誤
			try {
				accusationStatue =new Integer(Statue);
				}catch(Exception e) {
					errorMsgs.add("客訴狀態型態不正確");
			}
			AccusationVO accVO = new AccusationVO();
			accVO.setAccusationType(accusationType);;
			accVO.setAccusationContent(accusationContent);
			accVO.setAccusationStatue(accusationStatue);
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("accVO", accVO); // 含有輸入格式錯誤的empVO物件,也存入req
						RequestDispatcher failureView = req
								.getRequestDispatcher("/front-end/member/member/accusation/add_acc.jsp");
						failureView.forward(req, res);
						return;
			}
			
			AccusationService aSvc = new AccusationService();
			accVO = aSvc.addA(accusationType, accusationContent, accusationStatue);
			req.setAttribute("insertacc", "新增成功");
			String url ="/front-end/member/member/login.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		   }catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/member/accusation/add_acc.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("delete".contentEquals(action)) {
		   List<String> errorMsgs = new LinkedList<String>();
		   req.setAttribute("errorMsgs", errorMsgs);
		   try {
		   String accusationNo = req.getParameter("accusationNo");
		   AccusationService aSvc = new AccusationService();
		   aSvc.deleteA(accusationNo);
		   
		   String url="/back-end/member/accusation/listAll_acc.jsp";
		   RequestDispatcher successView = req.getRequestDispatcher(url);
		   successView.forward(req, res);
		   
		   }catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/accusation/listAll_acc.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try { 
				/***************************1.接收請求參數****************************************/
				String accusationNo = req.getParameter("accusationNo");
				
				/***************************2.開始查詢資料****************************************/
				AccusationService aSvc = new AccusationService();
				AccusationVO accVO = aSvc.getOneA(accusationNo);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("accVO", accVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/member/accusation/update_acc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交  update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理***********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/accusation/listone_acc.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			Integer accusationStatue = null;
			
			try {
			String accusationNo = req.getParameter("accusationNo");
			
			String accusationType = req.getParameter("accusationType");
			   String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (accusationType == null || accusationType.trim().length() == 0) {
					errorMsgs.add("客訴類型: 請勿空白");
				} else if(!accusationType.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("客訴類型: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	           }
				
				String accusationContent = req.getParameter("accusationContent");
				if (accusationContent== null || accusationContent.trim().length() == 0) {
					errorMsgs.add("客訴內容請勿空白");
				}
				
				String Statue = req.getParameter("accusationStatue");
				if (Statue== null || Statue.trim().length() == 0) {
					errorMsgs.add("客訴狀態請勿空白");
				}
				//避免轉成integer錯誤
				try {
					accusationStatue =new Integer(Statue);
					}catch(Exception e) {
						errorMsgs.add("客訴狀態型態不正確");
				}
			
				AccusationVO accVO = new AccusationVO();
				accVO.setAccusationNo(accusationNo);
				accVO.setAccusationType(accusationType);
				accVO.setAccusationContent(accusationContent);
				accVO.setAccusationStatue(accusationStatue);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("accVO", accVO); // 含有輸入格式錯誤的accVO物件,也存入req
							RequestDispatcher failureView = req
									.getRequestDispatcher("/back-end/member/accusation/update_acc.jsp");
							failureView.forward(req, res);
							return;
				}
				
				AccusationService aSvc = new AccusationService();
				accVO = aSvc.updateA(accusationType, accusationContent, accusationStatue, accusationNo);

				req.setAttribute("accVO",accVO);
				String url = "/back-end/member/accusation/listAll_acc.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); //修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
			}catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/accusation/update_acc.jsp");
				failureView.forward(req, res);
			}
				
		}
		
	}	
}
