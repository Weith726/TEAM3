package com.memlatestinfo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.accusation.model.AccusationService;
import com.mem.model.MemberService;
import com.mem.model.MemberVO;
import com.memlatestinfo.model.MemlatestinfoService;
import com.memlatestinfo.model.MemlatestinfoVO;



public class MemlatestinfoServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("mli");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/memlatestinfo/Memlatestinfo_select.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer mli = null;
				try {
					mli = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("訊息編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/memlatestinfo/Memlatestinfo_select.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				
				
				MemlatestinfoService mliSvc = new MemlatestinfoService();
				MemlatestinfoVO mliVO = mliSvc.getOneMemli(mli);
				if (mliVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/memlatestinfo/Memlatestinfo_select.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("mliVO", mliVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/member/memlatestinfo/listone_memli.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/memlatestinfo/Memlatestinfo_select.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getAll_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("mli");
				/***************************2.開始查詢資料*****************************************/				
				//為了讓登入頁面裡面不會按了最新消息後直接導到listAll
				if (str == null || (str.trim()).length() == 0) {
					res.sendRedirect(req.getContextPath() + "/front-end/member/login.jsp");
					return;
				}
				
				
				MemlatestinfoService mliSvc = new MemlatestinfoService();
				List<MemlatestinfoVO> mliVO = mliSvc.getAll();
				if (mliVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/login.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("mliVO", mliVO); //資料庫取出的empVO物件,存入req
				
				req.setAttribute("mli",str);  //讓listall知道有沒有會員在查詢
				
				
				String url = "/front-end/member/memlatestinfo/listAll_memli.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); //  成功轉交 listAll.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/login.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if("insert".equals(action) || "insertAll".equals(action) ) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
								
				MemlatestinfoService mliSvc = new MemlatestinfoService();
				
				
				String memNO = req.getParameter("memNO");
				if (memNO == null || memNO.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}				
				if(!memNO.matches("[M]{1}\\d\\d\\d\\d")) {
					errorMsgs.add("會員編號格式不正確");
				}
				
				MemberService mSvc = new MemberService();
				MemberVO memberNO = mSvc.getOneEmp(memNO);
				if (memberNO == null) {
					errorMsgs.add("查無此會員");
				}
				
				String InfoContent = req.getParameter("InfoContent");
				if("insert".equals(action)) {
					System.out.println("123");
					if (InfoContent == null || InfoContent.trim().length() == 0) {
					errorMsgs.add("訊息內容請勿空白");
					System.out.println("456");
					}
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/memlatestinfo/add_memli.jsp");
					failureView.forward(req, res);
					return;
				}
				//新增消息給全部會員
				if("insertAll".equals(action)) {
					List<com.mem.model.MemberVO> list = mSvc.getAll();
					for(MemberVO all : list) {
						mliSvc.addMemli(all.getMemNo(),InfoContent);
					}
				}else {
					mliSvc.addMemli(memNO,InfoContent);
					System.out.println("789");
				}
				
				String url = "/back-end/member/memlatestinfo/listAll_memli.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);	
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}
		
		if("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try { 
				/***************************1.接收請求參數****************************************/
				Integer memliNO = new Integer(req.getParameter("memliNO"));
				/***************************2.開始查詢資料****************************************/
				MemlatestinfoService mliSvc = new MemlatestinfoService();
				MemlatestinfoVO mliVO = mliSvc.getOneMemli(memliNO);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("mliVO", mliVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/member/memlatestinfo/update_memli.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交  update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理***********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/memlatestinfo/listAll.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		
		
		if("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			Integer memliNO = null;
			
			try {
				
				MemlatestinfoService mliSvc = new MemlatestinfoService();
				String memliNO2 = req.getParameter("memliNO");
				
				try {
					memliNO = new Integer(memliNO2);
				}catch(Exception e) {
					errorMsgs.add("訊息編號: 請勿空白");
				}				 
				
				String memNO = req.getParameter("memNO");
				if (memNO == null || memNO.trim().length() == 0) {
					errorMsgs.add("會員編號請勿空白");
				}
				if(!memNO.matches("[M]{1}\\d\\d\\d\\d")) {
					errorMsgs.add("會員編號格式不正確");
				}
				
				String InfoContent = req.getParameter("InfoContent");				
				if (InfoContent == null || InfoContent.trim().length() == 0) {
					errorMsgs.add("訊息內容請勿空白");
				}
				
								
				MemlatestinfoVO mliVO = new MemlatestinfoVO();
				mliVO.setMemLatestInfoNo(memliNO);
				mliVO.setMemNo(memNO);
				mliVO.setInfoContent(InfoContent);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("mliVO", mliVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/memlatestinfo/update_memli.jsp");
					failureView.forward(req, res);
					return;
				}
				
				mliVO = mliSvc.updateMemli(memliNO, memNO, InfoContent);
				
				req.setAttribute("mliVO", mliVO);
				String url ="/back-end/member/memlatestinfo/listAll_memli.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);	
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		if("delete".contentEquals(action) || "delete_B".equals(action)) {
			   List<String> errorMsgs = new LinkedList<String>();
			   req.setAttribute("errorMsgs", errorMsgs);
			   try {
			   Integer memLatestInfoNo =new Integer( req.getParameter("memLatestInfoNo"));
			   MemlatestinfoService mliSvc = new MemlatestinfoService();
			   mliSvc.deleteMemlatestinfo(memLatestInfoNo);
			   
			   String url=null;
			   if ("delete".equals(action)) {
					url = "/front-end/member/memlatestinfo/listAll_memli.jsp";
					String str = req.getParameter("memNO");
					req.setAttribute("mli",str);
			   	}
				else if ("delete_B".equals(action)) {
					url = "/back-end/member/memlatestinfo/listAll_memli.jsp";
				}
			   req.setAttribute("delete", "新增成功");  //給前面的sweetalert用的
			   RequestDispatcher successView = req.getRequestDispatcher(url);
			   successView.forward(req, res);
			   
			   }catch (Exception e) {
					errorMsgs.add("刪除資料失敗:"+e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/memlatestinfo/listAll_memli.jsp");
					failureView.forward(req, res);
				}
			}
	}
	
}
