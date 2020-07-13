package com.adopter.controller;

import java.io.*;
import java.sql.Date;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.adoptedpets.model.AdoptedPetsService;
import com.adoptedpets.model.AdoptedPetsVO;
import com.adopter.model.*;

@MultipartConfig
public class AdopterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自listAllAdopter.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("adopterNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入領養人編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/adopter/listAllAdopter.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				String adopterNo = null;
				try {
					adopterNo = new String(str);
				} catch (Exception e) {
					errorMsgs.add("領養人不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/adopter/listAllAdopter.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				AdopterService adopterSvc = new AdopterService();
				AdopterVO adopterVO = adopterSvc.getOneAdopter(adopterNo);
				if (adopterVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/adopter/listAllAdopter.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("adopterVO", adopterVO); // 資料庫取出的adopterVO物件,存入req
				String url = "/back-end/adopt/adopter/listOneAdopter.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAdopter.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/adopter/listAllAdopter.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String adopterNo = new String(req.getParameter("adopterNo"));

				/*************************** 2.開始查詢資料 ****************************************/
				AdopterService adopterSvc = new AdopterService();
				AdopterVO adopterVO = adopterSvc.getOneAdopter(adopterNo);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("adopterVO", adopterVO); // 資料庫取出的adopterVO物件,存入req
				String url = "/back-end/adopt/adopter/update_Adopter_Input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 update_Adopter_Input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/adopter/listAllAdopter.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自 update_AdoptedPets_Input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//		
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String adopterNo = req.getParameter("adopterNo");
				if (adopterNo == null || adopterNo.trim().length() == 0) {
					errorMsgs.add("領養人編號: 請勿空白");
				}

				String adopterName = req.getParameter("adopterName");
				if (adopterName == null || adopterName.trim().length() == 0) {
					errorMsgs.add("姓名: 請勿空白");
				}

				String adopterGender = req.getParameter("adopterGender");
				if (adopterGender == null || adopterGender.trim().length() == 0) {
					errorMsgs.add("性別: 請勿空白");
				}

				String adopterOccupation = req.getParameter("adopterOccupation");
				if (adopterOccupation == null || adopterOccupation.trim().length() == 0) {
					errorMsgs.add("職業: 請勿空白");
				}

				String adopterMail = req.getParameter("adopterMail").toUpperCase();
				if (adopterMail == null || adopterMail.trim().length() == 0) {
					errorMsgs.add("職業: 請勿空白");
				}

				AdopterVO adopterVO = new AdopterVO();
				adopterVO.setAdopterNo(adopterNo);
				adopterVO.setAdopterName(adopterName);
				adopterVO.setAdopterGender(adopterGender);
				adopterVO.setAdopterOccupation(adopterOccupation);
				adopterVO.setAdopterMail(adopterMail);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adopterVO", adopterVO); // 含有輸入格式錯誤的adopterVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/adopt/adopter/update_Adopter_Input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				AdopterService adopterSvc = new AdopterService();
				adopterVO = adopterSvc.updateAdopter(adopterNo, adopterName, adopterGender, adopterOccupation,
						adopterMail);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("adopterVO", adopterVO); // 資料庫取出的adopterVO物件,存入req
				String url = "/back-end/adopt/adopter/listOneAdopter.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneAdopter.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/adopter/update_Adopter_Input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自 add_AdoptedPets.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//		
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String adopterName = req.getParameter("adopterName");
				if (adopterName == null || adopterName.trim().length() == 0) {
					errorMsgs.add("姓名: 請勿空白");
				}

				String adopterGender = req.getParameter("adopterGender");
				if (adopterGender == null || adopterGender.trim().length() == 0) {
					errorMsgs.add("性別: 請勿空白");
				}

				String adopterOccupation = req.getParameter("adopterOccupation");
				if (adopterOccupation == null || adopterOccupation.trim().length() == 0) {
					errorMsgs.add("職業: 請勿空白");
				}

				String adopterMail = req.getParameter("adopterMail").toUpperCase();
				if (adopterOccupation == null || adopterOccupation.trim().length() == 0) {
					errorMsgs.add("職業: 請勿空白");
				}

				AdopterVO adopterVO = new AdopterVO();
				adopterVO.setAdopterName(adopterName);
				adopterVO.setAdopterGender(adopterGender);
				adopterVO.setAdopterOccupation(adopterOccupation);
				adopterVO.setAdopterMail(adopterMail);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adopterVO", adopterVO); // 含有輸入格式錯誤的adopterVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/adopter/add_Adopter.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				AdopterService adopterSvc = new AdopterService();
				adopterVO = adopterSvc.addAdopter(adopterName, adopterGender, adopterOccupation, adopterMail);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("adopterVO", adopterVO); // 資料庫取出的adopterVO物件,存入req
				String url = "/back-end/adopt/adopter/listAllAdopter.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listAllAdopter.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/adopter/add_Adopter.jsp");
				failureView.forward(req, res);
			}
		}

		if ("frontendInsert".equals(action)) { // 來自 add_AdoptedPets.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//		
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String adopterName = req.getParameter("adopterName");
				if (adopterName == null || adopterName.trim().length() == 0) {
					errorMsgs.add("姓名: 請勿空白");
				}

				String adopterGender = req.getParameter("adopterGender");
				if (adopterGender == null || adopterGender.trim().length() == 0) {
					errorMsgs.add("性別: 請勿空白");
				}

				String adopterOccupation = req.getParameter("adopterOccupation");
				if (adopterOccupation == null || adopterOccupation.trim().length() == 0) {
					errorMsgs.add("職業: 請勿空白");
				}

				String adopterMail = req.getParameter("adopterMail").toUpperCase();
				if (adopterOccupation == null || adopterOccupation.trim().length() == 0) {
					errorMsgs.add("職業: 請勿空白");
				}

				AdopterVO adopterVO = new AdopterVO();
				adopterVO.setAdopterName(adopterName);
				adopterVO.setAdopterGender(adopterGender);
				adopterVO.setAdopterOccupation(adopterOccupation);
				adopterVO.setAdopterMail(adopterMail);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("adopterVO", adopterVO); // 含有輸入格式錯誤的adopterVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/adopt/adopter/addAdopter.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				AdopterService adopterSvc = new AdopterService();
				adopterVO = adopterSvc.addAdopter(adopterName, adopterGender, adopterOccupation, adopterMail);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("adopterVO", adopterVO); // 資料庫取出的adopterVO物件,存入req
				String url = "/front-end/adopt/adoptedpets/listAllPets.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/adopt/adopter/addAdopter.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllAdopter.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String adopterNo = req.getParameter("adopterNo");
				if (adopterNo == null || adopterNo.trim().length() == 0) {
					errorMsgs.add("寵物編號: 請勿空白");
				}

				/*************************** 2.開始刪除資料 ***************************************/
				AdopterService adopterSvc = new AdopterService();
				adopterSvc.deleteAdopter(adopterNo);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/adopt/adopter/listAllAdopter.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/adopt/adopter/listAllAdopter.jsp");
				failureView.forward(req, res);
			}
		}

		if ("login".equals(action)) { // 來自listAllAdopter.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				String adopterName = req.getParameter("adopterName");
				if (adopterName == null || adopterName.trim().length() == 0) {
					errorMsgs.add("領養人姓名: 請勿空白");
				}

				String adopterMail = req.getParameter("adopterMail").toUpperCase();
				if (adopterMail == null || adopterMail.trim().length() == 0) {
					errorMsgs.add("領養人信箱: 請勿空白");
				}

				/***************************
				 * 2.開始查詢信箱資料間驗證
				 ***************************************/
				AdopterService adopterSvc = new AdopterService();
				AdopterVO adopterVO = new AdopterVO();
				adopterVO = adopterSvc.getOneMail(adopterName);
				
				System.out.println(adopterMail);
				System.out.println(adopterVO.getAdopterMail());
				System.out.println(adopterVO.getAdopterNo());
				System.out.println(req.getContextPath());
				
				
				if (adopterMail.equals(adopterVO.getAdopterMail())) {
					HttpSession session = req.getSession();
					session.setAttribute("adopterName", adopterName);
					session.setAttribute("adopterNo", adopterVO.getAdopterNo());

					try {
						String location = (String) session.getAttribute("location");
						if (location != null) {
							System.out.println("在這");
							session.removeAttribute("location");
							res.sendRedirect(location);
							return;
						}
					} catch (Exception ignore) {
					}

					res.sendRedirect(req.getContextPath()+"/front-end/adopt/adoptedpets/listAllPets.jsp");

				} else {

					res.sendRedirect(req.getContextPath()+"/front-end/adopt/adopter/adopterLogIn.jsp");
				}

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				System.out.println(e.getMessage());
				res.sendRedirect(req.getContextPath()+"/front-end/adopt/adopter/adopterLogIn.jsp");
//				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/adopt/adopter/adopterLogIn.jsp");
//				failureView.forward(req, res);
			}
		}

	}
}
